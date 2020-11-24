package com.yi.exception.exception;

import com.yi.exception.constant.Status;
import lombok.Getter;

/**
 * 页面异常
 *
 * @author YI
 * @date 2020-7-22
 */
@Getter
public class PageException extends BaseException {

    public PageException(Status status) {
        super(status);
    }

    public PageException(Integer code, String message) {
        super(code, message);
    }
}
