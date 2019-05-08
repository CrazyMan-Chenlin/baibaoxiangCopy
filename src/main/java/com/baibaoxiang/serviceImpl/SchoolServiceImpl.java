package com.baibaoxiang.serviceImpl;

import com.baibaoxiang.mapper.SchoolMapper;
import com.baibaoxiang.po.School;
import com.baibaoxiang.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author sheng
 * @create 2019-05-03-15:44
 */
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public int insertSchool(School record) throws Exception {
        return schoolMapper.insert(record);
    }

    @Override
    public int deleteSchool(Integer no) throws Exception {
        return schoolMapper.deleteByPrimaryKey(no);
    }

    @Override
    public School selectSchoolByNo(Integer no) throws Exception {
        return schoolMapper.selectByPrimaryKey(no);
    }

    @Override
    public List<School> selectAllSchool() throws Exception {
        return null;
    }
}
