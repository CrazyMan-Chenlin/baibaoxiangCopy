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

    /**通过校名删除学校（即是 删除该校名的所有校区）
     * @param name
     */
    void deleteSchoolBySchoolName(String name);

    /** 通过校名 获取所有校区的编号
     * @param name
     */
    List<Integer> seleteNosBySchoolName(String name);
}
