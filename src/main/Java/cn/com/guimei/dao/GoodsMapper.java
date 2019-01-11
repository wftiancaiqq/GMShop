package cn.com.guimei.dao;

import cn.com.guimei.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    //查询
    List<GoodsExt> getPageData(Map<String,Object> map);
    //删除
    int delete(int id);
    //修改
    int update(Goods goods);
    //增加
    int addGoods(Goods goods);
    //获取信息总条数
    int getTotalRecord(Goods goods);
    //查询小分类的ID和名称
    List<Smallclass> getSmallClass(@Param("smallBigId") int smallBigId);
    //查询折扣的ID和数值
    List<Discount> getDiscount();
    //根据Id查询
    Goods queryById(int id);
    //判定商品名称是否存在
    Goods queryByGoodsName(String goodsName);
    //获取卖家ID和名称
    List<Seller> getSeller();
}
