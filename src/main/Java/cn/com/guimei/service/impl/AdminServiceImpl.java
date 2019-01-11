package cn.com.guimei.service.impl;

import cn.com.guimei.dao.AdminMapper;
import cn.com.guimei.pojo.SuperUser;
import cn.com.guimei.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

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

    public int updateSuperUser(MultipartFile userImage, SuperUser superUser,String filePath) {
        //判断filePath路径是否真实存在
        File file = new File(filePath);
        if (file.exists()){
            //获取文件名称
            String userImageName=userImage.getOriginalFilename();
            //获取文件大小
            long fileSize=userImage.getSize();
            if (userImageName.toLowerCase().endsWith(".gif")||
                    userImageName.toLowerCase().endsWith(".bmp")||
                    userImageName.toLowerCase().endsWith(".jpeg")||
                    userImageName.toLowerCase().endsWith(".icon")||
                    userImageName.toLowerCase().endsWith(".jpg")){
                if (fileSize<=1024*1024*5){
                    try {
                        //执行写入操作
                        userImage.transferTo(new File(file,"/"+userImageName));
                        //执行数据库写入操作
                        superUser.setUserImage(userImageName);
                        int i=adminMapper.updateSuperUser(superUser);
                        if (i>0){
                            //写入成功
                            return 0;
                        }else {
                            //写入数据库失败
                            return 2;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    //文件超过规定大小
                    return 3;
                }
            }else {
                //文件格式不符合要求
                return 4;
            }
        }
        //文件路径不存在
        return 1;
    }
}
