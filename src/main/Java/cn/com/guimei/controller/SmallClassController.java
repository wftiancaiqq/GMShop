package cn.com.guimei.controller;

import cn.com.guimei.pojo.Bigclass;
import cn.com.guimei.pojo.Smallclass;
import cn.com.guimei.service.SmallClassService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doSmallClass")
public class SmallClassController {
    @Resource
    private SmallClassService smallClassService;
    @RequestMapping("/query")
    public String getPageData(String pageNumber, Smallclass smallclass, HttpServletRequest request){
        Map<String,Object> map=smallClassService.getPageData(pageNumber,smallclass);
        Long smallClassId= smallclass.getId()!=null?smallclass.getId():0;
        Long smallBigId=smallclass.getSmallBigId()!=null?smallclass.getSmallBigId():0;
        String smallName=smallclass.getSmallName()!=null?smallclass.getSmallName():"";
        map.put("ServletUrl","/doSmallClass/query?id="+smallClassId+"&smallBigId="+smallBigId+"&smallName="+smallName+"&pageNumber=");
        request.getSession().setAttribute("Map",map);
        return "showSmallClass";
    }
    @RequestMapping(value = "/ajaxLoadBigClass",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String ajaxLoadBigClass(){
        List<Bigclass> bigclassList=smallClassService.getBigClass();
        return JSONArray.toJSONString(bigclassList);
    }
    @RequestMapping("deleteById")
    public String deleteById(String smallId,HttpServletRequest request){
        int id=smallId!=null?Integer.parseInt(smallId):0;
        int i=smallClassService.delete(id);
        if (i==0){
            request.setAttribute("error","不能删除该小分类！该小分类中有对应的商品信息!");
        }
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/doSmallClass/query";
    }
    @RequestMapping("/queryById")
    public String queryById(String id,HttpServletRequest request){
        int i = id!=null && id.length()>0?Integer.parseInt(id):0;
        Smallclass smallclass=smallClassService.queryById(i);
        if (smallclass!=null){
            request.setAttribute("smallClass",smallclass);
            return "UpdateSmallClass";
        }
        request.setAttribute("error","修改失败！");
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/doSmallClass/query";
    }
    @RequestMapping(value = "/ajaxQueryBySmallName",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String queryBySmallName(String smallName){
        Smallclass smallclass=smallClassService.queryBySmallName(smallName);
        if (smallclass!=null){
            return "exists";
        }
        return null;
    }

    @RequestMapping("/update")
    public String updateSmallClass(Smallclass smallclass,HttpServletRequest request){
        int i=smallClassService.update(smallclass);
        if (i==1){
            request.setAttribute("result","success");
        }else {
            request.setAttribute("result","fail");
        }
        return "UpdateSmallClass";
    }
}
