package com.example.common.convention.errorcode;

/**
 * ClassName: IErrorCode
 * Package: com.example.common.convention.errorcode
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 10:32
 * @Version 1.0
 */
public interface IErrorCode {

    /**
     * 错误码
     */
    String code();

    /**
     * 错误信息
     */
    String message();
}