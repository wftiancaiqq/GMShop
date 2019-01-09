package cn.com.guimei.service;

import cn.com.guimei.pojo.Bigclass;
import cn.com.guimei.pojo.Page;
import cn.com.guimei.pojo.SmallClassExt;
import cn.com.guimei.pojo.Smallclass;

import java.util.List;
import java.util.Map;

public interface SmallClassService {
    //查询
    Map<String,Object> getPageData(String num, Smallclass smallclass);
    //查询大分类的ID和名称
    List<Bigclass> getBigClass();
    //删除
    int delete(int id);
    //修改
    int update(Smallclass sc);
    //根据Id查询
    Smallclass queryById(int id);
    //判定小分类名称是否存在
    Smallclass queryBySmallName(String smallName);
}
