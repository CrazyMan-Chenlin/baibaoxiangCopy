package com.baibaoxiang.serviceimpl;
import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.ArticleMapper;
import com.baibaoxiang.mapper.custom.ArticleMapperCustom;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.ArticleExample;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.tool.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author sheng
 * @create 2019-04-23-00:19
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleMapperCustom articleMapperCustom;
    @Autowired
    JedisClient jedisClient;

    private String articleInfoKey = "Article_INFO";

    @Override
    public Article selectByPrimaryKey(Integer no) throws Exception {
        Article article = articleMapper.selectByPrimaryKey(no);
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
        if (jedisClient.exists(key)) {
            jedisClient.del(key);
        }
        return articleMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer no) throws Exception {
        Article article = articleMapper.selectByPrimaryKey(no);
        String key = articleInfoKey + ":" + article.getType() + ":" + article.getArea();
        if (jedisClient.exists(key)) {
            jedisClient.del(key);
        }
        return articleMapper.deleteByPrimaryKey(no);

    }

    @Override
    public void deleteArticleBatch(Integer[] no) throws Exception {
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
        String key = articleInfoKey + ":" + record.getType() + ":" + record.getArea();
        String key2 = articleInfoKey +  record.getArea();
        if (jedisClient.exists(key)) {
            jedisClient.del(key);
            jedisClient.del(key2);
        }
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
