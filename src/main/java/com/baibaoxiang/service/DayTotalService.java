package com.baibaoxiang.service;

import com.baibaoxiang.po.DayTotal;
import com.baibaoxiang.po.DayTotalKey;

/**
 * @author sheng
 * @create 2019-05-03-15:45
 */
public interface DayTotalService {


    /** 查询 DayTotal
     * @param key = no + day
     * @return
     */
    DayTotal selectByPrimaryKey( DayTotalKey key);

    /** 删除 DayTotal
     * @param key = no + day
     * @return
     */
    int deleteByPrimaryKey(DayTotalKey key);

    /** 添加 DayTotal
     * @param record
     * @return
     */
    int insert(DayTotal record);

    /** 修改 DayTotal
     * @param record
     * @return
     */
    int updateByPrimaryKey(DayTotal record);
}
