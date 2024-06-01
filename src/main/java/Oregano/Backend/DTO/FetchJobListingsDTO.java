package Oregano.Backend.DTO;


import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class FetchJobListingsDTO {

  private Body body;

  @Getter
  @Setter
  @ToString
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class Body {
    private Items items;
  }

  @Getter
  @Setter
  @ToString
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class Items {
    private List<Item> item;
  }

  @Getter
  @Setter
  @ToString
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class Item {
    private String busplaName;
    private String cntctNo;
    private String compAddr;
    private String empType;
    private String enterType;
    private String jobNm;
    private String offerregDt;
    private String regDt;
    private String regagnName;
    private String reqCareer;
    private String reqEduc;
    private String salary;
    private String salaryType;
    private String termDate;
  }
}