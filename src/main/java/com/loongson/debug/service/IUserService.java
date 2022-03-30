package com.loongson.debug.service;

import com.loongson.debug.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuxp
 * @since 2022-02-28
 */
public interface IUserService extends IService<User> {

    int login(String username,String password);

    User getByUsername(String username);
}
