package cn.com.guimei.dao;

import cn.com.guimei.pojo.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    //结算顾客人数
    int countCustomer();
    //分页查询——首页查询
    List<Customer> showCustomer(@Param("pageIndex")int pageIndex,@Param("pageSize") int pageSize);
    //根据Id查询用户
    Customer cusPersonalInfo(String id);
    //修改用户信息
    int updateCustomer(Customer customer);
    //联合查询用户
    List<Customer> unionSelect(Map<String,Object> map);
    //计算联合查询结果的信息数量
    int countUnionCustomer(Customer customer);
}
