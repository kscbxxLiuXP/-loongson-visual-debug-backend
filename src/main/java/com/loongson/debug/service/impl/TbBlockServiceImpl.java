package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongson.debug.dto.TraceItem;
import com.loongson.debug.entity.TbBlock;
import com.loongson.debug.mapper.TbBlockMapper;
import com.loongson.debug.service.ITbBlockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuxp
 * @since 2022-02-28
 */
@Service
public class TbBlockServiceImpl extends ServiceImpl<TbBlockMapper, TbBlock> implements ITbBlockService {

    @Autowired
    TbBlockMapper tbBlockMapper;

    @Override
    public List<TbBlock> getTbBlocks(int ltid) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ltid", ltid);
        List list = tbBlockMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<TbBlock> getTbBlocksSimple(int ltid) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tbindex", "TBAddress", "startAddressIR1", "endAddressIR1", "startAddressIR2", "endAddressIR2", "IR1Num", "IR2Num");
        queryWrapper.eq("ltid", ltid);
        List list = tbBlockMapper.selectList(queryWrapper);
        return list;

    }

    @Override
    public IPage<TbBlock> selectByPage(int id, int currentPage, int limit) {
        QueryWrapper<TbBlock> wrapper = new QueryWrapper<>();
        wrapper.eq("ltid", id);
        Page<TbBlock> page = new Page<>(currentPage, limit);
        IPage<TbBlock> IPage = tbBlockMapper.selectPage(page, wrapper);
        return IPage;
    }

    @Override
    public TbBlock getTbBlockByStartAddress(String address, int ltid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("tbindex", "TBAddress", "startAddressIR1", "endAddressIR1", "startAddressIR2", "endAddressIR2", "IR1Num", "IR2Num");
        queryWrapper.eq("ltid", ltid);
        queryWrapper.eq("startAddressIr1", address);


        TbBlock tbBlock = tbBlockMapper.selectOne(queryWrapper);
        return tbBlock;
    }

    @Override
    public List<TbBlock> getTbBlocksByAddresses(Set<String> addresses, int ltid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("ltid", "tbindex", "TBAddress", "startAddressIR1", "endAddressIR1", "startAddressIR2", "endAddressIR2", "IR1Num", "IR2Num");
        queryWrapper.eq("ltid", ltid);
        queryWrapper.in("startAddressIr2", addresses);


        List list = tbBlockMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Map<String, TraceItem> getStringTbBlockTraceItemMap(int ltid) {
        Map<String, TraceItem> map = new HashMap<>();
        QueryWrapper<TbBlock> queryWrapper = new QueryWrapper<TbBlock>();
        queryWrapper.select("ltid", "tbindex", "TBAddress", "startAddressIR1", "endAddressIR1", "startAddressIR2", "endAddressIR2", "IR1Num", "IR2Num");
        queryWrapper.eq("ltid", ltid);

        List<TbBlock> list = tbBlockMapper.selectList(queryWrapper);

        for (TbBlock tbBlock : list) {
            TraceItem traceItem = new TraceItem(tbBlock);
            map.put(traceItem.getAddress(), traceItem);
        }

        return map;

    }
}
