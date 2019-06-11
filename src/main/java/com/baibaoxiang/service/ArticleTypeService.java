package com.baibaoxiang.service;

import com.baibaoxiang.po.ArticleType;

import java.util.List;

/** 文章类型 Service
 * @author sheng
 * @create 2019-05-03-11:08
 */
public interface ArticleTypeService {


    /** 查询 某文章类型
     * @param id
     * @return
     * @throws Exception
     */
    ArticleType selectByPrimaryKey(Integer id) throws Exception;

    /** 更新 某文章类型
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKey(ArticleType record)throws Exception;

    /** 删除 某文章类型
     * @param id
     * @return
     * @throws Exception
     */
    int deleteByPrimaryKey(Integer id)throws Exception;

    /** 添加 某文章类型
     * @param record
     * @return
     * @throws Exception
     */
    int insert(ArticleType record)throws Exception;

    /** 查询所有的文章类型
     * @return
     * @throws Exception
     */
    List<ArticleType> selectArticleTypes()throws Exception;

    /** 查询除了"推荐"外 的所有其他文章类型
     * @return ArticleType列表
     * @throws Exception
     */
    List<ArticleType> selectArticleTypesSelective() throws Exception;

    /** 通过文章类型 查询
     * @param type
     * @return
     * @throws Exception
     */
    ArticleType selectArticleTypeByType(String type) throws Exception;

    /** 通过文章的类型 删除
     * @throws Exception
     */
    void deleteByType(String type) throws Exception;
}
