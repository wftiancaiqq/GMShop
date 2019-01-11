package cn.com.guimei.controller;

import cn.com.guimei.pojo.SuperUser;
import cn.com.guimei.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
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

    @RequestMapping("/superUserInfo")
    public String getSuperUserInfoById(String id,HttpServletRequest request){
        SuperUser superUser=adminService.personalInfo(id);
        request.getSession().setAttribute("superUser",superUser);
        return "updateSuperUser";
    }
    @RequestMapping("/updateSuperUser")
    public String updateSuperUser(MultipartFile userImageX, SuperUser superUser, HttpServletRequest request){
        String filePath=request.getRealPath("/static/back/Image");
        int i=adminService.updateSuperUser(userImageX,superUser,filePath);
        if (i==0){
            //将更新后的信息传回页面
            SuperUser superUserX=adminService.personalInfo(String.valueOf(superUser.getId()));
            request.getSession().setAttribute("superUser",superUserX);
            request.setAttribute("result","success");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateSuperUser.jsp";
        } else if(i==1){
            request.setAttribute("error","图像文件路径不存在！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateSuperUser.jsp";
        }else if(i==2){
            request.setAttribute("error","修改管理员失败！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateSuperUser.jsp";
        }else if(i==3){
            request.setAttribute("error","上传文件大小不能超过5MB！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateSuperUser.jsp";
        }else if(i==4){
            request.setAttribute("error","文件类型非要求的图像格式！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateSuperUser.jsp";
        }
        request.setAttribute("result","fail");
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateSuperUser.jsp";
    }
    @RequestMapping("/goIndex")
    public String goIndex(){
        return "index";
    }
}
