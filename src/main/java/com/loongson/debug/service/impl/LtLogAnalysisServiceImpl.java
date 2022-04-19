package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongson.debug.entity.LtLog;
import com.loongson.debug.entity.LtlogAnalysis;
import com.loongson.debug.entity.User;
import com.loongson.debug.mapper.LtLogAnalysisMapper;
import com.loongson.debug.service.ILtLogAnalysisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuxp
 * @since 2022-04-18
 */
@Service
public class LtLogAnalysisServiceImpl extends ServiceImpl<LtLogAnalysisMapper, LtlogAnalysis> implements ILtLogAnalysisService {

    @Autowired
    LtLogAnalysisMapper ltLogAnalysisMapper;

    @Override
    public IPage<LtlogAnalysis> selectByPage(int currentPage, int limit) {

        QueryWrapper<LtlogAnalysis> wrapper = new QueryWrapper<>();

        Page<LtlogAnalysis> page = new Page<>(currentPage, limit);
        return ltLogAnalysisMapper.selectPage(page, wrapper);
    }
}
