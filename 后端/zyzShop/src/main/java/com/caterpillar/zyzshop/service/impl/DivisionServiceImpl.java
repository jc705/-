package com.caterpillar.zyzshop.service.impl;

import com.caterpillar.zyzshop.domain.Division;
import com.caterpillar.zyzshop.dao.DivisionDao;
import com.caterpillar.zyzshop.service.DivisionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisionServiceImpl extends ServiceImpl<DivisionDao, Division> implements DivisionService {

    @Autowired
    private DivisionDao divisionDao;

    @Override
    public String getDivision(Integer id) {
        String data = divisionDao.getDivision(id);
        return data;
    }
}
