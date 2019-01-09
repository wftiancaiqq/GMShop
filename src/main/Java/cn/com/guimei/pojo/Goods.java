package cn.com.guimei.pojo;


public class Goods {

  private Long id;
  private String goodsName;
  private Long goodsSmalId;
  private Double goodsMoney;
  private Long goodsNumber;
  private String goodsImage;
  private Double goodsCarriage;
  private Long goodsType;
  private Long goodsDiscId;
  private Long goodsSellerId;

      public Goods() {
      }

    public Goods(Long id, String goodsName, Long goodsSmalId, Double goodsMoney, Long goodsNumber, String goodsImage, Double goodsCarriage, Long goodsType, Long goodsDiscId, Long goodsSellerId) {
        this.id = id;
        this.goodsName = goodsName;
        this.goodsSmalId = goodsSmalId;
        this.goodsMoney = goodsMoney;
        this.goodsNumber = goodsNumber;
        this.goodsImage = goodsImage;
        this.goodsCarriage = goodsCarriage;
        this.goodsType = goodsType;
        this.goodsDiscId = goodsDiscId;
        this.goodsSellerId = goodsSellerId;
    }

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public Long getGoodsSmalId() {
    return goodsSmalId;
  }

  public void setGoodsSmalId(Long goodsSmalId) {
    this.goodsSmalId = goodsSmalId;
  }

  public Double getGoodsMoney() {
    return goodsMoney;
  }

  public void setGoodsMoney(Double goodsMoney) {
    this.goodsMoney = goodsMoney;
  }

  public Long getGoodsNumber() {
    return goodsNumber;
  }

  public void setGoodsNumber(Long goodsNumber) {
    this.goodsNumber = goodsNumber;
  }

  public String getGoodsImage() {
    return goodsImage;
  }

  public void setGoodsImage(String goodsImage) {
    this.goodsImage = goodsImage;
  }

  public Double getGoodsCarriage() {
    return goodsCarriage;
  }

  public void setGoodsCarriage(Double goodsCarriage) {
    this.goodsCarriage = goodsCarriage;
  }

  public Long getGoodsType() {
    return goodsType;
  }

  public void setGoodsType(Long goodsType) {
    this.goodsType = goodsType;
  }

  public Long getGoodsDiscId() {
    return goodsDiscId;
  }

  public void setGoodsDiscId(Long goodsDiscId) {
    this.goodsDiscId = goodsDiscId;
  }

    public Long getGoodsSellerId() {
        return goodsSellerId;
    }

    public void setGoodsSellerId(Long goodsSellerId) {
        this.goodsSellerId = goodsSellerId;
    }

    @Override
  public String toString() {
    return "Goods{" +
            "id=" + id +
            ", goodsName='" + goodsName + '\'' +
            ", goodsSmalId=" + goodsSmalId +
            ", goodsMoney=" + goodsMoney +
            ", goodsNumber=" + goodsNumber +
            ", goodsImage='" + goodsImage + '\'' +
            ", goodsCarriage=" + goodsCarriage +
            ", goodsType=" + goodsType +
            ", goodsDiscId=" + goodsDiscId +
            '}';
  }
}
