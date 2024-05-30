package Oregano.Backend.DTO.FetchJobDto;

import lombok.Data;

@Data
public class Body {
  private Items items;
  private int numOfRows;
  private int pageNo;
  private int totalCount;
}
