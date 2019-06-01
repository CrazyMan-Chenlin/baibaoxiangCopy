package com.baibaoxiang.controller;

import com.baibaoxiang.po.ArticleType;
import com.baibaoxiang.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sheng
 * @create 2019-05-03-11:32
 */
@Controller
@RequestMapping(value = "/articleType")
public class ArticleTypeController {

    @Autowired
    ArticleTypeService articleTypeService;

    /**
     * 查询 某个articleType
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ArticleType findArticleTypeById(@PathVariable Integer id) throws Exception{
        ArticleType articleType = articleTypeService.selectByPrimaryKey(id);
        return articleType;
    }

    /**
     * 查询所有的 文章类型
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public List<ArticleType> findArticleTypes() throws Exception{
        List<ArticleType> articleTypes = articleTypeService.selectArticleTypes();
        return articleTypes;
    }

    /**
     * 添加类型
     * @param articleType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public int addArticleType(@RequestBody ArticleType articleType) throws Exception{
        int i = articleTypeService.insert(articleType);
        return i;
    }

    /**
     * 修改 某文章类型
     * @param articleType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public int updateArticleType(@RequestBody ArticleType articleType) throws Exception{
        int i = articleTypeService.updateByPrimaryKey(articleType);
        return i;
    }

    /**
     * 删除 某文章类型
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteArticleType(@PathVariable Integer id) throws Exception{
        int i = articleTypeService.deleteByPrimaryKey(id);
        return i;
    }
}
