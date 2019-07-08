package com.baibaoxiang.service;

import com.baibaoxiang.po.Area;

import java.util.List;

/**
 * @author chenlin
 */

public interface AreaService {
     int insertArea() throws Exception;
     Area selectArea() throws Exception;
     List<Area> selectAllArea() throws Exception;
     int updateArea() throws Exception;
     int deleteArea() throws Exception;
}
