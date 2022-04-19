package com.loongson.debug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.entity.LtlogInstructionMap;
import com.baomidou.mybatisplus.extension.service.IService;
import com.loongson.debug.entity.TBAnalysis;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuxp
 * @since 2022-04-18
 */
public interface ILtlogInstructionMapService extends IService<LtlogInstructionMap> {
    void updateBatch(List<LtlogInstructionMap> ltlogInstructionMapList);
    IPage<LtlogInstructionMap> selectByPage(int ltid, int currentPage, int limit);
}
