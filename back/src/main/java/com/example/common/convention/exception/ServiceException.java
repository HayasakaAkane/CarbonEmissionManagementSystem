package com.example.common.convention.exception;


import com.example.common.convention.errorcode.BaseErrorCode;
import com.example.common.convention.errorcode.IErrorCode;

import java.util.Optional;

/**
 * ClassName: ServiceException
 * Package: com.natsume.shortlink.admin.common.convention.exception
 * Description: 服务端异常
 *
 * @Author natsume
 * @Create 2024/6/15 11:34
 * @Version 1.0
 */
public class ServiceException extends AbstractException {

    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
