package com.baibaoxiang.controller;

import com.baibaoxiang.po.School;
import com.baibaoxiang.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @create 2019-05-09-15:28
 */
@Controller
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;
    private String defSchoolName = "广东第二师范学院";

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
    @ResponseBody
    public Map<String,Object> deleteSchool(@PathVariable("id") Integer id) throws Exception{
        Map<String,Object> map = new HashMap<String,Object>(16);
        List<Integer> nos = schoolService.selectNosBySchoolName(defSchoolName);
        for(Integer no : nos){
            if(no.equals(id)){
                map.put("code",0);
                map.put("msg","广东第二师范学院为默认保留学校，不可删除");
                return map;
            }
        }
        schoolService.deleteSchool(id);
        map.put("code", 1);
        map.put("msg", "删除成功");
        return map;
    }

    /** 批量删除学校
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "deleteBatch",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteSchoolBatch(HttpServletRequest request) throws Exception{
        Map<String,Object> map = new HashMap<String,Object>(16);
        List<Integer> nos = schoolService.selectNosBySchoolName(defSchoolName);
        //接受前端传来的 ids字符串  将ids拆分成数组
        String str = request.getParameter("ids");
        //将ids拆分成数组
        String arr[] = str.split(",");
        Integer ids [] = new Integer[arr.length];
        for(int i = 0; i < ids.length; i++){
            ids[i] = Integer.valueOf(arr[i]);
            for(Integer no : nos){
                if(no.equals(ids[i])){
                    map.put("code",0);
                    map.put("msg","广东第二师范学院为默认保留学校，不可删除");
                    return map;
                }
            }
        }
        schoolService.deleteSchoolBatch(ids);
        map.put("code", 1);
        map.put("msg", "删除成功");
        return map;
    }

    @RequestMapping(value = "deleteSchoolBySchoolName",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteSchoolBySchoolName(HttpServletRequest request) throws Exception{
        String name = request.getParameter("schoolName");
        Map<String,Object> map = new HashMap<String,Object>(16);
        if(name.equals(defSchoolName)){
            map.put("code", 0);
            map.put("msg", "广东第二师范学院为默认保留学校，不可删除");
        }else{
            schoolService.deleteSchoolBySchoolName(name);
            map.put("code", 1);
            map.put("msg", "删除成功");
        }
        return map;
    }

}
