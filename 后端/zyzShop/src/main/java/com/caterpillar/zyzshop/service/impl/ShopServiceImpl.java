package com.caterpillar.zyzshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caterpillar.zyzshop.controller.Code.Codes;
import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.dao.ShopDao;
import com.caterpillar.zyzshop.domain.NewShop;
import com.caterpillar.zyzshop.domain.User;
import com.caterpillar.zyzshop.service.DivisionService;
import com.caterpillar.zyzshop.service.ShopService;
import com.caterpillar.zyzshop.domain.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopDao, Shop> implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Autowired
    private DivisionService divisionService;

    @Override
    public Result getAll() {
        List<Shop> shops = shopDao.selectList(null);
        if(shops.isEmpty()){
            return new Result(Codes.GET_ERR,"获取数据为空");
        }
        List<NewShop> newShops = new ArrayList<>();
        for (Shop shop:shops) {
            NewShop newShop = new NewShop();
            newShop.setSid(shop.getSid());
            newShop.setDivision(divisionService.getDivision(shop.getDivision()));
            newShop.setIntroduction(shop.getIntroduction());
            newShop.setMoney(shop.getMoney());
            newShop.setSclass(shop.getSclass());
            newShop.setProduct(shop.getProduct());
            newShop.setUname(shop.getUname());
            newShop.setPic(shop.getPic());
            newShops.add(newShop);
        }
        return new Result(Codes.GET_OK,newShops,"获取列表成功");
    }

    @Override
    public Result getElectronicList() {
        LambdaQueryWrapper<Shop> electronic = new LambdaQueryWrapper<>();
        electronic.eq(Shop::getDivision,1);
        List<Shop> shops = shopDao.selectList(electronic);
        if(shops.isEmpty()){
            return new Result(Codes.GET_ERR,"获取数据为空");
        }
        List<NewShop> newShops = new ArrayList<>();
        for (Shop shop:shops) {
            NewShop newShop = new NewShop();
            newShop.setSid(shop.getSid());
            newShop.setDivision(divisionService.getDivision(shop.getDivision()));
            newShop.setIntroduction(shop.getIntroduction());
            newShop.setMoney(shop.getMoney());
            newShop.setSclass(shop.getSclass());
            newShop.setProduct(shop.getProduct());
            newShop.setUname(shop.getUname());
            newShop.setPic(shop.getPic());
            newShops.add(newShop);
        }
        return new Result(Codes.GET_OK,newShops,"获取列表成功");
    }

    @Override
    public Result getClothingList() {
        LambdaQueryWrapper<Shop> clothing = new LambdaQueryWrapper<>();
        clothing.eq(Shop::getDivision,2);
        List<Shop> shops = shopDao.selectList(clothing);
        if(shops.isEmpty()){
            return new Result(Codes.GET_ERR,"获取数据为空");
        }
        List<NewShop> newShops = new ArrayList<>();
        for (Shop shop:shops) {
            NewShop newShop = new NewShop();
            newShop.setSid(shop.getSid());
            newShop.setDivision(divisionService.getDivision(shop.getDivision()));
            newShop.setIntroduction(shop.getIntroduction());
            newShop.setMoney(shop.getMoney());
            newShop.setSclass(shop.getSclass());
            newShop.setProduct(shop.getProduct());
            newShop.setUname(shop.getUname());
            newShop.setPic(shop.getPic());
            newShops.add(newShop);
        }
        return new Result(Codes.GET_OK,newShops,"获取列表成功");
    }

    @Override
    public Result getOthersList() {
        LambdaQueryWrapper<Shop> others = new LambdaQueryWrapper<>();
        others.eq(Shop::getDivision,3);
        List<Shop> shops = shopDao.selectList(others);
        if(shops.isEmpty()){
            return new Result(Codes.GET_ERR,"获取数据为空");
        }
        List<NewShop> newShops = new ArrayList<>();
        for (Shop shop:shops) {
            NewShop newShop = new NewShop();
            newShop.setSid(shop.getSid());
            newShop.setDivision(divisionService.getDivision(shop.getDivision()));
            newShop.setIntroduction(shop.getIntroduction());
            newShop.setMoney(shop.getMoney());
            newShop.setSclass(shop.getSclass());
            newShop.setProduct(shop.getProduct());
            newShop.setUname(shop.getUname());
            newShop.setPic(shop.getPic());
            newShops.add(newShop);
        }
        return new Result(Codes.GET_OK,newShops,"获取列表成功");
    }

    @Override
    public Result getById(Long id) {
        LambdaQueryWrapper<Shop> sid = new LambdaQueryWrapper<>();
        sid.eq(Shop::getSid,id);
        Shop shop = shopDao.selectOne(sid);
        if(shop == null){
            return new Result(Codes.GET_ERR,"获取id不存在");
        }
            NewShop newShop = new NewShop();
            newShop.setSid(shop.getSid());
            newShop.setDivision(divisionService.getDivision(shop.getDivision()));
            newShop.setIntroduction(shop.getIntroduction());
            newShop.setMoney(shop.getMoney());
            newShop.setSclass(shop.getSclass());
            newShop.setProduct(shop.getProduct());
            newShop.setUname(shop.getUname());
            newShop.setPic(shop.getPic());

        return new Result(Codes.GET_OK,newShop,"获取数据成功");
    }

    @Override
    public Result insert(Shop shop) {
        if(shop.getUname() == null)
            return new Result(Codes.INSERT_ERR,"发布失败，请先登录");
        shopDao.insert(shop);
        return new Result(Codes.INSERT_OK,"发布成功");
    }

    @Override
    public Result delId(Long id) {
        Shop shop = shopDao.selectById(id);
        if(shop == null)
            return new Result(Codes.DELECT_ERR,"当前商品不存在");
        shopDao.deleteById(id);
        return new Result(Codes.DELECT_OK,"删除成功");
    }

    @Override
    public Result getByLikes(String str,Integer page) {
        LambdaQueryWrapper<Shop> lqw = new LambdaQueryWrapper<>();
        lqw.like(Shop::getProduct,str);
        Page<Shop> pages = new Page<>(page,4);
        shopDao.selectPage(pages, lqw);
        List<Shop> shops = pages.getRecords();
        if(shops.isEmpty())
            return new Result(Codes.GET_ERR,"没有相关商品");
        return new Result(Codes.GET_OK,shops,"查询成功");
    }
}
