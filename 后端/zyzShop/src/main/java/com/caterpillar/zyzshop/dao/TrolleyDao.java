package com.caterpillar.zyzshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caterpillar.zyzshop.domain.Trolley;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface TrolleyDao extends BaseMapper<Trolley> {

    @Select("select count(sid) from tb_trolley where uid = #{id}")
    public Integer getlen(Long id);

    @Update("update tb_trolley set statics = #{check} where uid = #{id}")
    public void updateStaticsAll(Integer check,Long id);
}
