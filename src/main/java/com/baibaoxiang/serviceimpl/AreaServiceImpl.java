package com.baibaoxiang.serviceimpl;

import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.AreaMapper;
import com.baibaoxiang.mapper.SchoolMapper;
import com.baibaoxiang.mapper.custom.AreaMapperCustom;
import com.baibaoxiang.po.Area;
import com.baibaoxiang.po.AreaExample;
import com.baibaoxiang.service.AreaService;
import com.baibaoxiang.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sheng
 * @create 2019-07-03-17:09
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    JedisClient jedisClient;
    @Autowired
    AreaMapper areaMapper;
    @Autowired
    AreaMapperCustom areaMapperCustom;
    @Autowired
    SchoolService schoolService;
    private final String schoolInfoKey = "School_INFO:";
    @Override
    public Area findAreaById(Integer id) throws Exception {
        return areaMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Area> findAllAreas() throws Exception {
        return areaMapperCustom.findAllAreas();
    }

    @Override
    public List<Area> findAreaBySchoolName(String name) throws Exception {
        return areaMapperCustom.findAreaBySchoolName(name);
    }

    @Override
    public void updateArea(Area area) throws Exception {
        Integer schoolNo = area.getNo();
        String key = schoolInfoKey + schoolNo;
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        areaMapper.updateByPrimaryKeySelective(area);
    }

    @Override
    public void deleteAreaById(Integer id) throws Exception {
        //删除缓存
        Area area = this.findAreaById(id);
        if (area==null){
            throw new RuntimeException();
        }
        Integer schoolNo = area.getSchool().getNo();
        String key = schoolInfoKey + schoolNo;
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        areaMapper.deleteByPrimaryKey(id);
        AreaExample areaExample = new AreaExample();
        AreaExample.Criteria criteria = areaExample.createCriteria();
        criteria.andSchoolnoEqualTo(schoolNo);
        List<Area> areas = areaMapper.selectByExample(areaExample);
        if (areas.size() == 0){
            schoolService.deleteSchool(schoolNo);
        }
    }

    @Override
    public void deleteAreaBatch(Integer[] ids) throws Exception {
        Area area;
        Integer schoolNo;
        String key;
        for(Integer id : ids){
           this.deleteAreaById(id);
        }
    }

    @Override
    public void insertArea(Integer no,Integer schoolNo, String name) throws Exception {
        areaMapperCustom.insertArea(no,schoolNo,name);
    }

    @Override
    public Integer findMaxAreaNo() {
        return areaMapperCustom.findMaxAreaNo();
    }
}
