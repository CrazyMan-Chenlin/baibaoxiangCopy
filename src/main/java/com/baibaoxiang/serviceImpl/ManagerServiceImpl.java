package com.baibaoxiang.serviceImpl;

import com.baibaoxiang.mapper.ManagerMapper;
import com.baibaoxiang.mapper.custom.ManagerMapperCustom;
import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/** ManagerService接口 的实现类
 * @author sheng
 * @create 2019-04-29-10:04
 */
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    ManagerMapperCustom managerMapperCustom;

    @Override
    public Manager findManagerByUsername(String username) throws Exception {
        return managerMapperCustom.findManagerByUsername(username);
    }

    @Override
    public List<Manager> findManagersByTitle(String title) throws Exception {
        return managerMapperCustom.findManagersByTitle(title);
    }

    @Override
    public int insert(Manager record) throws Exception {
        return managerMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(Manager record) throws Exception {
        return managerMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(String username) throws Exception {
        return managerMapper.deleteByPrimaryKey(username);
    }
}
