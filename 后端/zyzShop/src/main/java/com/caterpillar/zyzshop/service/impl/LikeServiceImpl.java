package com.caterpillar.zyzshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.caterpillar.zyzshop.controller.Code.Codes;
import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.dao.ShopDao;
import com.caterpillar.zyzshop.dao.UserDao;
import com.caterpillar.zyzshop.domain.*;
import com.caterpillar.zyzshop.dao.LikeDao;
import com.caterpillar.zyzshop.service.LikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LikeServiceImpl extends ServiceImpl<LikeDao, Like> implements LikeService {
    @Autowired
    private LikeDao likeDao;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private UserDao userDao;


    @Override
    public Result getLikes(Long id) {
        LambdaQueryWrapper<Like> like = new LambdaQueryWrapper<>();
        like.eq(Like::getUid,id);
        List<Like> likes = likeDao.selectList(like);
        if(likes.isEmpty())
            return new Result(Codes.GET_ERR,"收藏为空");
        List<Collect> collects = new ArrayList<>();
        for (Like e:likes) {
            Shop shop = shopDao.selectById(e.getSid());
            Collect collect = new Collect();
            collect.setUid(e.getUid());
            collect.setSid(e.getSid());
            collect.setProduct(shop.getProduct());
            collect.setMoney(shop.getMoney());
            collect.setPic(shop.getPic());
            collect.setSclass(shop.getSclass());
            collects.add(collect);
        }
        return new Result(Codes.GET_OK,collects,"列表获取成功");
    }

    @Override
    public Result saveLike(Like like) {
        LambdaQueryWrapper<Like> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Like::getUid,like.getUid()).eq(Like::getSid,like.getSid());
        List<Like> likes = likeDao.selectList(queryWrapper);

        if(!likes.isEmpty())
            return new Result(Codes.INSERT_ERR,"您的收藏已存在该商品");

        LambdaQueryWrapper<User> existence1 = new LambdaQueryWrapper<>();
        existence1.eq(User::getUid,like.getUid());
        List<User> users = userDao.selectList(existence1);

        if(users.isEmpty())
            return new Result(Codes.INSERT_USER_ERR,"用户不存在");

        LambdaQueryWrapper<Shop> existence2 = new LambdaQueryWrapper<>();
        existence2.eq(Shop::getSid,like.getSid());
        List<Shop> shops = shopDao.selectList(existence2);
        if(shops.isEmpty())
            return new Result(Codes.INSERT_SHOP_ERR,"商品不存在");

        likeDao.insert(like);
        return new Result(Codes.INSERT_OK,"添加成功");
    }

    @Override
    public Result removeLike(Like like) {
        LambdaQueryWrapper<Like> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Like::getUid,like.getUid()).eq(Like::getSid,like.getSid());
        Like like1 = likeDao.selectOne(queryWrapper);
        if (like1 == null)
            return new Result(Codes.DELECT_ERR,"当前商品不存在");
        likeDao.delete(queryWrapper);
        return new Result(Codes.DELECT_OK,"删除成功");
    }
}
