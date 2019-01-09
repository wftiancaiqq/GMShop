package cn.com.guimei.service.impl;

import cn.com.guimei.dao.SmallclassMapper;
import cn.com.guimei.pojo.Bigclass;
import cn.com.guimei.pojo.Page;
import cn.com.guimei.pojo.SmallClassExt;
import cn.com.guimei.pojo.Smallclass;
import cn.com.guimei.service.SmallClassService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SmallClassServiceImpl implements SmallClassService {
    @Resource
    private SmallclassMapper smallclassMapper;

    public Map<String,Object> getPageData(String num, Smallclass smallclass) {
        Map<String,Object> map=new HashMap<String, Object>();
        int pageSize=5;
        //默认首次访问首页
        int pageNumber=1;
        if (num!=null && num.length()>0){
            pageNumber=Integer.parseInt(num);
        }
        int pageRecord=smallclassMapper.getTotalRecord(smallclass);
        //计算共有多少页
        int totalPage=pageRecord%pageSize==0?pageRecord/pageSize:pageRecord/pageSize+1;
        //求当前页的内容
        int pageIndex=(pageNumber-1)*pageSize;
        Map<String,Object> mapUnion=new HashMap<String, Object>();
        mapUnion.put("pageIndex",pageIndex);
        mapUnion.put("pageSize",pageSize);
        mapUnion.put("smallclass",smallclass);
        List<SmallClassExt> smallClassExtList=smallclassMapper.getPageData(mapUnion);
        map.put("pageNumber",pageNumber);
        map.put("totalPage",totalPage);
        map.put("smallClassList",smallClassExtList);
        return map;
    }

    public List<Bigclass> getBigClass() {
        return smallclassMapper.getBigClass();
    }

    public int delete(int id) {
        return smallclassMapper.delete(id);
    }

    public int update(Smallclass sc) {
        return smallclassMapper.update(sc);
    }

    public Smallclass queryById(int id) {
        if (id>0){
            return smallclassMapper.queryById(id);
        }
        return null;
    }

    public Smallclass queryBySmallName(String smallName) {
        return smallclassMapper.queryBySmallName(smallName);
    }

}
