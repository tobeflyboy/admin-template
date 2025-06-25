package com.nutcracker.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nutcracker.system.model.entity.Dict;
import com.nutcracker.system.model.form.DictForm;
import com.nutcracker.system.model.vo.DictPageVO;
import org.mapstruct.Mapper;

/**
 * 字典 对象转换器
 *
 * @author 胡桃夹子
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface DictConverter {

    Page<DictPageVO> toPageVo(Page<Dict> page);

    DictForm toForm(Dict entity);

    Dict toEntity(DictForm entity);
}
