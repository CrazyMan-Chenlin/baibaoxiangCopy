package com.baibaoxiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chenlin
 */
@Controller
public class SearchController {
    @RequestMapping(value = "/search",method= RequestMethod.GET)
    public String showSearch() throws Exception {
        return "search";
    }
}
