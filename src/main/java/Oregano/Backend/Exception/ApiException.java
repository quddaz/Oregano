package Oregano.Backend.Exception;

import Oregano.Backend.Exception.Enum.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiException extends RuntimeException {
  private ErrorCode errorCode;
}
