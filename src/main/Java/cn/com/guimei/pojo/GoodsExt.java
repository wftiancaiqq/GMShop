package cn.com.guimei.pojo;

public class GoodsExt extends Goods{
    private Smallclass smallclass;
    private Discount discount;
    private Seller seller;


    public GoodsExt() {
    }

    public GoodsExt(Long id, String goodsName, Long goodsSmalId, Double goodsMoney, Long goodsNumber, String goodsImage, Double goodsCarriage, Long goodsType, Long goodsDiscId, Long goodsSellerId, Smallclass smallclass, Discount discount, Seller seller) {
        super(id, goodsName, goodsSmalId, goodsMoney, goodsNumber, goodsImage, goodsCarriage, goodsType, goodsDiscId, goodsSellerId);
        this.smallclass = smallclass;
        this.discount = discount;
        this.seller = seller;
    }

    public Smallclass getSmallclass() {
        return smallclass;
    }

    public void setSmallclass(Smallclass smallclass) {
        this.smallclass = smallclass;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
