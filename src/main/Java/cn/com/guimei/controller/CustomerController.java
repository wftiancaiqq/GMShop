package cn.com.guimei.controller;

import cn.com.guimei.pojo.Customer;
import cn.com.guimei.pojo.SuperUser;
import cn.com.guimei.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doCustomer")
public class CustomerController {
    @Resource
    private CustomerService customerService;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @RequestMapping("/showCustomer")
    public String showCustomer(String pageNumber,HttpServletRequest request){
        Map<String,Object> customerMap=customerService.showCustomer(pageNumber);
        customerMap.put("ServletUrl","/doCustomer/showCustomer?pageNumber=");
        request.getSession().setAttribute("Map",customerMap);
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/showCustomer.jsp";
    }

    @RequestMapping("/cusPersonalInfo")
    public String cusPersonalInfo(String id,HttpServletRequest request){
        Customer customer=customerService.cusPersonalInfo(id);
        String birthday=sdf.format(customer.getCusBirthday());
        request.setAttribute("birthday",birthday);
        request.setAttribute("customer",customer);
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/cusPersonalInfo.jsp";
    }

    @RequestMapping("/updateCustomer")
    public String updateCustomer(Customer customer,String birthday ,HttpServletRequest request){
        try {
            customer.setCusBirthday(sdf.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int n=customerService.updateCustomer(customer);
        if (n==1){
            request.setAttribute("result","success");
        }else {
            request.setAttribute("result","fail");
        }
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/cusPersonalInfo.jsp";
    }

    @RequestMapping("unionSelect")
    public String unionSelect(Customer customer,String pageNumber, HttpServletRequest request){
        Map<String,Object> customerMap=customerService.unionSelect(customer,pageNumber);
        if (customer.getId()==null){
            customer.setId(Long.parseLong("0"));
        }
        customerMap.put("ServletUrl","/doCustomer/unionSelect?id="+customer.getId()+"&cusName="+customer.getCusName()+
                "&cusSex="+customer.getCusSex()+"&pageNumber=");
        request.getSession().setAttribute("Map",customerMap);
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/showCustomer.jsp";
    }
}
