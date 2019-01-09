package cn.com.guimei.service.impl;

import cn.com.guimei.dao.CustomerMapper;
import cn.com.guimei.pojo.Customer;
import cn.com.guimei.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;
    @RequestMapping
    public Map<String, Object> showCustomer(String num) {
        Map<String,Object> map=new HashMap<String, Object>();
        int pageSize=3;
        //默认首次访问首页
        int pageNumber=1;
        if (num!=null && num.length()>0){
            pageNumber=Integer.parseInt(num);
        }
        int pageRecord=customerMapper.countCustomer();
        //计算共有多少页
        int totalPage=pageRecord%pageSize==0?pageRecord/pageSize:pageRecord/pageSize+1;
        //求当前页的内容
        int pageIndex=(pageNumber-1)*pageSize;
        List<Customer> customerList=customerMapper.showCustomer(pageIndex,pageSize);
        map.put("pageNumber",pageNumber);
        map.put("totalPage",totalPage);
        map.put("customerList",customerList);
        return map;
    }

    @RequestMapping
    public Customer cusPersonalInfo(String id) {
        return customerMapper.cusPersonalInfo(id);
    }

    @RequestMapping
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    public Map<String,Object> unionSelect(Customer customer,String num) {
        Map<String,Object> map=new HashMap<String, Object>();
        int pageSize=3;
        //默认首次访问首页
        int pageNumber=1;
        if (num!=null && num.length()>0){
            pageNumber=Integer.parseInt(num);
        }
        int pageRecord=customerMapper.countUnionCustomer(customer);
        //计算共有多少页
        int totalPage=pageRecord%pageSize==0?pageRecord/pageSize:pageRecord/pageSize+1;
        //求当前页的内容
        int pageIndex=(pageNumber-1)*pageSize;
        Map<String,Object> mapUnion=new HashMap<String, Object>();
        mapUnion.put("pageIndex",pageIndex);
        mapUnion.put("pageSize",pageSize);
        mapUnion.put("customer",customer);
        List<Customer> customerList=customerMapper.unionSelect(mapUnion);
        map.put("pageNumber",pageNumber);
        map.put("totalPage",totalPage);
        map.put("customerList",customerList);
        return map;
    }
}
