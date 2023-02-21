package com.caterpillar.zyzshop.dao;

import com.caterpillar.zyzshop.domain.Like;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 * @author 想写程序的毛毛虫
 * @since 2023-01-30
 */
public interface LikeDao extends BaseMapper<Like> {
    @Select("select count(sid) from tb_like where uid = #{id}")
    public Integer getlen(Long id);

}
