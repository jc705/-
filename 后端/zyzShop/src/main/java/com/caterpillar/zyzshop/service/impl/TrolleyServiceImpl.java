package com.caterpillar.zyzshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caterpillar.zyzshop.controller.Code.Codes;
import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.dao.ShopDao;
import com.caterpillar.zyzshop.dao.UserDao;
import com.caterpillar.zyzshop.domain.Cart;
import com.caterpillar.zyzshop.domain.Shop;
import com.caterpillar.zyzshop.domain.Trolley;
import com.caterpillar.zyzshop.dao.TrolleyDao;
import com.caterpillar.zyzshop.domain.User;
import com.caterpillar.zyzshop.service.TrolleyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrolleyServiceImpl extends ServiceImpl<TrolleyDao, Trolley> implements TrolleyService {
    @Autowired
    private TrolleyDao trolleyDao;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Result getCartList(Long id) {
        LambdaQueryWrapper<Trolley> trolley = new LambdaQueryWrapper<>();
        trolley.eq(Trolley::getUid,id);
        List<Trolley> trolleys = trolleyDao.selectList(trolley);
        if(trolleys.isEmpty())
            return new Result(Codes.GET_ERR,"购物车为空");
        List<Cart> carts = new ArrayList<>();
        for (Trolley e:trolleys) {
            Shop shop = shopDao.selectById(e.getSid());
            Cart cart = new Cart();
            cart.setUid(e.getUid());
            cart.setSid(e.getSid());
            cart.setStatics(e.getStatics());
            cart.setTcount(e.getTcount());
            cart.setProduct(shop.getProduct());
            cart.setMoney(shop.getMoney());
            cart.setPic(shop.getPic());
            carts.add(cart);
        }
        return new Result(Codes.GET_OK,carts,"列表获取成功");
    }

    @Override
    public Result updataById(Trolley trolley) {
        LambdaQueryWrapper<Trolley> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Trolley::getUid,trolley.getUid()).eq(Trolley::getSid,trolley.getSid());
        Trolley trolley1 = trolleyDao.selectOne(queryWrapper);
        System.out.println(trolley1);
        if (trolley1 == null)
            return new Result(Codes.UPDATE_ERR,"当前商品不存在");
        trolleyDao.update(trolley,queryWrapper);
        return new Result(Codes.UPDATE_OK,"修改成功");
    }

    @Override
    public Result deleteById(Trolley trolley) {
        LambdaQueryWrapper<Trolley> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Trolley::getUid,trolley.getUid()).eq(Trolley::getSid,trolley.getSid());
        Trolley trolley1 = trolleyDao.selectOne(queryWrapper);
        if (trolley1 == null)
            return new Result(Codes.DELECT_ERR,"当前商品不存在");
        trolleyDao.delete(queryWrapper);
        return new Result(Codes.DELECT_OK,"删除成功");
    }

    @Override
    public Result saveOne(Trolley trolley){
        LambdaQueryWrapper<Trolley> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Trolley::getUid,trolley.getUid()).eq(Trolley::getSid,trolley.getSid());
        List<Trolley> trolleys = trolleyDao.selectList(queryWrapper);

        if(!trolleys.isEmpty())
            return new Result(Codes.INSERT_ERR,"您的购物车已存在该商品");

        LambdaQueryWrapper<User> existence1 = new LambdaQueryWrapper<>();
        existence1.eq(User::getUid,trolley.getUid());
        List<User> users = userDao.selectList(existence1);

        if(users.isEmpty())
            return new Result(Codes.INSERT_USER_ERR,"用户不存在");

        LambdaQueryWrapper<Shop> existence2 = new LambdaQueryWrapper<>();
        existence2.eq(Shop::getSid,trolley.getSid());
        List<Shop> shops = shopDao.selectList(existence2);
        if(shops.isEmpty())
            return new Result(Codes.INSERT_SHOP_ERR,"商品不存在");

        trolleyDao.insert(trolley);
        return new Result(Codes.INSERT_OK,"添加成功");
    }

    @Override
    public Result deleteAll(Long id) {
        User user = userDao.selectById(id);
        if(user == null)
            return new Result(Codes.DELECT_ERR,"用户不存在");
        LambdaQueryWrapper<Trolley> del = new LambdaQueryWrapper<>();
        del.eq(Trolley::getUid,id);
        trolleyDao.delete(del);
        return new Result(Codes.DELECT_OK,"已清空购物车");
    }

    @Override
    public Result updateStaticAll(Trolley trolley) {
        Long id = trolley.getUid();
        Integer check = trolley.getStatics();
        User user = userDao.selectById(id);
        if(user == null)
            return new Result(Codes.UPDATE_ERR,"用户不存在");
        if(check != 1 && check != 0)
            return new Result(Codes.UPDATE_ERR,"参数只有1和0");
        trolleyDao.updateStaticsAll(check,id);
        return new Result(Codes.UPDATE_OK,"修改成功");
    }

    @Override
    public Result updateStaticById(Trolley trolley) {
        LambdaQueryWrapper<Trolley> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Trolley::getUid,trolley.getUid()).eq(Trolley::getSid,trolley.getSid());
        Trolley trolley1 = trolleyDao.selectOne(queryWrapper);
        if (trolley1 == null)
            return new Result(Codes.UPDATE_ERR,"当前商品不存在");
        trolleyDao.update(trolley,queryWrapper);
        return new Result(Codes.UPDATE_OK,"修改成功");
    }
}
