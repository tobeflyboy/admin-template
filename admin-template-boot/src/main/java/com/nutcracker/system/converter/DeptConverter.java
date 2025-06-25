package com.nutcracker.system.converter;

import com.nutcracker.system.model.entity.Dept;
import com.nutcracker.system.model.form.DeptForm;
import com.nutcracker.system.model.vo.DeptVO;
import org.mapstruct.Mapper;

/**
 * 部门对象转换器
 *
 * @author 胡桃夹子
 * @since 2022/7/29
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    DeptForm toForm(Dept entity);

    DeptVO toVo(Dept entity);

    Dept toEntity(DeptForm deptForm);

}