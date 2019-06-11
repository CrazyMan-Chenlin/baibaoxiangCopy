package com.baibaoxiang.serviceimpl;

import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.ManagerMapper;
import com.baibaoxiang.mapper.custom.ManagerMapperCustom;
import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.ManagerService;
import com.baibaoxiang.tool.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/** ManagerService接口 的实现类
 * @author sheng
 * @create 2019-04-29-10:04
 */
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    ManagerMapperCustom managerMapperCustom;
    @Autowired
    JedisClient jedisClient;

     private String ManagerInfoKey = "Manager_INFO";
    @Override
    public Manager findManagerByUsername(String username) throws Exception {
        return managerMapperCustom.findManagerByUsername(username);
    }

    @Override
    public Manager findManagerWithPassword_salt(String username) throws Exception {
        return managerMapper.selectByPrimaryKey(username);
    }

    @Override
    public List<Manager> findManagersByTitle(String title) throws Exception {
        return managerMapperCustom.findManagersByTitle(title);
    }

    @Override
    public int insert(Manager record) throws Exception {
        return managerMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Manager record) throws Exception {
        String name = managerMapper.selectByPrimaryKey(record.getUsername()).getName();
        if (jedisClient.hexists(ManagerInfoKey,name)){
            jedisClient.hdel(ManagerInfoKey,name);
        }
        return managerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String username) throws Exception {
        String name = managerMapper.selectByPrimaryKey(username).getName();
        if (jedisClient.hexists(ManagerInfoKey,name)){
            jedisClient.hdel(ManagerInfoKey,name);
        }
        return managerMapper.deleteByPrimaryKey(username);
    }

    @Override
    public void deleteManagerBatch(String usernames) throws Exception {
        String arr [] = usernames.split(",");
        String name ;
        for(int i = 0; i < arr.length; i++){
            name = managerMapper.selectByPrimaryKey(arr[i]).getName();
            if (jedisClient.hexists(ManagerInfoKey,name)){
                jedisClient.hdel(ManagerInfoKey,name);
            }
            managerMapper.deleteByPrimaryKey(arr[i]);
        }
    }

    @Override
    public String queryAuthorPicture(String name) {
       if (jedisClient.hexists(ManagerInfoKey,name)){
         return JsonUtils.jsonToPojo(jedisClient.hget(ManagerInfoKey,name),String.class);
       }
        String authorPicture = managerMapper.queryAuthorPicture(name);
        jedisClient.hset(ManagerInfoKey,name, JsonUtils.objectToJson(authorPicture));
        return authorPicture;
    }
}
