package Oregano.Backend.Service;

import Oregano.Backend.DTO.FetchJobListingsDTO;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class JobListingsFilterService {

  public FetchJobListingsDTO filterJobListings(FetchJobListingsDTO responseData, String region, String empType) {
    if (responseData == null || responseData.getBody() == null || responseData.getBody().getItems() == null) {
      return new FetchJobListingsDTO();
    }

    convertSalaryToMonthly(responseData);

    List<FetchJobListingsDTO.Item> filteredItems = responseData.getBody().getItems().getItem().stream()
        .filter(item -> {
          String compAddr = item.getCompAddr();
          String jobType = item.getEmpType();
          return compAddr != null && compAddr.startsWith(region.split(" ")[0]) &&
              jobType != null && jobType.equals(empType);
        })
        .collect(Collectors.toList());

    responseData.getBody().getItems().setItem(filteredItems);
    return responseData;
  }

  private void convertSalaryToMonthly(FetchJobListingsDTO responseData) {
    if (responseData == null || responseData.getBody() == null || responseData.getBody().getItems() == null) {
      return;
    }

    List<FetchJobListingsDTO.Item> items = responseData.getBody().getItems().getItem();
    for (FetchJobListingsDTO.Item item : items) {
      String salaryType = item.getSalaryType();
      String salary = item.getSalary();
      if (salaryType != null && salary != null) {
        if (salaryType.equals("연봉")) {
          // 연봉을 월급으로 변환 (연봉 / 12)
          double annualSalary = Double.parseDouble(salary.replace(",", ""));
          double monthlySalary = annualSalary / 12;
          item.setSalary(formatSalary(monthlySalary));
          item.setSalaryType("월급");
        }
      }
    }
  }

  private String formatSalary(double salary) {
    DecimalFormat formatter = new DecimalFormat("#,###");
    return formatter.format(salary);
  }
}