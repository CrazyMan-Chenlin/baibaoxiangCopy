package com.baibaoxiang.mapper.custom;

import com.baibaoxiang.po.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sheng
 * @create 2019-07-02-22:06
 */
public interface AreaMapperCustom {

    /** 添加
     * @param schoolNo
     * @param name
     */
    void insertArea(@Param("schoolNo") Integer schoolNo,@Param("name") String name);

    /**  查询所有的校区
     * @return
     */
    List<Area> findAllAreas();

    /** 获取某学校的所有校区
     * @param name
     * @return
     */
    List<String> findAreaBySchoolName(String name);
}
