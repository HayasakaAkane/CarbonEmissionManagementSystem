package com.example.common.convention.exception;


import com.example.common.convention.errorcode.IErrorCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * ClassName: AbstractException
 * Package: com.natsume.shortlink.admin.common.convention.exception
 * Description: 抽象项目中三类异常体系，客户端异常、服务端异常以及远程服务调用异常
 *
 * @Author natsume
 * @Create 2024/6/15 11:32
 * @Version 1.0
 */
@Getter
public class AbstractException extends RuntimeException {
    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message) ? message : null).orElse(errorCode.message());
    }
}
