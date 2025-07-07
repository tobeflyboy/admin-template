package com.nutcracker.core.security.filter;

import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.util.StrUtil;
import com.nutcracker.common.constant.RedisConstants;
import com.nutcracker.common.constant.SecurityConstants;
import com.nutcracker.common.result.ResultCode;
import com.nutcracker.common.util.ResponseUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * 图形验证码校验过滤器
 *
 * @author 胡桃夹子
 * @since 2022/10/1
 */
@Slf4j
@SuppressWarnings("NullableProblems")
public class CaptchaValidationFilter extends OncePerRequestFilter {

    private static final RequestMatcher LOGIN_PATH_REQUEST_MATCHER =
            PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, SecurityConstants.LOGIN_PATH);

    public static final String CAPTCHA_CODE_PARAM_NAME = "captchaCode";
    public static final String CAPTCHA_KEY_PARAM_NAME = "captchaKey";

    private final RedisTemplate<String, Object> redisTemplate;

    private final CodeGenerator codeGenerator;

    public CaptchaValidationFilter(RedisTemplate<String, Object> redisTemplate, CodeGenerator codeGenerator) {
        this.redisTemplate = redisTemplate;
        this.codeGenerator = codeGenerator;
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 检验登录接口的验证码
        if (LOGIN_PATH_REQUEST_MATCHER.matches(request)) {
            // 请求中的验证码
            String captchaCode = request.getParameter(CAPTCHA_CODE_PARAM_NAME);
            log.info("captchaCode={}", captchaCode);
            // TODO 兼容没有验证码的版本(线上请移除这个判断)
            if (StrUtil.isBlank(captchaCode)) {
                chain.doFilter(request, response);
                return;
            }
            // 缓存中的验证码
            String verifyCodeKey = request.getParameter(CAPTCHA_KEY_PARAM_NAME);
            String cacheVerifyCode = (String) redisTemplate.opsForValue().get(
                    StrUtil.format(RedisConstants.Captcha.IMAGE_CODE, verifyCodeKey)
            );
            log.info("verifyCodeKey={},cacheVerifyCode={}", verifyCodeKey, cacheVerifyCode);
            if (cacheVerifyCode == null) {
                log.warn("用户验证码过期");
                ResponseUtils.writeErrMsg(response, ResultCode.USER_VERIFICATION_CODE_EXPIRED);
            } else {
                // 验证码比对
                boolean verify = codeGenerator.verify(cacheVerifyCode, captchaCode);
                log.info("{}={} ({})", cacheVerifyCode, captchaCode, verify);
                if (verify) {
                    chain.doFilter(request, response);
                } else {
                    log.warn("验证码错误");
                    ResponseUtils.writeErrMsg(response, ResultCode.USER_VERIFICATION_CODE_ERROR);
                }
            }
        } else {
            // 非登录接口放行
            chain.doFilter(request, response);
        }
    }

}
