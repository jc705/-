package com.caterpillar.zyzshop;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.dao.DivisionDao;
import com.caterpillar.zyzshop.dao.ShopDao;
import com.caterpillar.zyzshop.dao.TrolleyDao;
import com.caterpillar.zyzshop.dao.UserDao;
import com.caterpillar.zyzshop.domain.*;
import com.caterpillar.zyzshop.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ZyzShopApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ShopService shopService;
    @Autowired
    private DivisionDao divisionDao;
    @Autowired
    private DivisionService divisionService;
    @Autowired
    private TrolleyService trolleyService;
    @Autowired
    private TrolleyDao trolleyDao;
    @Autowired
    private LikeService likeService;


    @Test
    void text() {
        Like like = new Like();
        like.setUid(2L);
        like.setSid(1L);
        System.out.println(likeService.removeLike(like));
    }

}
