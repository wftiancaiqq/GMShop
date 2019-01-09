package cn.com.guimei.service;

import cn.com.guimei.pojo.SuperUser;

public interface AdminService {

    public SuperUser adminLogin(String userLoginName,String userPassword);
    public SuperUser personalInfo(String id);
}
