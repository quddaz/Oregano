package Oregano.Backend.Exception.Advice;

import Oregano.Backend.DTO.ResponseDto;
import Oregano.Backend.Exception.ApiException;
import Oregano.Backend.Exception.Enum.ErrorCode;
import Oregano.Backend.Exception.NoDataFailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ApiExceptionAdvice {
  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ResponseDto<?>> apiExceptionHandler(ApiException e) {
    ErrorCode errorCode = e.getErrorCode();
    ResponseDto<?> response = ResponseDto.fail(errorCode.getHttpStatus().value(), errorCode.getMessage() + " " + e.getMessage());
    return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
  }
  @ExceptionHandler(NoDataFailException.class)
  public ResponseEntity<ResponseDto<?>> apiExceptionHandler(NoDataFailException e) {
    ErrorCode errorCode = e.getErrorCode();
    ResponseDto<?> response = ResponseDto.fail(errorCode.getHttpStatus().value(), errorCode.getMessage() + " " + e.getMessage());
    return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
  }

}
