package com._520it.crm.web.controller;

import com._520it.crm.domain.Menu;
import com._520it.crm.service.IMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private IMenuService service;

    @RequestMapping("/index")
    public  String index(HttpServletRequest req) {
        //获取当前登录的员工对象
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        List<Menu> menuList = service.getRootMenu();
        req.setAttribute("menuList", menuList);
        return "index";
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest req) {
        //获取用户认证结果
        Subject subject = SecurityUtils.getSubject();
        System.out.println("认证结果:" + subject.isAuthenticated());

        //获取认证失败的异常信息
        String failure = (String) req.getAttribute("shiroLoginFailure");
        System.out.println(failure);

        //根据不同的异常信息来提示不同的错误文字信息
        if (UnknownAccountException.class.getName().equals(failure)) {
            req.setAttribute("errorMsg", "账号不存在!");
        } else if (IncorrectCredentialsException.class.getName().equals(failure)) {
            req.setAttribute("errorMsg", "密码错误!");
        } else {
            //req.setAttribute("errorMsg", "网络繁忙,请稍后再试!");
        }
        //请求转发到登录页面
        return "forward:login.jsp";
    }


}
