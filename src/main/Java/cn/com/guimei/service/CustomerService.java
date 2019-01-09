package cn.com.guimei.service;

import cn.com.guimei.pojo.Customer;
import java.util.Map;

public interface CustomerService {
    Map<String,Object> showCustomer(String pageNumber);
    Customer cusPersonalInfo(String id);
    int updateCustomer(Customer customer);
    Map<String,Object> unionSelect(Customer customer,String num);
}
