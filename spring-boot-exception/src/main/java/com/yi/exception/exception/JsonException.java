package com.yi.exception.exception;

import com.yi.exception.constant.Status;
import lombok.Getter;

/**
 * json异常
 *
 * @author YI
 * @date 2020-7-22
 */
@Getter
public class JsonException extends BaseException {

    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}
