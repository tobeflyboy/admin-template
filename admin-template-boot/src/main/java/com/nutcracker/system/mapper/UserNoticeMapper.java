package com.nutcracker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nutcracker.system.model.entity.UserNotice;
import com.nutcracker.system.model.query.NoticePageQuery;
import com.nutcracker.system.model.vo.NoticePageVO;
import com.nutcracker.system.model.vo.UserNoticePageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户公告状态Mapper接口
 *
 * @author 胡桃夹子
 * @since 2024-08-28 16:56
 */
@Mapper
public interface UserNoticeMapper extends BaseMapper<UserNotice> {
    /**
     * 分页获取我的通知公告
     *
     * @param page        分页对象
     * @param queryParams 查询参数
     * @return 通知公告分页列表
     */
    IPage<UserNoticePageVO> getMyNoticePage(Page<NoticePageVO> page, @Param("queryParams") NoticePageQuery queryParams);
}
