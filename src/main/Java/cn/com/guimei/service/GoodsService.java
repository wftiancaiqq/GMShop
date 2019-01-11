package cn.com.guimei.service;

import cn.com.guimei.pojo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    //查询
    Map<String,Object> getPageData(String num, Goods goods);
    //查询小分类的ID和名称
    List<Smallclass> getSmallClass(String smallBigId);
    //查询折扣的ID和数值
    List<Discount> getDiscount();
    //删除
    int delete(int id);
    //修改
    int update(Goods goods, MultipartFile goodsFile, String filePath);
    //根据Id查询
    Goods queryById(int id);
    //判定商品名称是否存在
    Goods queryByGoodsName(String goodsName);
    //获取卖家ID和名称
    List<Seller> getSeller();
    //增加
    int addGoods(Goods goods, MultipartFile goodsFile, String filePath);
}
