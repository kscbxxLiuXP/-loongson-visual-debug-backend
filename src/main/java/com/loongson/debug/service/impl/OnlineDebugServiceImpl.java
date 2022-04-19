package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongson.debug.entity.LtLog;
import com.loongson.debug.entity.OnlineDebug;
import com.loongson.debug.entity.User;
import com.loongson.debug.mapper.OnlineDebugMapper;
import com.loongson.debug.service.IOnlineDebugService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuxp
 * @since 2022-04-11
 */
@Service
public class OnlineDebugServiceImpl extends ServiceImpl<OnlineDebugMapper, OnlineDebug> implements IOnlineDebugService {
    @Autowired
    OnlineDebugMapper onlineDebugMapper;

    @Override
    public List<OnlineDebug> available() {
        return onlineDebugMapper.getAvailable();
    }

    @Override
    public IPage<OnlineDebug> selectByPage(int currentPage, int limit) {
        QueryWrapper<OnlineDebug> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("createTime");
        Page<OnlineDebug> page = new Page<>(currentPage, limit);
        IPage<OnlineDebug> IPage = onlineDebugMapper.selectPage(page, wrapper);
        return IPage;
    }
}
