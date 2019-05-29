package com.baibaoxiang.controller;

import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.ManagerService;
import com.baibaoxiang.tool.RandomValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @create 2019-04-29-10:08
 */
@Controller
@RequestMapping("/manager1")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    /**
     * 返回登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 获取生成验证码显示到 UI 界面
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/checkCode")
    public void checkCode(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");

        // 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            // 输出图片方法
            randomValidateCode.getRandcode(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 登录验证
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String validatecode = request.getParameter("validatecode");
        String rememberme = request.getParameter("rememberme");
        Manager manager = managerService.findManagerWithPassword_salt(username);

        //获取session中保存的 验证码
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute("RANDOMCODEKEY");

        if(!code.equals(validatecode)){
            map.put("code",0);
            map.put("msg","验证码有误！");
        }else{
            if(manager == null){
                map.put("code",0);
                map.put("msg","用户名无效！");
            }else if (!manager.getPassword().equals(md5(manager.getSalt(),password))){
                map.put("code",0);
                map.put("msg","密码出错!");
            }else{
                //登录成功
                map.put("code",1);
                map.put("msg","");
                //添加session 将用户名添加到session
                request.getSession().setAttribute("username", username);
                //添加cookie
                if(rememberme!=null) {
                    //创建两个Cookie对象
                    Cookie nameCookie = new Cookie("username", username);
                    //设置Cookie的有效期为3天
                    nameCookie.setMaxAge(60 * 60 * 24 * 3);
                    Cookie pwdCookie = new Cookie("password", password);
                    pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                    response.addCookie(nameCookie);
                    response.addCookie(pwdCookie);
                }
            }
        }

        return map;
    }


    /**
     * 退出登录
     *
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) throws Exception {
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:/login";
    }


    /**
     * 添加管理员
     * @param manager
     * @return true：添加成功 false: 添加失败
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addManager(@RequestBody Manager manager) throws Exception{
        System.out.println(manager.getUsername());
        String salt = getRandomSalt();
        manager.setTitle("BBBBB");
        manager.setPath("/img");
        String s = md5(salt, manager.getPassword());
        manager.setSalt(salt);
        manager.setPassword(s);
        int i = managerService.insert(manager);
        return i==1;
    }

    /** 完善/更新 管理员的昵称，头像信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateNamePicture", method = RequestMethod.PUT)
    public void updateNamePicture(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Manager manager = new Manager();
        String name = request.getParameter("name");
        String path = request.getParameter("path");
        manager.setUsername(username);
        manager.setName(name);
        manager.setPath(path);
        managerService.updateByPrimaryKeySelective(manager);

    }

    /** 更改密码
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updatepassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updatePassword(HttpServletRequest request) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>(16);
        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");
        String username = "liang123";
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        Manager manager = managerService.findManagerWithPassword_salt(username);
        String salt = manager.getSalt();
        if(!manager.getPassword().equals(md5(salt,oldPassword))){
            map.put("code",0);
            map.put("msg","原密码有误！");
        }else{
            //密码正确
            manager.setPassword(md5(salt,newPassword));
            managerService.updateByPrimaryKeySelective(manager);
            map.put("code",1);
            map.put("msg","密码更改成功！");
        }

        return map;
    }

    /**
     * 通过管理员名称 查询管理员信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/managerInfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findManagerByUsername(HttpServletRequest request) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>(16);
        //通过session 获取管理员名称 从而查询到 管理员
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        Manager manager = managerService.findManagerByUsername(username);
        map.put("username",manager.getUsername());
        map.put("name",manager.getName());
        map.put("area",manager.getArea());
        map.put("path",manager.getPath());
        return map;
    }

    /**
     * 通过管理员名称 查询管理员信息
     * @param username
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/username/{username}",method = RequestMethod.GET)
    @ResponseBody
    public Manager findManagerByUsername(@PathVariable String username) throws Exception{
        Manager manager = managerService.findManagerByUsername(username);
        return manager;
    }

    /**
     * 查询所有的地区管理员
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/title",method = RequestMethod.POST)
    @ResponseBody
    public List<Manager> findManagerByTitle(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String title = request.getParameter("title");
        System.out.println(title);
        List<Manager> managers = managerService.findManagersByTitle(title);
        return managers;
    }

    /**
     * 删除单个管理员 通过管理员名称删除
     * @param username
     * @throws Exception
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public void deleteManager(@PathVariable("username") String username) throws Exception{
        managerService.deleteByPrimaryKey(username);
    }

    /** 批量删除管理员
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/deleteBatch")
    public void deleteManagerBatch(HttpServletRequest request) throws Exception{
        String usernames = request.getParameter("usernames");
        managerService.deleteManagerBatch(usernames);
    }

    /**
     * 生成盐
     * @return
     */
    public static String getRandomSalt() {
        String model = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuffer salt = new StringBuffer();
        char[] m = model.toCharArray();
        for (int i = 0; i < 10; i++) {
            char c = m[(int) (Math.random() * 36)];
            salt = salt.append(c);
        }
        return salt.toString();
    }

    /**
     * 生成MD5 摘要
     * @param salt ：盐 message: 明文
     * @return
     */
    public static String md5(String salt, String message) {
        String plainText = message.concat(salt);
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeErrorException(null, "md5加密异常");
        }
        String md5codeString = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5codeString.length(); i++) {
            md5codeString = md5codeString.concat("0");
        }
        return md5codeString;
    }

}
