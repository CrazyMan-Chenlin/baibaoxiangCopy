package com.baibaoxiang.controller;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.ArticleType;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.ArticleTypeService;
import com.baibaoxiang.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * @author chenlin
 */
@Controller
public class IndexController {
    @Autowired
    SchoolService schoolService;
    @Autowired
    ArticleTypeService articleTypeService;
    @Autowired
    ArticleService articleService;
    /**
     * 展示首页
     * @return
     */
    @RequestMapping("/")
    public ModelAndView showIndex() throws Exception {
        List<String> schoolName = schoolService.selectDifferentSchoolName();
        ModelAndView model = new ModelAndView("/index");
        model.addObject("schoolName",schoolName);
        List<String> areaName = schoolService.selectSchoolArea("广东第二师范学院");
        model.addObject("areaName",areaName);
        List<ArticleType> articleTypeList = articleTypeService.selectArticleTypes();
        model.addObject("articleTypeList",articleTypeList);
        List<Article> articleList = articleService.selectTopArticle("广东第二师范学院花都校区");
        model.addObject("articleList",articleList);
        return model;
    }

    /**
     * 查询地区名
     * @param schoolName
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index/queryAreaName",method=RequestMethod.POST)
    @ResponseBody
    public List<String> queryAreaName(String schoolName,String type) throws Exception{
       return schoolService.selectSchoolArea(schoolName);
    }

    /**
     * 得到地区文章
     * @param area
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index/getAreaArticle",method= RequestMethod.POST)
    @ResponseBody
    public List<Article> getAreaArticle(String area,String type) throws Exception {
        String str = "推荐";
        if (str.equals(type)){
            return articleService.selectTopArticle(area);
        }
        return articleService.selectByTypeArea(type,area);
    }

    /**
     * 改变地区时，文章相应改变
     * @param area
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index/changeAreaArticle",method= RequestMethod.POST)
    @ResponseBody
    public List<Article> changeAreaArticle(String area,String type) throws  Exception{
        String str = "推荐";
        if (str.equals(type)){
            return articleService.selectTopArticle(area);
        }
        return articleService.selectByTypeArea(type,area);
    }
 }
