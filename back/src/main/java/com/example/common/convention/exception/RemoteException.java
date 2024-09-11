package com.example.common.convention.exception;


import com.example.common.convention.errorcode.BaseErrorCode;
import com.example.common.convention.errorcode.IErrorCode;

/**
 * ClassName: RemoteException
 * Package: com.natsume.shortlink.admin.common.convention.exception
 * Description: 远程服务调用异常
 *
 * @Author natsume
 * @Create 2024/6/15 11:34
 * @Version 1.0
 */
public class RemoteException extends AbstractException {

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
