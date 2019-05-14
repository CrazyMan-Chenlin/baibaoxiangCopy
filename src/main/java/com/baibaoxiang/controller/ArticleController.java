package com.baibaoxiang.controller;

import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author sheng
 * @create 2019-04-26-09:59
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ManagerService managerService;

    /**
     * 按主键查询文章
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Article selectByPrimaryKey(@PathVariable("id") Integer id) throws Exception{
        Article article = articleService.selectByPrimaryKey(id);
        return article;
    }

    /**
     * 按地区，类型查询 并按置顶号，发布时间排序 （手机前端应用）
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/type_area",method = RequestMethod.POST)
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    public List<Article> selectAll() throws Exception{
        List<Article> articleList = articleService.selectAllAticles();

        return articleList;
    }

    /**
     * 添加文章
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public void insert(@RequestBody Article record) throws Exception {
       articleService.insertSelective(record);
    }

    /**
     * 删除文章
     * @param no
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{no}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteByPrimaryKey(@PathVariable("no") Integer no) throws Exception {
        articleService.deleteByPrimaryKey(no);
    }

    /** 批量删除文章
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "deleteBatch",method = RequestMethod.POST)
    public void deleteArticleBatch(HttpServletRequest request) throws Exception{
        String str = request.getParameter("ids");
        String arr[] = str.split(",");
        Integer ids [] = new Integer[arr.length];
        for(int i = 0; i < ids.length; i++){
            ids[i] = Integer.valueOf(arr[i]);
        }
        articleService.deleteArticleBatch(ids);
    }

    /**
     * 更新文章
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public void updateByPrimaryKey(@RequestBody Article record) throws Exception {
        articleService.updateByPrimaryKey(record);
    }
}
