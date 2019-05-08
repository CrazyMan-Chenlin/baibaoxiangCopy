package com.baibaoxiang.mapper.custom;

import com.baibaoxiang.po.School;

import java.util.List;

/**
 * @author sheng
 * @create 2019-05-08-11:47
 */
public interface SchoolMapperCustom {

    /** 获取所有的校区信息
     * @return
     * @throws Exception
     */
    List<School> selectAllSchool () throws Exception;
}
