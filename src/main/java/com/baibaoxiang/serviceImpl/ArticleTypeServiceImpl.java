package com.baibaoxiang.serviceImpl;

import com.baibaoxiang.mapper.ArticleTypeMapper;
import com.baibaoxiang.mapper.custom.ArticleTypeMapperCustom;
import com.baibaoxiang.po.ArticleType;
import com.baibaoxiang.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author sheng
 * @create 2019-05-03-11:25
 */
public class ArticleTypeServiceImpl implements ArticleTypeService {

    @Autowired
    ArticleTypeMapper articleTypeMapper;
    @Autowired
    ArticleTypeMapperCustom articleTypeMapperCustom;


    @Override
    public ArticleType selectByPrimaryKey(Integer id) throws Exception {
        return articleTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(ArticleType record) throws Exception {
        return articleTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception {
        return articleTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ArticleType record) throws Exception {
        return articleTypeMapper.insert(record);
    }

    @Override
    public List<ArticleType> selectArticleTypes() throws Exception {
        return articleTypeMapperCustom.selectArticleTypes();
    }
}
