package com.baibaoxiang.mapper.custom;

import com.baibaoxiang.po.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sheng
 * @create 2019-04-27-11:11
 */
public interface ArticleMapperCustom {


    /**
     * @param type
     * @param area
     * @return
     * @throws Exception
     */
    List<Article> selectByTypeArea(@Param("type") String type, @Param("area") String area) throws Exception;

    List<Article> selectAllArticles() throws Exception;

}
