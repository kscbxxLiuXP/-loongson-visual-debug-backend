package com.loongson.debug.mapper;

import com.loongson.debug.dto.LtlogInstructionMapDTO;
import com.loongson.debug.dto.PatternDTO;
import com.loongson.debug.dto.PatternFilter;
import com.loongson.debug.entity.LtlogInstructionMap;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loongson.debug.entity.LtlogInstructionPattern;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuxp
 * @since 2022-04-18
 */
@Mapper
public interface LtlogInstructionMapMapper extends BaseMapper<LtlogInstructionMap> {
    List<LtlogInstructionPattern> getLtlogInstructionMapsComboed(int ltid);

    List<Object> getLtlogInstructionMapsAll(String operator, String orderby, String order, int ltid, int limitlow, int limit,List<String> notInList,List<String> hiddenOperator);

    int getFoundRows();
}
