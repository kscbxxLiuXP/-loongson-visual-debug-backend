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
import java.util.HashMap;
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
    public HashMap<String, Object> selectByPage(String operator, String orderby, String order, int ltid, int currentPage, int limit) {
        QueryWrapper<LtlogInstructionMap> wrapper = new QueryWrapper<>();
        QueryWrapper<LtlogInstructionMap> wrapper2= new QueryWrapper<>();
        wrapper.select("*, num * ir2num as ir2execute");
        wrapper2.eq("ltid",ltid);
        wrapper.eq("ltid", ltid);
        if (order.equals("asc")) {
            wrapper.orderByAsc(orderby);
        } else {
            wrapper.orderByDesc(orderby);
        }
        if (operator != "") {
            wrapper.eq("operator", operator);
            wrapper2.eq("operator",operator);
        }
        Page<LtlogInstructionMap> page = new Page<>(currentPage, limit);
        IPage<LtlogInstructionMap> IPage = ltlogInstructionMapMapper.selectPage(page, wrapper);


        wrapper2.select("sum(num) as sumir1, sum(num*ir2num) as sumir2");
        LtlogInstructionMap ltlogInstructionMap = ltlogInstructionMapMapper.selectOne(wrapper2);

        List<LtlogInstructionMap> ltlogInstructionMapList = IPage.getRecords();
        long pages = IPage.getPages();

        HashMap<String, Object> returnRes = new HashMap<>();
        returnRes.put("records", ltlogInstructionMapList);
        returnRes.put("total", IPage.getTotal());
        returnRes.put("sumir1", ltlogInstructionMap.getSumir1());
        returnRes.put("sumir2", ltlogInstructionMap.getSumir2());
        returnRes.put("pages", pages);
        return returnRes;
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
