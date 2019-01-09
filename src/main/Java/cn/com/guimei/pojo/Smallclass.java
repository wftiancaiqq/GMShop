package cn.com.guimei.pojo;


public class Smallclass {

  private Long id;
  private String smallName;
  private Long smallBigId;
  private String smallText;

  public Smallclass() {
  }

  public Smallclass(Long id, String smallName, Long smallBigId, String smallText) {
    this.id = id;
    this.smallName = smallName;
    this.smallBigId = smallBigId;
    this.smallText = smallText;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getSmallName() {
    return smallName;
  }

  public void setSmallName(String smallName) {
    this.smallName = smallName;
  }


  public Long getSmallBigId() {
    return smallBigId;
  }

  public void setSmallBigId(Long smallBigId) {
    this.smallBigId = smallBigId;
  }


  public String getSmallText() {
    return smallText;
  }

  public void setSmallText(String smallText) {
    this.smallText = smallText;
  }

}
