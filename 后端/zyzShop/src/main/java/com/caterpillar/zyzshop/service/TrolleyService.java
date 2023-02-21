package com.caterpillar.zyzshop.service;

import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.domain.Trolley;
import com.baomidou.mybatisplus.extension.service.IService;


public interface TrolleyService extends IService<Trolley> {
    public Result getCartList(Long id);
    public Result updataById(Trolley trolley);
    public Result deleteById(Trolley trolley);

    public Result saveOne(Trolley trolley);

    public Result deleteAll(Long id);
    public Result updateStaticAll(Trolley trolley);
    public Result updateStaticById(Trolley trolley);
}
