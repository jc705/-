package com.caterpillar.zyzshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caterpillar.zyzshop.controller.Code.Codes;
import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.dao.LikeDao;
import com.caterpillar.zyzshop.dao.TrolleyDao;
import com.caterpillar.zyzshop.dao.UserDao;
import com.caterpillar.zyzshop.domain.User;
import com.caterpillar.zyzshop.domain.NewUser;
import com.caterpillar.zyzshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TrolleyDao trolleyDao;
    @Autowired
    private LikeDao likeDao;


    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUname,user.getUname()).eq(User::getUpassword,user.getUpassword());
        User u = userDao.selectOne(lqw);
        if (u == null)
            return new Result( Codes.LOGIN_ERR,u,"用户名或密码错误");
        int cartLen = trolleyDao.getlen(u.getUid());
        int likeLen = likeDao.getlen(u.getUid());

        NewUser newUser = new NewUser();
        newUser.setUid(u.getUid());
        newUser.setUname(u.getUname());
        newUser.setUpassword(u.getUpassword());
        newUser.setUemail(u.getUemail());
        newUser.setCart(cartLen);
        newUser.setLike(likeLen);
        return new Result( Codes.LOGIN_OK,newUser,"登录成功");
    }

    @Override
    public Result register(User user) {
        LambdaQueryWrapper<User> name = new LambdaQueryWrapper<>();
        name.eq(User::getUname,user.getUname());
        List<User> users = userDao.selectList(name);
        if (!users.isEmpty())
            return new Result(Codes.REGISTER_NAME_ERR,"该用户名已被注册");
        LambdaQueryWrapper<User> email = new LambdaQueryWrapper<>();
        email.eq(User::getUemail,user.getUemail());
        users = userDao.selectList(email);
        if (!users.isEmpty())
            return new Result(Codes.REGISTER_EAMIL_ERR,"该邮箱已被注册");
        userDao.insert(user);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUname,user.getUname()).eq(User::getUemail,user.getUemail());
        return new Result(Codes.REGISTER_OK,userDao.selectOne(queryWrapper),"注册成功");
    }
}
