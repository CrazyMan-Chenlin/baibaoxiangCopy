package com.baibaoxiang.service;

import com.baibaoxiang.po.DayTotal;
import com.baibaoxiang.po.DayTotalKey;
import com.baibaoxiang.po.ReadLikeNumber;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;

/**
 * @create 2019-05-03-15:45
 * @author sheng
 */
public interface DayTotalService {


    /**
     * 查询 DayTotal
     *
     * @param key = no + day
     * @return
     */
    DayTotal selectByPrimaryKey(DayTotalKey key);

    /**
     * 删除 DayTotal
     *
     * @param key = no + day
     * @return
     */
    int deleteByPrimaryKey(DayTotalKey key);

    /**
     * 添加 DayTotal
     *
     * @param record
     * @return
     */
    int insert(DayTotal record);

    /**
     * 修改 DayTotal
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(DayTotal record);

    /**
     * 更新某天 某推文的阅读量
     *
     * @param no
     * @param time
     * @param num
     * @throws Exception
     */
    void updateReadNum(Date time, Integer num, Integer no) throws Exception;

    /**
     * 更新某天 某推文的点赞数
     *
     * @param no
     * @param time
     * @param num
     * @throws Exception
     */
    void updateLikeNum(Date time, Integer num, Integer no) throws Exception;

    /**
     * 网站一天的总点赞数和阅读量 （超级管理员使用）
     *
     * @param time
     * @return
     * @throws Exception
     */
    ReadLikeNumber dayTotal(Date time) throws Exception;

    /**
     * 某校区的 某天的总浏览量和点赞数（超级管理员和该地区管理员使用）
     *
     * @param area
     * @param time
     * @return
     * @throws Exception
     */
    ReadLikeNumber dayTotalArea(Date time, String area) throws Exception;

    /**
     * 某类型的所有推文 某天的总浏览量和点赞数（超级管理员使用）
     *
     * @param type
     * @return
     * @throws Exception
     */
    ReadLikeNumber dayTotalType(Date time, String type) throws Exception;

    /**
     * 某校区的 某类型的所有推文 某天的总浏览量和点赞数（超级管理员和该地区管理员使用）
     *
     * @param type
     * @param area
     * @return
     * @throws Exception
     */
    ReadLikeNumber dayTotalTypeArea(Date time, String area, String type) throws Exception;


    /**
     * 某篇推文 某天的点赞数
     *
     * @param time
     * @param no
     * @return
     * @throws Exception
     */
    ReadLikeNumber dayTotalNo(Date time, Integer no) throws Exception;


}
