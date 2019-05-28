package com.baibaoxiang.serviceimpl;
import com.baibaoxiang.dao.SearchDao;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenlin
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    ArticleService articleService;
    @Autowired
    HttpSolrClient httpSolrClient;
    @Autowired
    SearchDao searchDao;
    @Override
    public void importAllArticles() throws Exception {
        List<Article> articles = articleService.selectAllAticles();
        SolrInputDocument document ;
        for(Article article :articles){
            document =  new SolrInputDocument();
            document.addField("title",article.getTitle());
            document.addField("message",article.getMessage());
            document.addField("no",article.getNo());
            document.addField("create_time",article.getCreateTime());
            document.addField("like_num",article.getLikeNum());
            document.addField("type",article.getType());
            document.addField("author",article.getAuthor());
            httpSolrClient.add(document);
        }
        httpSolrClient.commit();
        System.out.println("提交成功！");
    }

    @Override
    public void search(String queryString) throws Exception {
        //1.定义一个搜索对象
        SolrQuery solrQuery = new SolrQuery();
        //2.设置查询条件
        solrQuery.setQuery(queryString);
        solrQuery.set("df","article_keywords");
        searchDao.search(solrQuery);
    }
}
