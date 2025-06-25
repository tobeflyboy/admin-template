package com.nutcracker.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nutcracker.common.model.Option;
import com.nutcracker.system.model.entity.Dict;
import com.nutcracker.system.model.form.DictForm;
import com.nutcracker.system.model.query.DictPageQuery;
import com.nutcracker.system.model.vo.DictPageVO;

import java.util.List;

/**
 * 字典业务接口
 *
 * @author 胡桃夹子
 * @since 2022/10/12
 */
public interface DictService extends IService<Dict> {

    /**
     * 获取字典分页列表
     *
     * @param queryParams 分页查询对象
     * @return 字典分页列表
     */
    Page<DictPageVO> getDictPage(DictPageQuery queryParams);

    /**
     * 获取字典列表
     *
     * @return 字典列表
     */
    List<Option<String>> getDictList();

    /**
     * 获取字典表单数据
     *
     * @param id 字典ID
     * @return 字典表单
     */
    DictForm getDictForm(Long id);

    /**
     * 新增字典
     *
     * @param dictForm 字典表单
     * @return 是否成功
     */
    boolean saveDict(DictForm dictForm);

    /**
     * 修改字典
     *
     * @param id       字典ID
     * @param dictForm 字典表单
     * @return 是否成功
     */
    boolean updateDict(Long id, DictForm dictForm);

    /**
     * 删除字典
     *
     * @param ids 字典ID集合
     */
    void deleteDictByIds(List<String> ids);

    /**
     * 根据字典ID列表获取字典编码列表
     *
     * @param ids 字典ID列表
     * @return 字典编码列表
     */
    List<String> getDictCodesByIds(List<String> ids);
}
