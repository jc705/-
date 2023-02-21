package com.caterpillar.zyzshop.service;

import com.caterpillar.zyzshop.domain.Division;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface DivisionService extends IService<Division> {
    public String getDivision(Integer id);
}
