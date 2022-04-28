package com.loongson.debug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.dto.LtlogInstructionMapDTO;
import com.loongson.debug.dto.PatternDTO;
import com.loongson.debug.dto.QueryInstructionAllDTO;
import com.loongson.debug.entity.LtlogInstructionMap;
import com.baomidou.mybatisplus.extension.service.IService;
import com.loongson.debug.entity.LtlogInstructionPattern;
import com.loongson.debug.entity.TBAnalysis;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuxp
 * @since 2022-04-18
 */
public interface ILtlogInstructionMapService extends IService<LtlogInstructionMap> {

    HashMap<String, Object> selectByPage(String operator, String orderby, String order, int ltid, int currentPage, int limit);

    List<LtlogInstructionPattern> getLtlogInstructionMapsComboed(int ltid);

    List<LtlogInstructionMap> getChartData(int ltid);

    List<String> getInstructionTypes(int ltid);

    HashMap<String, Object> getLtlogInstructionMapsAll(QueryInstructionAllDTO queryInstructionAllDTO);

    HashMap<String, Object> getLtlogInstructionMapsAllPatterned(QueryInstructionAllDTO queryInstructionAllDTO);


}
