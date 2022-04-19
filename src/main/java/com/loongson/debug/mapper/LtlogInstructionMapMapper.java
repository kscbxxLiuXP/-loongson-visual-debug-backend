package com.loongson.debug.mapper;

import com.loongson.debug.entity.LtlogInstructionMap;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuxp
 * @since 2022-04-18
 */
@Mapper
public interface LtlogInstructionMapMapper extends BaseMapper<LtlogInstructionMap> {
    List<LtlogInstructionMap> getLtlogInstructionMapsComboed( int ltid);
 }
