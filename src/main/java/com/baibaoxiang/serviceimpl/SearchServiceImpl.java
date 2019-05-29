package com.baibaoxiang.serviceimpl;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author chenlin
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    ArticleService articleService;
    @Autowired
    HttpSolrClient httpSolrClient;

    @Override
    public List<Article> searchIndex(String queryString) throws Exception {
        //1.定义一个搜索对象
        SolrQuery solrQuery = new SolrQuery();
        //2.设置查询条件
        solrQuery.setQuery(queryString);
        solrQuery.set("df", "article_keywords");
        //1.查询索引库
        QueryResponse response = httpSolrClient.query(solrQuery);
        //2.获取文章列表
        SolrDocumentList results = response.getResults();
        //取高亮
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        //3.封装商品列表
        List<Article> searchArticle = new ArrayList<>();
        Article article ;
        for (SolrDocument solrDocument : results){
            article = new Article();
            article.setNo( solrDocument.get("id").toString());
            List<String> list = highlighting.get(solrDocument.get("id")).get("title");
            String hightLight;
            if (list !=null && list.size() > 0){
                hightLight = list.get(0);
            }else{
                hightLight = solrDocument.get("title").toString();
            }
            article.setTitle(hightLight);
            article.setAuthor(solrDocument.get("author").toString());
            article.setCreateTime(Date.valueOf(solrDocument.get("create_time").toString()));
            article.setLikeNum(Integer.parseInt(solrDocument.get("like_num").toString()));
            article.setType((String)solrDocument.get("type"));
            searchArticle.add(article);
        }
       return searchArticle;
    }

    @Override
    public void deleteIndex(String id) throws Exception {
            httpSolrClient.deleteById(id) ;
            httpSolrClient.commit();
    }

    @Override
    public void deleteAllIndex() throws Exception {
        httpSolrClient.deleteByQuery("*:*");
        httpSolrClient.commit();
    }

    @Override
    public void importAllIndex() throws Exception {
        List<Article> articles = articleService.selectAllArticles();
        SolrInputDocument document;
        for (Article article : articles) {
            document = new SolrInputDocument();
            document.addField("title", article.getTitle());
            document.addField("message", article.getMessage());
            document.addField("id", article.getNo());
            document.addField("create_time", article.getCreateTime());
            document.addField("like_num", article.getLikeNum());
            document.addField("type", article.getType());
            document.addField("author", article.getAuthor());
            httpSolrClient.add(document);
        }
        httpSolrClient.commit();
    }

    @Override
    public void addIndex(Article article) throws Exception {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("title", article.getTitle());
        document.addField("message", article.getMessage());
        document.addField("id", article.getNo());
        document.addField("create_time", article.getCreateTime());
        document.addField("like_num", article.getLikeNum());
        document.addField("type", article.getType());
        document.addField("author", article.getAuthor());
        httpSolrClient.add(document);
        httpSolrClient.commit();
    }
}
