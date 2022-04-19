package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongson.debug.entity.LtLog;
import com.loongson.debug.entity.TBAnalysis;
import com.loongson.debug.entity.User;
import com.loongson.debug.mapper.TbAnalysisMapper;
import com.loongson.debug.service.ITbAnalysisService;
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
public class TbAnalysisServiceImpl extends ServiceImpl<TbAnalysisMapper, TBAnalysis> implements ITbAnalysisService {
    @Autowired
    TbAnalysisMapper tbAnalysisMapper;

    @Override
    public IPage<TBAnalysis> selectByPage(int ltid, int currentPage, int limit) {

        QueryWrapper<TBAnalysis> wrapper = new QueryWrapper<>();
        wrapper.eq("ltid", ltid);
        Page<TBAnalysis> page = new Page<>(currentPage, limit);
        IPage<TBAnalysis> IPage = tbAnalysisMapper.selectPage(page, wrapper);
        return IPage;
    }
}
