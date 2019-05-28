package com.baibaoxiang.serviceImpl;


import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.ArticleMapper;
import com.baibaoxiang.mapper.custom.ArticleMapperCustom;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.ArticleExample;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.tool.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;


import java.sql.Date;
import java.text.SimpleDateFormat;
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
    @Autowired
    JedisClient jedisClient;

    private String articleInfoKey = "Article_INFO";

    @Override
    public Article selectByPrimaryKey(String no) throws Exception {

        Article article = articleMapper.selectByPrimaryKey(no);
        return article;
    }

    @Override
    public List<String> selectNoByCreateTime(Date startTime, Date endTime) throws Exception {
        return articleMapperCustom.selectNoByCreateTime(startTime,endTime);
    }

    @Override
    public List<Article> selectByTypeArea(String type, String area) throws Exception {
        return articleMapperCustom.selectByTypeArea(type,area);
    }

    @Override
    public List<Article> selectAllArticles() throws Exception {
        return articleMapperCustom.selectAllArticles();
    }

    @Override
    public int insertSelective(Article record) throws Exception {
        return articleMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String no) throws Exception {
        Article article = articleMapper.selectByPrimaryKey(no);
        String key = articleInfoKey + ":" + article.getType() + ":" + article.getArea();
        if (jedisClient.exists(key)) {
            jedisClient.del(key);
        }

        return articleMapper.deleteByPrimaryKey(no);
    }

    @Override
    public void deleteArticleBatch(String[] no) throws Exception {
        Article article;
        String key;
        for (int i = 0; i < no.length; i++) {
            article = articleMapper.selectByPrimaryKey(no[i]);
            key = articleInfoKey + ":" + article.getType() + ":" + article.getArea();
            if (jedisClient.exists(key)) {
                jedisClient.del(key);
            }

            articleMapper.deleteByPrimaryKey(no[i]);
        }
    }

    @Override
    public int updateByPrimaryKey(Article record) throws Exception {
        return articleMapper.updateByPrimaryKey(record);
    }

    /**
     *  更新阅读量
     * @param no
     * @param readNum
     * @throws Exception
     */
    @Override
    public void updateReadNum(String no, Integer readNum) throws Exception {

        if(jedisClient.hexists("readnum", no)){
           jedisClient.hdel("readnum", no);
        }
        articleMapperCustom.updateReadNum(no,readNum);
    }

    /**
     *  更新点赞量
     * @param no
     * @param likeNum
     * @throws Exception
     */
    @Override
    public void updateLikeNum(String no, Integer likeNum) throws Exception {

        if(jedisClient.hexists("likenum", no)){
            jedisClient.hdel("likenum", no);
        }
        articleMapperCustom.updateLikeNum(no,likeNum);
    }

    /**
     * 同时更新 浏览量和点赞量
     * @param no
     * @param readNum
     * @param likeNum
     * @throws Exception
     */
    @Override
    public void updateReadLikeNum(String no, Integer readNum, Integer likeNum) throws Exception {

        if(jedisClient.hexists("readnum", no)){
            jedisClient.hdel("readnum", no);
        }
        if(jedisClient.hexists("likenum", no)){
            jedisClient.hdel("likenum", no);
        }
        articleMapperCustom.updateReadLikeNum(no,readNum,likeNum);
    }

    /**
     * 添加缓存机制
     * @param area
     * @return
     * @throws Exception
     */
    @Override
    public List<Article> selectTopArticle(String area) throws Exception {
        String key = articleInfoKey + ":" + area;
        if (jedisClient.exists(key)) {
            String jsonString = jedisClient.get(key);
            return JsonUtils.jsonToList(jsonString, Article.class);
        } else {
            ArticleExample example = new ArticleExample();
            example.setOrderByClause("top");
            ArticleExample.Criteria criteria = example.createCriteria();
            criteria.andTopNotEqualTo(4);
            criteria.andAreaEqualTo(area);
            List<Article> articles = articleMapper.selectByExample(example);
            jedisClient.set(key, JsonUtils.objectToJson(articles));
            return articles;
        }
    }


}
