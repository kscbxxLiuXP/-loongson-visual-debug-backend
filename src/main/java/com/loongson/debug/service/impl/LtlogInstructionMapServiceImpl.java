package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongson.debug.entity.LtlogInstructionMap;
import com.loongson.debug.mapper.LtlogInstructionMapMapper;
import com.loongson.debug.service.ILtlogInstructionMapService;
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
 * @since 2022-04-18
 */
@Service
public class LtlogInstructionMapServiceImpl extends ServiceImpl<LtlogInstructionMapMapper, LtlogInstructionMap> implements ILtlogInstructionMapService {
    @Autowired
    LtlogInstructionMapMapper ltlogInstructionMapMapper;


    @Override
    public void updateBatch(List<LtlogInstructionMap> ltlogInstructionMapList) {
            ltlogInstructionMapMapper.updateBatch(ltlogInstructionMapList);
    }

    @Override
    public IPage<LtlogInstructionMap> selectByPage(int ltid, int currentPage, int limit) {
        QueryWrapper<LtlogInstructionMap> wrapper = new QueryWrapper<>();
        wrapper.eq("ltid", ltid).orderByDesc("num");
        Page<LtlogInstructionMap> page = new Page<>(currentPage, limit);
        IPage<LtlogInstructionMap> IPage = ltlogInstructionMapMapper.selectPage(page, wrapper);
        return IPage;
    }
}
