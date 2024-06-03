package Oregano.Backend.Exception.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  API_CALL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "API 호출 중 오류가 발생했습니다."),
  JSON_PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "JSON 파싱 중 오류가 발생했습니다."),
  NO_DATA_FAIL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "데이터를 가져오지 못했습니다.");
  private final HttpStatus httpStatus;
  private final String message;
}