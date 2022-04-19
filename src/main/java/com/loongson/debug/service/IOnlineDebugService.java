package com.loongson.debug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.entity.OnlineDebug;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuxp
 * @since 2022-04-11
 */
public interface IOnlineDebugService extends IService<OnlineDebug> {
    List<OnlineDebug> available();
    IPage<OnlineDebug> selectByPage(int currentPage, int limit);

}
