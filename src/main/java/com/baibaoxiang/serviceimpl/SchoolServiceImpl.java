package com.baibaoxiang.serviceimpl;
import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.SchoolMapper;
import com.baibaoxiang.mapper.custom.SchoolMapperCustom;
import com.baibaoxiang.po.School;
import com.baibaoxiang.po.SchoolExample;
import com.baibaoxiang.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sheng
 * @create 2019-05-03-15:44
 */
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    JedisClient jedisClient;
    @Autowired
    SchoolMapper schoolMapper;
    @Autowired
    SchoolMapperCustom schoolMapperCustom;
    private String schoolInfoKey = "School_INFO";
    private String key = schoolInfoKey + ":" + "SCHOOLNAME";

    @Override
    public int insertSchool(School record) throws Exception {
        return schoolMapper.insert(record);
    }

    @Override
    public int deleteSchool(Integer no) throws Exception {
        return schoolMapper.deleteByPrimaryKey(no);
    }

    @Override
    public void deleteSchoolBatch(Integer[] no) throws Exception {
        for (int i = 0; i < no.length; i++) {
            schoolMapper.deleteByPrimaryKey(no[i]);
        }
    }

    @Override
    public School selectSchoolByNo(Integer no) throws Exception {
        return schoolMapper.selectByPrimaryKey(no);
    }

    @Override
    public List<School> selectAllSchool() throws Exception {
        return schoolMapperCustom.selectAllSchool();
    }

    /**
     * 添加缓存机制
     * @return
     * @throws Exception
     */
    @Override
    public List<String> selectDifferentSchoolName() throws Exception {
        List<String> schoolName;
        StringBuilder value = new StringBuilder();
        if (jedisClient.exists(key)) {
            String array = jedisClient.get(key);
            String[] split = array.split(",");
            schoolName = Arrays.asList(split);
            return schoolName;
        } else {
            schoolName = schoolMapper.selectDifferentSchoolName();
            for (String str : schoolName) {
                value.append(str);
                value.append(",");
            }
            value.delete(value.length() - 1, value.length());
            jedisClient.set(key, value.toString());
            return schoolName;
        }
    }

    @Override
    public List<String> selectSchoolArea(String schoolName) throws Exception {
        // 添加缓存的原则是，不能够影响现在有的业务逻辑
        // 查询缓存
        /*删除key 存在于添加和删除area的时候，删除*/
        List<String> areaName = new ArrayList<>();
        try {
            if (schoolName != null) {
                // 从缓存中查询
                String areaNameString = jedisClient.get(schoolInfoKey + ":" + schoolName + ":BASE1");
                if (!"".equals(areaNameString) && areaNameString != null) {
                    String[] split = areaNameString.split(",");
                    areaName = Arrays.asList(split);
                    return areaName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SchoolExample example = new SchoolExample();
        SchoolExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(schoolName);
        List<School> schools = schoolMapper.selectByExample(example);
        for (School school : schools) {
            areaName.add(school.getArea());
        }
        /*添加缓存*/
        try {
            // 注入redisjedisCluster
            StringBuilder value = new StringBuilder();
            if (schoolName != null) {
                for (String str : areaName) {
                    value.append(str);
                    value.append(",");
                }
                value.delete(value.length() - 1, value.length());
                jedisClient.set(schoolInfoKey + ":" + schoolName + ":BASE1", value.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areaName;
    }
}
