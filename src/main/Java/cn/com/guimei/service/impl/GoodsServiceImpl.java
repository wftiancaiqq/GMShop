package cn.com.guimei.service.impl;

import cn.com.guimei.dao.GoodsMapper;
import cn.com.guimei.pojo.*;
import cn.com.guimei.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    public Map<String, Object> getPageData(String num, Goods goods) {
        Map<String,Object> map=new HashMap<String, Object>();
        int pageSize=5;
        //默认首次访问首页
        int pageNumber=1;
        if (num!=null && num.length()>0){
            pageNumber=Integer.parseInt(num);
        }
        int pageRecord=goodsMapper.getTotalRecord(goods);
        //计算共有多少页
        int totalPage=pageRecord%pageSize==0?pageRecord/pageSize:pageRecord/pageSize+1;
        //求当前页的内容
        int pageIndex=(pageNumber-1)*pageSize;
        Map<String,Object> mapUnion=new HashMap<String, Object>();
        mapUnion.put("pageIndex",pageIndex);
        mapUnion.put("pageSize",pageSize);
        mapUnion.put("goods",goods);
        List<GoodsExt> goodsList=goodsMapper.getPageData(mapUnion);
        map.put("pageNumber",pageNumber);
        map.put("totalPage",totalPage);
        map.put("Goods",goodsList);
        return map;
    }

    public List<Smallclass> getSmallClass() {
        return goodsMapper.getSmallClass();
    }

    public List<Discount> getDiscount() {
        return goodsMapper.getDiscount();
    }

    public int delete(int id) {
        return goodsMapper.delete(id);
    }

    public int update(Goods goods, MultipartFile goodsFile, String filePath) {
        //判断filePath路径是否真是存在
        File file=new File(filePath);
        if (file.exists()){
            //获取文件名称
            String goodsImageName=goodsFile.getOriginalFilename();
            //获取文件大小
            long fileSize=goodsFile.getSize();
            if (goodsImageName.toLowerCase().endsWith(".gif")||
                    goodsImageName.toLowerCase().endsWith(".bmp")||
                    goodsImageName.toLowerCase().endsWith(".jpeg")||
                    goodsImageName.toLowerCase().endsWith(".icon")||
                    goodsImageName.toLowerCase().endsWith(".jpg")){
                if (fileSize<=1024*1024*5){
                    try {
                        //执行写入操作
                        goodsFile.transferTo(new File(file,"/"+goodsImageName));
                        //执行数据库写入操作
                        goods.setGoodsImage(goodsImageName);
                        int i=goodsMapper.update(goods);
                        if (i>0){
                            //写入成功
                            return 0;
                        }else {
                            //写入数据库失败
                            return 2;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    //文件大小超标
                    return 3;
                }
            }else {
                //文件格式不符合要求
                return 4;
            }
        }
        //文件路径不存在
        return 1;
    }

    public Goods queryById(int id) {
        return goodsMapper.queryById(id);
    }

    public Goods queryByGoodsName(String goodsName) {
        return goodsMapper.queryByGoodsName(goodsName);
    }

    public List<Seller> getSeller() {
        return goodsMapper.getSeller();
    }

    public int addGoods(Goods goods,MultipartFile goodsFile,String filePath) {
        //判断filePath路径是否真是存在
        File file = new File(filePath);
        if (file.exists()){
            //获取文件名称
            String goodsImageName=goodsFile.getOriginalFilename();
            //获取文件大小
            long fileSize=goodsFile.getSize();
            if (goodsImageName.toLowerCase().endsWith(".gif")||
                    goodsImageName.toLowerCase().endsWith(".bmp")||
                    goodsImageName.toLowerCase().endsWith(".jpeg")||
                    goodsImageName.toLowerCase().endsWith(".icon")||
                    goodsImageName.toLowerCase().endsWith(".jpg")){
                if (fileSize<=1024*1024*5){
                    try {
                        //执行写入操作
                        goodsFile.transferTo(new File(file,"/"+goodsImageName));
                        //执行数据库写入操作
                        goods.setGoodsImage(goodsImageName);
                        int i=goodsMapper.addGoods(goods);
                        if (i>0){
                            return 0;
                        }else {
                            //写入数据库失败
                            return 2;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    //文件超过规定大小
                    return 3;
                }
            }else {
                //文件格式不符合要求
                return 4;
            }
        }
        //文件路径不存在
        return 1;
    }
}
