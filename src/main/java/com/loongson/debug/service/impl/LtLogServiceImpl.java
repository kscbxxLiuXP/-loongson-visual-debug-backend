package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongson.debug.entity.LtLog;
import com.loongson.debug.entity.User;
import com.loongson.debug.mapper.*;
import com.loongson.debug.service.ILtLogService;
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
 * @since 2022-02-28
 */
@Service
public class LtLogServiceImpl extends ServiceImpl<LtLogMapper, LtLog> implements ILtLogService {

    @Autowired
    LtLogMapper ltLogMapper;

    @Autowired
    HeadMapper headMapper;

    @Autowired
    TbBlockMapper tbBlockMapper;
    @Autowired
    TraceMapper traceMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void createNewLog(LtLog ltLog) {
        int insert = ltLogMapper.insert(ltLog);

    }

    @Override
    public int updateLog(LtLog ltLog) {
        int i = ltLogMapper.updateById(ltLog);
        return i;
    }

    @Override
    public IPage<LtLog> selectByPage(String username, int currentPage, int limit) {
        QueryWrapper queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("username", username);
        User user = userMapper.selectOne(queryWrapper1);

        QueryWrapper<LtLog> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", user.getUid());
        Page<LtLog> page = new Page<>(currentPage, limit);
        IPage<LtLog> IPage = ltLogMapper.selectPage(page, wrapper);
        return IPage;
    }

    @Override
    public List<LtLog> getLtLogsByUsername(String username) {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);

        QueryWrapper queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("userid", user.getUid());
        List list = ltLogMapper.selectList(queryWrapper1);
        return list;
    }

    @Override
    public LtLog getLtLogsById(int ltid) {
        LtLog ltLog = ltLogMapper.selectById(ltid);
        return ltLog;
    }

    @Override
    public int deleteLtLog(int ltid) {
        int i = ltLogMapper.deleteById(ltid);
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ltid", ltid);
        traceMapper.delete(queryWrapper);
        headMapper.delete(queryWrapper);
        tbBlockMapper.delete(queryWrapper);
        return i;
    }
}
