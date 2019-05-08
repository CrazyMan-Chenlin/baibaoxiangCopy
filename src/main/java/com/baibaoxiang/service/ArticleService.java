package com.baibaoxiang.service;


import com.baibaoxiang.po.Article;

import java.util.List;

/**
 * @author sheng
 * @create 2019-04-23-00:18
 */
public interface ArticleService {


    /**
     * 通过主键(no)查询文章
     * @param no
     * @return Article
     * @throws Exception
     */
    Article selectByPrimaryKey(Integer no) throws Exception;


    /**
     * 按文章的类型(type），地区(area)查询文章
     * 顺序依据:置顶号（号数低的在前面），文章发表时间（最近发表的在前面）
     * @param type,area
     * @return 返回一个Article类型的链表
     * @throws Exception
     */
    List<Article> selectByTypeArea(String type, String area) throws Exception;


    /** 查询所有的推文 置顶/发布时间 排序
     * @return
     * @throws Exception
     */
    List<Article> selectAllAticles() throws Exception;

    /**
     * 添加文章
     * @param record
     * @return int
     * @throws Exception
     */
    int insert(Article record) throws Exception;


    /**
     * 删除文章
     * @param no
     * @return
     * @throws Exception
     */
    int deleteByPrimaryKey(Integer no) throws Exception;

    /** 批量删除文章
     * @param no
     * @throws Exception
     */
    void deleteArticleBatch(Integer no[]) throws Exception;


    /**
     * 修改文章
     * @param record
     * @return
     * @throws Exception
     */
    int updateByPrimaryKey(Article record) throws Exception;


}


