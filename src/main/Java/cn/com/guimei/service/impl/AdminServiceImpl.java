package cn.com.guimei.service.impl;

import cn.com.guimei.dao.AdminMapper;
import cn.com.guimei.pojo.SuperUser;
import cn.com.guimei.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    public SuperUser adminLogin(String userLoginName, String userPassword) {
        return adminMapper.adminLogin(userLoginName,userPassword);
    }

    public SuperUser personalInfo(String id) {
        return adminMapper.personalInfo(Integer.parseInt(id));
    }
}
