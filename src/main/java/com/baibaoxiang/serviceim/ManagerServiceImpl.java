package com.baibaoxiang.serviceim;
import com.baibaoxiang.mapper.ManagerMapper;
import com.baibaoxiang.mapper.custom.ManagerMapperCustom;
import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.ManagerService;
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
        return managerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String username) throws Exception {
        return managerMapper.deleteByPrimaryKey(username);
    }

    @Override
    public void deleteManagerBatch(String usernames) throws Exception {
        String arr [] = usernames.split(",");
        for(int i = 0; i < arr.length; i++){
            managerMapper.deleteByPrimaryKey(arr[i]);
        }
    }


}
