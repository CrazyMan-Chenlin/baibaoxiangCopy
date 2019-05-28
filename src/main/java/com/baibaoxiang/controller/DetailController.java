package com.baibaoxiang.controller;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenlin
 */
@Controller
public class DetailController {
    @Autowired
    ArticleService articleService;
    @RequestMapping(value = "/detail",method= RequestMethod.GET)
    public ModelAndView showDetail(String no) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/detail");
        //根据id查文章的具体内容
        Article article = articleService.selectByPrimaryKey(no);
        /**/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timeFormat = sdf.format(article.getCreateTime());
        modelAndView.addObject("article",article);
        modelAndView.addObject("timeFormat",timeFormat);
        return modelAndView;
    }
}
