package com.loongson.debug.controller;


import com.loongson.debug.entity.User;
import com.loongson.debug.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liuxp
 * @since 2022-02-28
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("login")
    public HashMap<String, Object> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println("username:" + username + "\tpassword:" + password);
        HashMap<String, Object> result = new HashMap<>();
        int r = userService.login(username, password);
        String message = "";
        if (r == 1) {
            message = "此用户不存在！";
        } else if (r == 2) {
            message = "用户名或密码错误！";
        } else if (r == 3) {
            message = "登陆成功";
        }
        result.put("result", r);
        result.put("message", message);

        return result;
    }
}

