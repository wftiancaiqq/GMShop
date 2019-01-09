package cn.com.guimei.dao;

import cn.com.guimei.pojo.SuperUser;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    public SuperUser adminLogin(@Param("userLoginName")String userLoginName,@Param("userPassword")String userPassword);
    public SuperUser personalInfo(int id);
}
