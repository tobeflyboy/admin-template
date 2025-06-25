package com.nutcracker.core.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码校验异常
 *
 * @author 胡桃夹子
 * @since 2025/3/1
 */
public class CaptchaValidationException extends AuthenticationException {
    public CaptchaValidationException(String msg) {
        super(msg);
    }
}