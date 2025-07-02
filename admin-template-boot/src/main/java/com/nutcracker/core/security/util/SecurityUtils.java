package com.nutcracker.core.security.util;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.nutcracker.common.constant.SecurityConstants;
import com.nutcracker.common.constant.SystemConstants;
import com.nutcracker.core.security.model.SysUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * Spring Security 工具类
 *
 * @author 胡桃夹子
 * @since 2021/1/10
 */
@Slf4j
public class SecurityUtils {

    /**
     * 获取当前登录人信息
     *
     * @return Optional<SysUserDetails>
     */
    public static Optional<SysUserDetails> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SysUserDetails) {
                return Optional.of((SysUserDetails) principal);
            }
        }
        return Optional.empty();
    }


    /**
     * 获取用户ID
     *
     * @return Long
     */
    public static Long getUserId() {
        return getUser().map(SysUserDetails::getUserId).orElse(null);
    }


    /**
     * 获取用户账号
     *
     * @return String 用户账号
     */
    public static String getUsername() {
        return getUser().map(SysUserDetails::getUsername).orElse(null);
    }


    /**
     * 获取部门ID
     *
     * @return Long
     */
    public static Long getDeptId() {
        return getUser().map(SysUserDetails::getDeptId).orElse(null);
    }

    /**
     * 获取数据权限范围
     *
     * @return Integer
     */
    public static Integer getDataScope() {
        return getUser().map(SysUserDetails::getDataScope).orElse(null);
    }

    /**
     * 获取当前用户的角色集合（去除前缀 ROLE_ 后的结果）
     *
     * @return 角色名称的字符串集合（Set<String>）
     */
    public static Set<String> getRoles() {
        // 1. 获取当前用户的认证信息 Authentication 对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 2. 判断认证信息是否为空
        if (authentication == null) {
            // 如果没有认证信息，返回一个空的 Set（避免返回 null）
            return Collections.emptySet();
        }

        // 3. 获取认证信息中的权限集合（GrantedAuthority 是 Spring Security 中表示权限的对象）
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // 4. 判断权限集合是否为空
        if (authorities == null || authorities.isEmpty()) {
            // 权限集合为空，返回空 Set
            return Collections.emptySet();
        }

        // 5. 创建一个 HashSet 用于存储处理后的角色名称
        Set<String> roles = new HashSet<>();

        // 6. 遍历每一个权限对象
        for (GrantedAuthority authority : authorities) {
            // 获取权限名称（例如：ROLE_ADMIN）
            String authorityStr = authority.getAuthority();

            // 7. 判断权限名称不为空，并且是以 "ROLE_" 开头的角色权限
            if (authorityStr != null && authorityStr.startsWith(SecurityConstants.ROLE_PREFIX)) {

                // 8. 去除前缀 "ROLE_"，只保留角色名部分（如 ADMIN）
                String role = authorityStr.substring(SecurityConstants.ROLE_PREFIX.length());

                // 9. 将角色名添加到结果集合中
                roles.add(role);
            }
        }
        log.info("roles={}", JSONUtil.toJsonStr(roles));
        // 10. 返回最终的角色集合
        return roles;
    }

    /**
     * 是否超级管理员
     * <p>
     * 超级管理员忽视任何权限判断
     */
    public static boolean isRoot() {
        Set<String> roles = getRoles();
        return roles.contains(SystemConstants.ROOT_ROLE_CODE);
    }

    /**
     * 获取请求中的 Token
     *
     * @return Token 字符串
     */
    public static String getTokenFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }


}
