package com.nutcracker.core.security.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

/**
 * 验证码校验异常
 *
 * @author 胡桃夹子
 * @date 2025/07/07 13:11:29
 */
public class CaptchaValidationException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = -6819848467340158698L;

    public CaptchaValidationException(String msg) {
        super(msg);
    }
}