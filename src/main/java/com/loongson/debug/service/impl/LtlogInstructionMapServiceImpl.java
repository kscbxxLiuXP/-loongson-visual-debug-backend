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

import java.util.ArrayList;
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
    public IPage<LtlogInstructionMap> selectByPage(String operator, String order, int ltid, int currentPage, int limit) {
        QueryWrapper<LtlogInstructionMap> wrapper = new QueryWrapper<>();
        wrapper.eq("ltid", ltid);
        if (order.equals("asc")) {
            wrapper.orderByAsc("num");
        } else {
            wrapper.orderByDesc("num");
        }
        if (operator != "") {
            wrapper.eq("operator", operator);
        }
        Page<LtlogInstructionMap> page = new Page<>(currentPage, limit);
        IPage<LtlogInstructionMap> IPage = ltlogInstructionMapMapper.selectPage(page, wrapper);
        wrapper.select("sum(num) as sum");
        LtlogInstructionMap ltlogInstructionMap = ltlogInstructionMapMapper.selectOne(wrapper);
        IPage.getRecords().get(0).setSum(ltlogInstructionMap.getSum());
        return IPage;
    }

    @Override
    public List<LtlogInstructionMap> getLtlogInstructionMapsComboed(int ltid) {
        return ltlogInstructionMapMapper.getLtlogInstructionMapsComboed(ltid);
    }

    @Override
    public List<LtlogInstructionMap> getChartData(int ltid) {
        QueryWrapper<LtlogInstructionMap> wrapper = new QueryWrapper<>();
        wrapper.select("operator", "operand", "num").eq("ltid", ltid).orderByDesc("num");
        return ltlogInstructionMapMapper.selectList(wrapper);
    }

    @Override
    public List<String> getInstructionTypes(int ltid) {
        ArrayList<String> instructionTypes = new ArrayList<>();
        QueryWrapper<LtlogInstructionMap> wrapper = new QueryWrapper<>();
        wrapper.select("operator").groupBy("operator");
        List<LtlogInstructionMap> ltlogInstructionMapList = ltlogInstructionMapMapper.selectList(wrapper);
        for (LtlogInstructionMap ltlogInstructionMap : ltlogInstructionMapList) {
            instructionTypes.add(ltlogInstructionMap.getOperator());
        }

        return instructionTypes;
    }
}
