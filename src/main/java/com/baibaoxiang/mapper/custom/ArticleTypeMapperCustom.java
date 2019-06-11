package com.baibaoxiang.mapper.custom;

import com.baibaoxiang.po.ArticleType;

import java.util.List;

/** ArticleTypeMapper的扩展接口
 * @author sheng
 * @create 2019-05-03-11:15
 */
public interface ArticleTypeMapperCustom {

    /** 查询所有的 文章类型
     * @return
     */
    List<ArticleType> selectArticleTypes();

    /** 查询除了"推荐"外 的所有其他文章类型
     * @return
     */
    List<ArticleType> selectArticleTypesSelective();

    /**
     * 通过文章类型名称删除
     */
    void deleteByType(String type);

    /** 通过文章类型 查询
     * @param type
     * @return
     */
    ArticleType selectArticleTypeByType(String type);
}
