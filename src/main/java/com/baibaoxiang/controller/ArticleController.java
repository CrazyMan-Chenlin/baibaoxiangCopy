package com.baibaoxiang.controller;

import com.baibaoxiang.po.Article;
import com.baibaoxiang.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 按主键查询文章
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ModelAndView selectByPrimaryKey(@PathVariable("id") Integer id) throws Exception{
        Article article = articleService.selectByPrimaryKey(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("article",article);
        modelAndView.setViewName("backstage/articledetail");
        return modelAndView;
    }

    /**
     * 按地区，类型查询 并按置顶号，发布时间排序
     * @param type
     * @param area
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{type}/{area}",method = RequestMethod.GET)
    @ResponseBody
    public  List<Article> selectByTypeArea(@PathVariable String type,@PathVariable String area) throws Exception {
        type = new String(type.getBytes("ISO-8859-1"), "utf8");
        area = new String(area.getBytes("ISO-8859-1"), "utf8");
        List<Article> articleList = articleService.selectByTypeArea(type, area);

        return articleList;
    }

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
       articleService.insert(record);
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
    @RequestMapping(value = "deleteBatch")
    public void deleteArticleBatch(HttpServletRequest request) throws Exception{
        String str = request.getParameter("ids");
        String arr[] = str.split(",");
        Integer ids [] = new Integer[arr.length];
        for(int i = 0; i < ids.length; i++){
            ids[i] = Integer.valueOf(arr[i]);
        }
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
