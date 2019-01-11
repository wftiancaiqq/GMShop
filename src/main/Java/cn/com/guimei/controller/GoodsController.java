package cn.com.guimei.controller;

import cn.com.guimei.pojo.*;
import cn.com.guimei.service.GoodsService;
import cn.com.guimei.service.SmallClassService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doGoods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private SmallClassService smallClassService;
    @RequestMapping("/query")
    public String getPageData(String pageNumber, Goods goods, HttpServletRequest request){
        Map<String,Object> map=goodsService.getPageData(pageNumber,goods);
        Long goodsId= goods.getId()!=null?goods.getId():0;
        Long goodsSmalId=goods.getGoodsSmalId()!=null?goods.getGoodsSmalId():0;
        String goodsName=goods.getGoodsName()!=null&&goods.getGoodsName().length()>0?goods.getGoodsName():"";
        map.put("ServletUrl","/doGoods/query?id="+goodsId+"&goodsSmalId="+goodsSmalId+"&goodsName="+goodsName+"&pageNumber=");
        request.getSession().setAttribute("Map",map);
        return "showGoods";
    }
    @RequestMapping(value = "/ajaxLoadBigClass",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String getBigClass(){
        List<Bigclass> bigclassList=smallClassService.getBigClass();
        return JSONArray.toJSONString(bigclassList);
    }
    @RequestMapping(value = "/ajaxLoadSmallClass",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String getSmallClass(String smallBigId){
        List<Smallclass> smallclassList=goodsService.getSmallClass(smallBigId);
        return JSONArray.toJSONString(smallclassList);
    }

    @RequestMapping("/deleteById")
    public String deleteGoodsById(String id,HttpServletRequest request){
        int i=goodsService.delete(Integer.parseInt(id));
        if (i==0){
            request.setAttribute("error","服务器繁忙，暂时无法处理该请求！");
        }
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/doGoods/query";
    }
    @RequestMapping("/update")
    public String updateSmallClass(MultipartFile goodsImageX, Goods goods, HttpServletRequest request) throws IOException {
        String filePath=request.getRealPath("/static/images/goodsImage");
        int i=goodsService.update(goods,goodsImageX,filePath);
        if (i==0){
            request.setAttribute("result","success");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateGoods.jsp";
        } else if(i==1){
            request.setAttribute("error","图像文件路径不存在！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateGoods.jsp";
        }else if(i==2){
            request.setAttribute("error","添加商品失败！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateGoods.jsp";
        }else if(i==3){
            request.setAttribute("error","上传文件不能操作5MB！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateGoods.jsp";
        }else if(i==4){
            request.setAttribute("error","文件类型非要求的图像格式！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateGoods.jsp";
        }
        request.setAttribute("result","fail");
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateGoods.jsp";
    }
    @RequestMapping("/queryById")
    public String queryGoodsById(String id,HttpServletRequest request){
        int i = id!=null && id.length()>0?Integer.parseInt(id):0;
        Goods goods=goodsService.queryById(i);
        if (goods!=null){
            request.setAttribute("goods",goods);
            return "updateGoods";
        }
        request.setAttribute("error","修改失败！");
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/doGoods/query";
    }
    @RequestMapping(value = "/ajaxQueryByGoodsName",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String queryGoodsByGoodsName(String goodsName) {
        Goods goods = goodsService.queryByGoodsName(goodsName);
        if (goods != null) {
            return "exists";
        }
        return null;
    }
    @RequestMapping(value = "/ajaxLoadDiscount",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String ajaxLoadDiscount() {
       List<Discount> discountList=goodsService.getDiscount();
       return JSONArray.toJSONString(discountList);
    }
    @RequestMapping(value = "/ajaxLoadSeller",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String ajaxLoadSeller(){
        List<Seller> sellerList=goodsService.getSeller();
        return JSONArray.toJSONString(sellerList);
    }
    @RequestMapping("/locationAddGoods")
    public String locationAddGoods(){
        return "addGoods";
    }

    @RequestMapping("/addGoods")
    public String addGoods(MultipartFile goodsImageX, Goods goods, HttpServletRequest request){
        String filePath=request.getRealPath("/static/images/goodsImage");
        int i=goodsService.addGoods(goods,goodsImageX,filePath);
        if(i==1){
            request.setAttribute("error","图像文件路径不存在！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/addGoods.jsp";
        }else if(i==2){
            request.setAttribute("error","添加商品失败！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/addGoods.jsp";
        }else if(i==3){
            request.setAttribute("error","上传文件不能操作5MB！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/addGoods.jsp";
        }else if(i==4){
            request.setAttribute("error","文件类型非要求的图像格式！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/addGoods.jsp";
        }

        return "showGoods";
    }
}
