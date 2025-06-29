package com.nutcracker.shared.codegen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nutcracker.shared.codegen.model.bo.ColumnMetaData;
import com.nutcracker.shared.codegen.model.bo.TableMetaData;
import com.nutcracker.shared.codegen.model.query.TablePageQuery;
import com.nutcracker.shared.codegen.model.vo.TablePageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 数据库映射层
 *
 * @author 胡桃夹子
 * @since 2.9.0
 */
@Mapper
public interface DatabaseMapper extends BaseMapper {

    /**
     * 获取表分页列表
     *
     * @param page
     * @param queryParams
     * @return
     */
    Page<TablePageVO> getTablePage(Page<TablePageVO> page, TablePageQuery queryParams);

    /**
     * 获取表字段列表
     *
     * @param tableName
     * @return
     */
    List<ColumnMetaData> getTableColumns(String tableName);

    /**
     * 获取表元数据
     *
     * @param tableName
     * @return
     */
    TableMetaData getTableMetadata(String tableName);
}
