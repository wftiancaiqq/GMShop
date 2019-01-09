package cn.com.guimei.dao;

import cn.com.guimei.pojo.Bigclass;
import cn.com.guimei.pojo.SmallClassExt;
import cn.com.guimei.pojo.Smallclass;

import java.util.List;
import java.util.Map;

public interface SmallclassMapper {
    //查询
    List<SmallClassExt> getPageData(Map<String,Object> map);
    //删除
    int delete(int id);
    //修改
    int update(Smallclass sc);
    //增加
    int add(Smallclass sc);
    //获取信息总条数
    int getTotalRecord(Smallclass smallclass);
    //查询大分类的ID和名称
    List<Bigclass> getBigClass();
    //根据Id查询
    Smallclass queryById(int id);
    //判定小分类名称是否存在
    Smallclass queryBySmallName(String smallName);
}
