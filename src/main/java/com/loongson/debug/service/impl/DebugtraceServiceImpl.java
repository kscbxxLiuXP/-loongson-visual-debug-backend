package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.loongson.debug.dto.DebugTraceDTO;
import com.loongson.debug.entity.DebugTrace;
import com.loongson.debug.mapper.DebugtraceMapper;
import com.loongson.debug.service.IDebugtraceService;
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
 * @since 2022-04-11
 */
@Service
public class DebugtraceServiceImpl extends ServiceImpl<DebugtraceMapper, DebugTrace> implements IDebugtraceService {
    @Autowired
    DebugtraceMapper debugtraceMapper;

    @Override
    public void setIndirectTo(int debugid, String from, String to) {
        UpdateWrapper<DebugTrace> updateWrapper = new UpdateWrapper<DebugTrace>();
        updateWrapper.eq("debugid", debugid).eq("address", from).set("indirectTo", to);
        debugtraceMapper.update(null, updateWrapper);
    }

    @Override
    public void setSkipTo(int debugid, String from, String to) {
        UpdateWrapper<DebugTrace> updateWrapper = new UpdateWrapper<DebugTrace>();
        updateWrapper.eq("debugid", debugid).eq("address", from).set("skipTo", to);
        debugtraceMapper.update(null, updateWrapper);
    }

    @Override
    public List<DebugTraceDTO> getAllTrace(int debugid) {
        QueryWrapper<DebugTrace> debugTraceQueryWrapper = new QueryWrapper<>();
        debugTraceQueryWrapper.eq("debugid", debugid);

        List<DebugTrace> debugTraces = debugtraceMapper.selectList(debugTraceQueryWrapper);
        List<DebugTraceDTO> debugTraceDTOS = new ArrayList<>();
        for (DebugTrace debugTrace : debugTraces) {
            debugTraceDTOS.add(new DebugTraceDTO(debugTrace));
        }

        return debugTraceDTOS;
    }
}
