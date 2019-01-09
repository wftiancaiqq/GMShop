package cn.com.guimei.controller;

import cn.com.guimei.pojo.SuperUser;
import cn.com.guimei.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;
    @RequestMapping("/login")
    public String adminLogin(String userLoginName, String userPassword, HttpServletRequest request){
        SuperUser superUser=adminService.adminLogin(userLoginName,userPassword);
        if (superUser!=null){
            request.getSession().setAttribute("superUser",superUser);
            return "/index";
        }else {
            request.setAttribute("error","用户名或密码输入错误！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/Login.jsp";
        }
    }

    @RequestMapping("/personalInfo")
    public String personalInfo(String id,HttpServletRequest request){
        SuperUser user=adminService.personalInfo(id);
        request.setAttribute("user",user);
        return "userPersonInfo";
    }
}
