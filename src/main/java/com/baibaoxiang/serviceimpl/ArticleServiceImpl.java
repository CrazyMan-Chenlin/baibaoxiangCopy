package com.baibaoxiang.serviceimpl;
import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.ArticleMapper;
import com.baibaoxiang.mapper.custom.ArticleMapperCustom;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.ArticleExample;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.SearchService;
import com.baibaoxiang.tool.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.lang.*;
import java.util.Set;

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
    SearchService searchService;
    private String articleInfoKey = "Article_INFO";
    @Override
    public Article selectByPrimaryKey(String no) throws Exception {
        System.out.println(no);
        String key = articleInfoKey + ":" + no + ":" + "BASE";
        if (no!=null){
            if (jedisClient.exists(key)){
                String jsonString = jedisClient.get(key);
                return  JsonUtils.jsonToPojo(jsonString,Article.class);
            }
        }
        Article article = articleMapper.selectByPrimaryKey(no);
        if (article!=null) {
            jedisClient.set(key, JsonUtils.objectToJson(article));
            jedisClient.expire(key, 60 * 60);
        }
        return article;
}

    @Override
    public List<String> selectNoByCreateTime(Date startTime, Date endTime) throws Exception {
        return articleMapperCustom.selectNoByCreateTime(startTime,endTime);
    }

    @Override
    public List<Article> selectByTypeArea(Integer areaNo, Integer typeNo) throws Exception {
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("create_time  desc");
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andAreanoEqualTo(areaNo);
        criteria.andTypenoEqualTo(typeNo);
        return articleMapper.selectByExample(articleExample);
    }

    @Override
    public List<Article> selectByTypeArea2(Integer typeNo, Integer areaNo,Integer page,Integer rows) throws Exception {
        //设置分页信息
        if (page==null||page==0) { page = 1; }
        if (rows==null||rows==0) { rows = 20 ;}
        PageHelper.startPage(page,rows);
        String key = articleInfoKey + ":" + typeNo + ":" + areaNo;
        if (jedisClient.hexists(key,page.toString())){
            String jsonString = jedisClient.hget(key,page.toString());
            return JsonUtils.jsonToList(jsonString,Article.class);
        }
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("create_time  desc");
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andAreanoEqualTo(areaNo);
        criteria.andTypenoEqualTo(typeNo);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        jedisClient.hset(key,page.toString(),JsonUtils.objectToJson(articles));
        jedisClient.expire(key,60*60*6);
        return pageInfo.getList();
    }

    @Override
    public List<Article> selectAllArticles() throws Exception {
        return articleMapperCustom.selectAllArticles();
    }

    @Override
    public List<Article> selectByType(Integer typeNo) throws Exception {
        return articleMapperCustom.selectByType(typeNo);
    }

    @Override
    public int insertSelective(Article record) throws Exception {
        String key = articleInfoKey + ":" + record.getArticleType().getType() + ":" + record.getArea().getSchool().getName()+":"+record.getArea().getName();
        String key2 = articleInfoKey + ":" + record.getArea().getSchool().getName()+":"+record.getArea().getName();

        Set<String> hkeys = jedisClient.hkeys(key);
        Iterator it = hkeys.iterator();
        while(it.hasNext()){
            String keyStr = (String)it.next();
            jedisClient.hdel(key,keyStr);
        }
         hkeys = jedisClient.hkeys(key2);
         it = hkeys.iterator();
        while(it.hasNext()){
            String keyStr = (String)it.next();
            jedisClient.hdel(key2,keyStr);
        }
        //添加索引
        searchService.addIndex(record);
        return articleMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String no) throws Exception {
        Article article = articleMapper.selectByPrimaryKey(no);
        String key = articleInfoKey + ":" + article.getArticleType().getType() + ":" + article.getArea().getSchool().getName()+":"+article.getArea().getName();
        String key2 = articleInfoKey + ":" + article.getArea().getSchool().getName()+":"+ article.getArea().getName();
        String key3 = articleInfoKey + ":" + no + ":" + "BASE";
        delKey(key, key2, key3);
        //删除索引
        searchService.deleteIndex(no);
        return articleMapper.deleteByPrimaryKey(no);
    }

    /**
     * 抽取删除key的方法
     * @param topKey
     * @param typeKey
     * @param noKey
     */
    private void delKey(String topKey, String typeKey, String noKey) {
        Set<String> hkeys = jedisClient.hkeys(topKey);
        Iterator it = hkeys.iterator();
        while(it.hasNext()){
            String keyStr = (String)it.next();
            jedisClient.hdel(topKey,keyStr);
        }
        if (jedisClient.exists(noKey)) {
            jedisClient.del(noKey);
        }
         hkeys = jedisClient.hkeys(typeKey);
         it = hkeys.iterator();
        while(it.hasNext()){
            String keyStr = (String)it.next();
            jedisClient.hdel(typeKey,keyStr);
            }
    }

    @Override
    public void deleteArticleBatch(String[] no) throws Exception {
        Article article;
        String key;
        String key2;
        String key3;
        for (int i = 0; i < no.length; i++) {
            article = articleMapper.selectByPrimaryKey(no[i]);
            key = articleInfoKey + ":" + article.getArticleType().getType() + ":" + article.getArea().getSchool().getName()+":"+article.getArea().getName();
            if (jedisClient.exists(key)) {
                jedisClient.del(key);
            }
            key2 = articleInfoKey + articleInfoKey +  article.getArea().getSchool().getName()+":"+article.getArea().getName();
            key3 = articleInfoKey + ":" + article.getNo() + ":" + "BASE";
            delKey(key,key2,key3);
            articleMapper.deleteByPrimaryKey(no[i]);
            //变量删除索引
            searchService.deleteIndex(no[i]);
        }
    }

    @Override
    public int updateByPrimaryKey(Article record) throws Exception {
        String key = articleInfoKey + ":" + record.getArticleType().getType() + ":" + record.getArea().getSchool().getName()+":"+record.getArea().getName();
        String key2 = articleInfoKey + ":" + record.getArea().getSchool().getName()+":"+record.getArea().getName();
        String key3 = articleInfoKey + ":" + record.getNo() + ":" + "BASE";
        delKey(key, key2, key3);
        //修改索引
        searchService.deleteIndex(record.getNo());
        searchService.addIndex(record);
        return articleMapper.updateByPrimaryKeySelective(record);
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
     * @param areaNo
     * @return
     * @throws Exception
     */
    @Override
    public List<Article> selectTopArticle(Integer areaNo,Integer page, Integer rows) throws Exception {
        //设置分页信息
        if (page==null||page==0) { page = 1; }
        if (rows==null||rows==0) {rows = 20 ;}
        PageHelper.startPage(page,rows);
        String key = articleInfoKey + ":" + areaNo;
        if (jedisClient.hexists(key,page.toString())) {
            String jsonString = jedisClient.hget(key,page.toString());
            return JsonUtils.jsonToList(jsonString, Article.class);
        } else {
            ArticleExample example = new ArticleExample();
            example.setOrderByClause("top");
            ArticleExample.Criteria criteria = example.createCriteria();
            criteria.andTopNotEqualTo(4);
            criteria.andAreanoEqualTo(areaNo);
            List<Article> articles = articleMapper.selectByExample(example);
            //获取分页信息
            PageInfo<Article> pageInfo = new PageInfo<>(articles);
            jedisClient.hset(key, page.toString(),JsonUtils.objectToJson(articles));
            jedisClient.expire(key,60*60*6);
            return pageInfo.getList();
        }
    }
    /** 设置 置顶文章
     * @param no
     * @param top
     * @throws Exception
     */
    @Override
    public void setTopArticle(String no, Integer top) throws Exception {
        Article article = articleMapper.selectByPrimaryKey(no);
        String key = articleInfoKey + ":" + article.getArea().getSchool().getName()+":"+article.getArea().getName();
        Set<String> hkeys = jedisClient.hkeys(key);
        Iterator it = hkeys.iterator();
        while(it.hasNext()){
            String keyStr = (String)it.next();
            jedisClient.hdel(key,keyStr);
        }
        articleMapperCustom.setTopArticle(no,top);
    }
}
