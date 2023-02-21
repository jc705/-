package com.caterpillar.zyzshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caterpillar.zyzshop.domain.Division;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;



public interface DivisionDao extends BaseMapper<Division> {
    @Select("select dname from tb_division where did = #{did}")
    public String getDivision(@Param("did") Integer id);
}
