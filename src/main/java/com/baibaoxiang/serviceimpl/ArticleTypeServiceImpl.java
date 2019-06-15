package com.baibaoxiang.serviceimpl;

import com.baibaoxiang.jedis.JedisClient;
import com.baibaoxiang.mapper.ArticleTypeMapper;
import com.baibaoxiang.mapper.custom.ArticleTypeMapperCustom;
import com.baibaoxiang.po.ArticleType;
import com.baibaoxiang.service.ArticleTypeService;
import com.baibaoxiang.tool.JsonUtils;
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
    @Autowired
    JedisClient jedisClient;
    private  String typeInfoKey ="Type_INFO";
    private String key = typeInfoKey + ":" + "TYPENAME";
    @Override
    public ArticleType selectByPrimaryKey(Integer id) throws Exception {
        return articleTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(ArticleType record) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        return articleTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        return articleTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ArticleType record) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        return articleTypeMapper.insert(record);
    }

    /**
     * 添加缓存机制
     * @return·
     * @throws Exception
     */
    @Override
    public List<ArticleType> selectArticleTypes() throws Exception {
        if (jedisClient.exists(key)){
            String jsonString = jedisClient.get(key);
            return JsonUtils.jsonToList(jsonString,ArticleType.class);
        }else{
            List<ArticleType> articleTypes = articleTypeMapperCustom.selectArticleTypes();
            jedisClient.set(key,JsonUtils.objectToJson(articleTypes));
            return articleTypes;
        }
    }

    @Override
    public List<ArticleType> selectArticleTypesSelective() throws Exception {
        return articleTypeMapperCustom.selectArticleTypesSelective();
    }

    @Override
    public ArticleType selectArticleTypeByType(String type) throws Exception {
        return articleTypeMapperCustom.selectArticleTypeByType(type);
    }

    @Override
    public void deleteByType(String type) throws Exception {
        if (jedisClient.exists(key)){
            jedisClient.del(key);
        }
        articleTypeMapperCustom.deleteByType(type);
    }
}
