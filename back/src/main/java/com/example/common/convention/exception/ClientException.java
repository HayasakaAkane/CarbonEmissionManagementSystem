package com.example.common.convention.exception;


import com.example.common.convention.errorcode.BaseErrorCode;
import com.example.common.convention.errorcode.IErrorCode;

/**
 * ClassName: ClientException
 * Package: com.natsume.shortlink.admin.common.convention.exception
 * Description: 客户端异常
 *
 * @Author natsume
 * @Create 2024/6/15 11:33
 * @Version 1.0
 */
public class ClientException extends AbstractException {

    public ClientException(IErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public ClientException(String message) {
        this(message, null, BaseErrorCode.CLIENT_ERROR);
    }

    public ClientException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
