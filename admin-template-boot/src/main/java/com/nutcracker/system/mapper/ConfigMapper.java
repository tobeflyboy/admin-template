package com.nutcracker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nutcracker.system.model.entity.Config;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置 访问层
 *
 * @author 胡桃夹子
 * @since 2024-7-29 11:41:04
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

}
