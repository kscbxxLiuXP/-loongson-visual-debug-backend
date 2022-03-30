package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loongson.debug.entity.Trace;
import com.loongson.debug.mapper.TraceMapper;
import com.loongson.debug.service.ITraceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuxp
 * @since 2022-03-17
 */
@Service
public class TraceServiceImpl extends ServiceImpl<TraceMapper, Trace> implements ITraceService {

}
