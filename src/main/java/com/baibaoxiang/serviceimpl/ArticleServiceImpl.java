package com.baibaoxiang.serviceimpl;
import com.baibaoxiang.dao.SearchDao;
import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.ArticleMapper;
import com.baibaoxiang.mapper.custom.ArticleMapperCustom;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.ArticleExample;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.tool.JsonUtils;
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
    @Autowired
    JedisClient jedisClient;
    @Autowired
    SearchDao searchDao;
    private String articleInfoKey = "Article_INFO";

    @Override
    public Article selectByPrimaryKey(Integer no) throws Exception {
        String key = articleInfoKey + ":" + no + ":" + "BASE";
        if (no!=null){
            if (jedisClient.exists(key)){
            String jsonString = jedisClient.get(key);
            return  JsonUtils.jsonToPojo(jsonString,Article.class);}
        }
        Article article = articleMapper.selectByPrimaryKey(no);
        jedisClient.set(key,JsonUtils.objectToJson(article));
        return article;
    }

    @Override
    public List<Article> selectByTypeArea(String type, String area) throws Exception {
        String key = articleInfoKey + ":" + type + ":" + area;
        if (jedisClient.exists(key)) {
            String jsonString = jedisClient.get(key);
            return JsonUtils.jsonToList(jsonString, Article.class);
        } else {
            List<Article> articles = articleMapperCustom.selectByTypeArea(type, area);
            jedisClient.set(key, JsonUtils.objectToJson(articles));
            return articles;
        }
    }

    @Override
    public List<Article> selectAllAticles() throws Exception {
        return articleMapperCustom.selectAllArticles();
    }

    @Override
    public int insertSelective(Article record) throws Exception {
        String key = articleInfoKey + ":" + record.getType() + ":" + record.getArea();
        String key2 = articleInfoKey + ":" + record.getArea();
        if (jedisClient.exists(key)) {
            jedisClient.del(key);
        }else if (jedisClient.exists(key2)){
            jedisClient.del(key2);
        }
        return articleMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer no) throws Exception {
        Article article = articleMapper.selectByPrimaryKey(no);
        String key = articleInfoKey + ":" + article.getType() + ":" + article.getArea();
        String key2 = articleInfoKey + ":" + no + ":" + "BASE";
        String key3 = articleInfoKey + ":" + article.getArea();
        delKey(key, key2, key3);
        return articleMapper.deleteByPrimaryKey(no);
    }

    /**
     * 抽取删除key的方法
     * @param key
     * @param key2
     * @param key3
     */
    private void delKey(String key, String key2, String key3) {
        if (jedisClient.exists(key)) {
            jedisClient.del(key);
        } else if (jedisClient.exists(key2)) {
            jedisClient.del(key2);
        } else if (jedisClient.exists(key3)) {
            jedisClient.del(key3);
        }
    }

    @Override
    public void deleteArticleBatch(Integer[] no) throws Exception {
        Article article;
        String key;
        String key2;
        String key3;
        for (int i = 0; i < no.length; i++) {
            article = articleMapper.selectByPrimaryKey(no[i]);
            key = articleInfoKey + ":" + article.getType() + ":" + article.getArea();
            key2 = articleInfoKey + articleInfoKey +  article.getArea();
            key3 = articleInfoKey + ":" + article.getNo() + ":" + "BASE";
            delKey(key,key2,key3);
            articleMapper.deleteByPrimaryKey(no[i]);
        }
    }

    @Override
    public int updateByPrimaryKey(Article record) throws Exception {
        String key = articleInfoKey + ":" + record.getType() + ":" + record.getArea();
        String key2 = articleInfoKey +  record.getArea();
        String key3 = articleInfoKey + ":" + record.getNo() + ":" + "BASE";
        delKey(key, key2, key3);
        searchDao.updateArticleById(record.getNo());
        return articleMapper.updateByPrimaryKey(record);
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