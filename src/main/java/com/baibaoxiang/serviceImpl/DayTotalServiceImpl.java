package com.baibaoxiang.serviceImpl;

import com.baibaoxiang.mapper.DayTotalMapper;
import com.baibaoxiang.po.DayTotal;
import com.baibaoxiang.po.DayTotalKey;
import com.baibaoxiang.service.DayTotalService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sheng
 * @create 2019-05-03-15:44
 */
public class DayTotalServiceImpl implements DayTotalService {

    @Autowired
    DayTotalMapper dayTotalMapper;

    @Override
    public DayTotal selectByPrimaryKey(DayTotalKey key) {
        return dayTotalMapper.selectByPrimaryKey(key);
    }

    @Override
    public int deleteByPrimaryKey(DayTotalKey key) {
        return dayTotalMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(DayTotal record) {
        return dayTotalMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(DayTotal record) {
        return dayTotalMapper.updateByPrimaryKey(record);
    }
}
