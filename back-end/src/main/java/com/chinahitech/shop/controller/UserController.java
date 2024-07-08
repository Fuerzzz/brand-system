package com.chinahitech.shop.controller;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.service.UserService;
import com.chinahitech.shop.utils.JwtUtils;
import com.chinahitech.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            String token = JwtUtils.generateToken(loginUser.getUsername());
            return Result.ok().data("token", token);
        } else {
            return Result.error().message("登录失败");
        }
    }

    @GetMapping("/info")
    public Result info(String token){
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        String url = "https://img.pconline.com.cn/images/upload/upc/tx/photoblog/1901/10/c7/127760406_1547120813172_mthumb.jpg";
        return Result.ok().data("name",username).data("avatar",url);
    }

    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }
}
