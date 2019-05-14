package com.baibaoxiang.serviceImpl;


import com.baibaoxiang.mapper.ArticleMapper;
import com.baibaoxiang.mapper.custom.ArticleMapperCustom;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.ArticleExample;
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
    public List<Article> selectAllAticles() throws Exception {
        return articleMapperCustom.selectAllArticles();
    }

    @Override
    public int insertSelective(Article record) throws Exception {
        return articleMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer no) throws Exception {
        return articleMapper.deleteByPrimaryKey(no);
    }

    @Override
    public void deleteArticleBatch(Integer[] no) throws Exception {
        for(int i = 0; i < no.length; i++){
            articleMapper.deleteByPrimaryKey(no[i]);
        }
    }

    @Override
    public int updateByPrimaryKey(Article record) throws Exception {
        return articleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Article> selectTopArticle(String area) throws Exception {
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("top");
        ArticleExample.Criteria criteria = example.createCriteria();
        criteria.andTopNotEqualTo(4);
        criteria.andAreaEqualTo(area);
        return articleMapper.selectByExample(example);
    }
}
