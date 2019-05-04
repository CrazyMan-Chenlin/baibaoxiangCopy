package com.baibaoxiang.serviceImpl;


import com.baibaoxiang.mapper.ArticleMapper;
import com.baibaoxiang.mapper.custom.ArticleMapperCustom;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author sheng
 * @create 2019-04-23-00:19
 */
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleMapperCustom articleMapperCustom;

    @Override
    public Article selectByPrimaryKey(Integer no) throws Exception {
        Article article = articleMapper.selectByPrimaryKey(no);
        return article;
    }

    @Override
    public List<Article> selectByTypeArea(String type, String area) throws Exception {
        return articleMapperCustom.selectByTypeArea(type,area);
    }

    @Override
    public int insert(Article record) throws Exception {
        return articleMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer no) throws Exception {
        return articleMapper.deleteByPrimaryKey(no);
    }

    @Override
    public int updateByPrimaryKey(Article record) throws Exception {
        return articleMapper.updateByPrimaryKey(record);
    }
}
