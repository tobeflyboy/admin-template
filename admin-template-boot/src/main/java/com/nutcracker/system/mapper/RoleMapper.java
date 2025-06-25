package com.nutcracker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nutcracker.system.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 角色持久层接口
 *
 * @author 胡桃夹子
 * @since 2022/1/14
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取最大范围的数据权限
     *
     * @param roles 角色编码集合
     * @return
     */
    Integer getMaximumDataScope(Set<String> roles);
}
