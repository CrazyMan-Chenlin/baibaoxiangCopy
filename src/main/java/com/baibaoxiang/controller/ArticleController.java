package com.baibaoxiang.controller;

import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.ManagerService;
import com.baibaoxiang.service.RedisService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @author sheng
 * @create 2019-04-26-09:59
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ManagerService managerService;

    @Autowired
    RedisService redisService;


    /**
     * 按主键查询文章
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Article selectByPrimaryKey(@PathVariable("id") String id) throws Exception{
        Article article = articleService.selectByPrimaryKey(id);
        redisService.saveReadNumRedis(id);
        return article;
    }
//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//    public  selectById(@PathVariable("id") String id) throws Exception{
//        Article article = articleService.selectByPrimaryKey(id);
//        redisService.saveReadNumRedis(id);
//        return article;
//    }

    /**
     * 按地区，类型查询 并按置顶号，发布时间排序 （手机前端应用）
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/type_area",method = RequestMethod.POST)
    public  List<Article> selectByTypeArea(HttpServletRequest request) throws Exception {
        String type = request.getParameter("type");
        String area = request.getParameter("area");
        List<Article> articleList = articleService.selectByTypeArea(type, area);

        return articleList;
    }

    /** 按地区，类型查询 并按置顶号，发布时间排序(后台地区管理员应用)
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/type",method = RequestMethod.POST)
    public  List<Article> selectByType(HttpServletRequest request) throws Exception {
        String type = request.getParameter("type");
//        System.out.println(type);
        //获取session 中的username
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        Manager manager = managerService.findManagerByUsername("chen123");
        String area = manager.getArea();
        List<Article> articleList = articleService.selectByTypeArea(type, area);
        return articleList;
    }

    /** 查询所有的文章
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "allArticles",method = RequestMethod.GET)
    public List<Article> selectAll() throws Exception{
        List<Article> articleList = articleService.selectAllArticles();

        return articleList;
    }

    /**
     * 添加文章
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public void insert(@RequestBody Article record) throws Exception {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        record.setNo(uuid);
        articleService.insertSelective(record);
    }

    /**
     * 删除文章
     * @param no
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{no}",method = RequestMethod.DELETE)
    public void deleteByPrimaryKey(@PathVariable("no") String no) throws Exception {
        articleService.deleteByPrimaryKey(no);
    }

    /** 批量删除文章
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "deleteBatch",method = RequestMethod.POST)
    public void deleteArticleBatch(HttpServletRequest request) throws Exception{
        String str = request.getParameter("ids");
        String []ids= str.split(",");
        articleService.deleteArticleBatch(ids);
    }

    /**
     * 更新文章
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public void updateByPrimaryKey(@RequestBody Article record) throws Exception {
        articleService.updateByPrimaryKey(record);
    }

    /** 点赞行为
     * @param no
     * @throws Exception
     */
    @RequestMapping(value = "like/{no}", method = RequestMethod.GET)
    public void onclickLike(@PathVariable("no") String no) throws Exception{
       redisService.saveLikeNumRedis(no);
    }
}
