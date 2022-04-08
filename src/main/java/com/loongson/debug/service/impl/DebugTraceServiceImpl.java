package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loongson.debug.entity.DebugTrace;
import com.loongson.debug.mapper.DebugTraceMapper;
import com.loongson.debug.service.IDebugTraceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuxp
 * @since 2022-03-17
 */
@Service
public class DebugTraceServiceImpl extends ServiceImpl<DebugTraceMapper, DebugTrace> implements IDebugTraceService {

}
