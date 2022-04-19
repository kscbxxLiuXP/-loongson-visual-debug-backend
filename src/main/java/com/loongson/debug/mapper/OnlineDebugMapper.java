package com.loongson.debug.mapper;

import com.loongson.debug.entity.OnlineDebug;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuxp
 * @since 2022-04-11
 */
@Mapper
public interface OnlineDebugMapper extends BaseMapper<OnlineDebug> {

    List<OnlineDebug> getAvailable();
}
