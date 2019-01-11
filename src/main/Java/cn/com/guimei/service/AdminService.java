package cn.com.guimei.service;

import cn.com.guimei.pojo.SuperUser;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {

    public SuperUser adminLogin(String userLoginName,String userPassword);
    public SuperUser personalInfo(String id);
    int updateSuperUser(MultipartFile file, SuperUser superUser, String filePath);
}
