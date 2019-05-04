package com.baibaoxiang.controller;

import com.baibaoxiang.po.Article;
import com.baibaoxiang.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        modelAndView.setViewName("articledetail");
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
    public List<Article> selectByTypeArea(@PathVariable String type,@PathVariable String area) throws Exception {
        List<Article> articles = articleService.selectByTypeArea(type, area);
        return articles;
    }

    /**
     * 添加文章
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public int insert(@RequestBody Article record) throws Exception {
        int i = articleService.insert(record);
        return i;
    }

    /**
     * 删除文章
     * @param no
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{no}",method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteByPrimaryKey(@PathVariable("no") Integer no) throws Exception {
        int i = articleService.deleteByPrimaryKey(no);
        return i;
    }

    /**
     * 更新文章
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public int updateByPrimaryKey(@RequestBody Article record) throws Exception {
        int i = articleService.updateByPrimaryKey(record);
        return i;
    }
}
