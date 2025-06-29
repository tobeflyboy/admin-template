package com.nutcracker.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nutcracker.common.model.Option;
import com.nutcracker.system.model.entity.Role;
import com.nutcracker.system.model.form.RoleForm;
import com.nutcracker.system.model.vo.RolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 角色对象转换器
 *
 * @author 胡桃夹子
 * @since 2022/5/29
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    Page<RolePageVO> toPageVo(Page<Role> page);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option<Long> toOption(Role role);

    List<Option<Long>> toOptions(List<Role> roles);

    Role toEntity(RoleForm roleForm);

    RoleForm toForm(Role entity);
}