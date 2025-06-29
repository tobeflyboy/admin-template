package com.nutcracker.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nutcracker.common.model.Option;
import com.nutcracker.system.model.bo.UserBO;
import com.nutcracker.system.model.dto.CurrentUserDTO;
import com.nutcracker.system.model.dto.UserImportDTO;
import com.nutcracker.system.model.entity.User;
import com.nutcracker.system.model.form.UserForm;
import com.nutcracker.system.model.form.UserProfileForm;
import com.nutcracker.system.model.vo.UserPageVO;
import com.nutcracker.system.model.vo.UserProfileVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 用户对象转换器
 *
 * @author 胡桃夹子
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserPageVO toPageVo(UserBO bo);

    Page<UserPageVO> toPageVo(Page<UserBO> bo);

    UserForm toForm(User entity);

    @InheritInverseConfiguration(name = "toForm")
    User toEntity(UserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    CurrentUserDTO toCurrentUserDto(User entity);

    User toEntity(UserImportDTO vo);


    UserProfileVO toProfileVo(UserBO bo);

    User toEntity(UserProfileForm formData);

    @Mappings({
            @Mapping(target = "label", source = "nickname"),
            @Mapping(target = "value", source = "id")
    })
    Option<String> toOption(User entity);

    List<Option<String>> toOptions(List<User> list);
}
