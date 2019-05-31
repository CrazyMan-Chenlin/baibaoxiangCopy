package com.baibaoxiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author:joe
 * @create：2019/5/30
 */
@Controller
@RequestMapping("/jsp")
public class JspController {
    @RequestMapping(value = "/left")
    public ModelAndView left(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/left");
        return modelAndView;
    }

    @RequestMapping(value = "/top")
    public ModelAndView top(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/top");
        return modelAndView;
    }

    @RequestMapping(value = "/main")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/main");
        return modelAndView;
    }

    @RequestMapping(value = "/rights_management")
    public ModelAndView rights_management(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/rights_management");
        return modelAndView;
    }


    @RequestMapping(value = "/article")
    public ModelAndView atricle(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/article");
        return modelAndView;
    }

    @RequestMapping(value = "/school")
    public ModelAndView school(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/school");
        return modelAndView;
    }

    @RequestMapping(value = "/editpwd")
    public ModelAndView editpwd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/editpwd");
        return modelAndView;
    }


    @RequestMapping(value = "/cancellation")
    public ModelAndView cancellation(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/cancellation");
        return modelAndView;
    }

    @RequestMapping(value = "/personal_Information")
    public ModelAndView personal_Information(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/personal_Information");
        return modelAndView;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/edit");
        return modelAndView;
    }

}
