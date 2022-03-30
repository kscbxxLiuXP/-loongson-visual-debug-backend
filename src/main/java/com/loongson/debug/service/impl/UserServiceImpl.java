package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loongson.debug.entity.User;
import com.loongson.debug.mapper.UserMapper;
import com.loongson.debug.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuxp
 * @since 2022-02-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int login(String username, String password) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return 1;
        } else if (!user.getPassword().equals(password)) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public User getByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
       queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}
