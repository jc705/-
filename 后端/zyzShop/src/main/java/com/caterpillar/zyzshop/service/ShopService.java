package com.caterpillar.zyzshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.domain.Shop;

import java.util.List;


public interface ShopService extends IService<Shop> {
    public Result getAll();
    public Result getElectronicList();
    public Result getClothingList();
    public Result getOthersList();
    public Result getById(Long id);

    public Result insert(Shop shop);

    public Result delId(Long id);

    public Result getByLikes(String str,Integer page);
}
