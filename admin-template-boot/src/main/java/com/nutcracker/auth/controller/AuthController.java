package com.nutcracker.auth.controller;

import com.nutcracker.auth.model.CaptchaInfo;
import com.nutcracker.auth.model.dto.WxMiniAppCodeLoginDTO;
import com.nutcracker.auth.model.dto.WxMiniAppPhoneLoginDTO;
import com.nutcracker.auth.service.AuthService;
import com.nutcracker.common.annotation.Log;
import com.nutcracker.common.enums.LogModuleEnum;
import com.nutcracker.common.result.Result;
import com.nutcracker.core.security.model.AuthenticationToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 认证控制层
 *
 * @author 胡桃夹子
 * @since 2022/10/16
 */
@Tag(name = "01.认证中心")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public Result<CaptchaInfo> getCaptcha() {
        CaptchaInfo captcha = authService.getCaptcha();
        return Result.success(captcha);
    }

    @Operation(summary = "账号密码登录")
    @PostMapping("/login")
    @Log(value = "登录", module = LogModuleEnum.LOGIN)
    public Result<AuthenticationToken> login(
            @Parameter(description = "用户名", example = "admin") @RequestParam String username,
            @Parameter(description = "密码", example = "123456") @RequestParam String password
    ) {
        AuthenticationToken authenticationToken = authService.login(username, password);
        return Result.success(authenticationToken);
    }

    @Operation(summary = "短信验证码登录")
    @PostMapping("/login/sms")
    @Log(value = "短信验证码登录", module = LogModuleEnum.LOGIN)
    public Result<AuthenticationToken> loginBySms(
            @Parameter(description = "手机号", example = "18812345678") @RequestParam String mobile,
            @Parameter(description = "验证码", example = "1234") @RequestParam String code
    ) {
        AuthenticationToken loginResult = authService.loginBySms(mobile, code);
        return Result.success(loginResult);
    }

    @Operation(summary = "发送登录短信验证码")
    @PostMapping("/sms/code")
    public Result<Void> sendLoginVerifyCode(
            @Parameter(description = "手机号", example = "18812345678") @RequestParam String mobile
    ) {
        authService.sendSmsLoginCode(mobile);
        return Result.success();
    }

    @Operation(summary = "微信授权登录(Web)")
    @PostMapping("/login/wechat")
    @Log(value = "微信登录", module = LogModuleEnum.LOGIN)
    public Result<AuthenticationToken> loginByWechat(
            @Parameter(description = "微信授权码", example = "code") @RequestParam String code
    ) {
        AuthenticationToken loginResult = authService.loginByWechat(code);
        return Result.success(loginResult);
    }

    @Operation(summary = "微信小程序登录(Code)")
    @PostMapping("/wx/miniapp/code-login")
    public Result<AuthenticationToken> loginByWxMiniAppCode(@RequestBody @Valid WxMiniAppCodeLoginDTO loginDTO) {
        AuthenticationToken token = authService.loginByWxMiniAppCode(loginDTO);
        return Result.success(token);
    }

    @Operation(summary = "微信小程序登录(手机号)")
    @PostMapping("/wx/miniapp/phone-login")
    public Result<AuthenticationToken> loginByWxMiniAppPhone(@RequestBody @Valid WxMiniAppPhoneLoginDTO loginDTO) {
        AuthenticationToken token = authService.loginByWxMiniAppPhone(loginDTO);
        return Result.success(token);
    }


    @Operation(summary = "退出登录")
    @DeleteMapping("/logout")
    @Log(value = "退出登录", module = LogModuleEnum.LOGIN)
    public Result<?> logout() {
        authService.logout();
        return Result.success();
    }

    @Operation(summary = "刷新令牌")
    @PostMapping("/refresh-token")
    public Result<?> refreshToken(
            @Parameter(description = "刷新令牌", example = "xxx.xxx.xxx") @RequestParam String refreshToken
    ) {
        AuthenticationToken authenticationToken = authService.refreshToken(refreshToken);
        return Result.success(authenticationToken);
    }

}
