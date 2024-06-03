package Oregano.Backend.Exception;

import Oregano.Backend.Exception.Enum.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoDataFailException extends RuntimeException{
  private ErrorCode errorCode;
}
