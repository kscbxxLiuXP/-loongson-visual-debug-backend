package com.loongson.debug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.entity.LtLog;
import com.loongson.debug.entity.LtlogAnalysis;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuxp
 * @since 2022-04-18
 */
public interface ILtLogAnalysisService extends IService<LtlogAnalysis> {
    IPage<LtlogAnalysis> selectByPage( int currentPage, int limit);
}
