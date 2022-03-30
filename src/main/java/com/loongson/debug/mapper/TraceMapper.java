package com.loongson.debug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loongson.debug.entity.Trace;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuxp
 * @since 2022-03-17
 */
@Mapper
public interface TraceMapper extends BaseMapper<Trace> {

}
