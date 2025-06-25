package com.nutcracker.common.enums;

import com.nutcracker.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 环境枚举
 *
 * @author 胡桃夹子
 * @since 4.0.0
 */
@Getter
public enum EnvEnum implements IBaseEnum<String> {

    DEV("dev", "开发环境"),
    PROD("prod", "生产环境");

    private final String value;

    private final String label;

    EnvEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
