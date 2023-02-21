package com.caterpillar.zyzshop.controller;

import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.domain.User;
import com.caterpillar.zyzshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping
    public Result login(@RequestBody User user){
        return userService.login(user);
    }
}
