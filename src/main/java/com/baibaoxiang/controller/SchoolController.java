package com.baibaoxiang.controller;

import com.baibaoxiang.po.School;
import com.baibaoxiang.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author sheng
 * @create 2019-05-09-15:28
 */
@Controller
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    /** 通过id 查询学校信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public School findSchoolById(@PathVariable("id") Integer id) throws Exception{
        return schoolService.selectSchoolByNo(id);

    }

    /** 查询所有的学校信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/allSchool",method = RequestMethod.GET)
    @ResponseBody
    public List<School> findAllSchool() throws Exception{
        return  schoolService.selectAllSchool();

    }


    /**
     * 添加学校
     * @param school
     * @throws Exception
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addSchool(@RequestBody School school) throws Exception{
        schoolService.insertSchool(school);
    }

    /** 删除学校
     * @param id
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSchool(@PathVariable("id") Integer id) throws Exception{
        schoolService.deleteSchool(id);
    }

    /** 批量删除学校
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "deleteBatch",method = RequestMethod.POST)
    public void deleteSchoolBatch(HttpServletRequest request) throws Exception{
        //接受前端传来的 ids字符串  将ids拆分成数组
        String str = request.getParameter("ids");
        //将ids拆分成数组
        String arr[] = str.split(",");
        Integer ids [] = new Integer[arr.length];
        for(int i = 0; i < ids.length; i++){
            ids[i] = Integer.valueOf(arr[i]);
        }
        schoolService.deleteSchoolBatch(ids);
    }

}
