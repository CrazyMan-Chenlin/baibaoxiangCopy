package com.baibaoxiang.controller;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
/**
 * @author chenlin
 */
@Controller
public class SearchController {
    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String showSearch() throws Exception {
        return "search";
    }

    @RequestMapping(value = "/search/importAllIndex", method = RequestMethod.POST)
    @ResponseBody
    public int importAllIndex() {
        try {
            searchService.importAllIndex();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @RequestMapping(value = "/search/deleteAllIndex", method = RequestMethod.POST)
    @ResponseBody
    public int deleteAllIndex() {
        try {
            searchService.deleteAllIndex();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @RequestMapping(value = "/search/searchSomething", method = RequestMethod.POST)
    @ResponseBody
    public List<Article> search(@RequestParam("query") String query) throws Exception {
        return searchService.searchIndex(query);
    }
}
