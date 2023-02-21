package com.caterpillar.zyzshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.domain.User;


public interface UserService extends IService<User> {
    public Result login(User user);
    public Result register(User user);
}
