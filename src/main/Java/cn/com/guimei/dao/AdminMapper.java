package cn.com.guimei.dao;

import cn.com.guimei.pojo.SuperUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

public interface AdminMapper {
    SuperUser adminLogin(@Param("userLoginName")String userLoginName,@Param("userPassword")String userPassword);
    SuperUser personalInfo(int id);
    int updateSuperUser(SuperUser superUser);
}
