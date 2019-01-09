package cn.com.guimei.pojo;


public class Discount {

  private Long id;
  private Double discRate;

  public Discount() {
  }

  public Discount(Long id, Double discRate) {
    this.id = id;
    this.discRate = discRate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getDiscRate() {
    return discRate;
  }

  public void setDiscRate(Double discRate) {
    this.discRate = discRate;
  }
}
