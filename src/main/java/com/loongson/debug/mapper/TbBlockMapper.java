package com.loongson.debug.mapper;

import com.loongson.debug.entity.TbBlock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuxp
 * @since 2022-02-28
 */
@Mapper
public interface TbBlockMapper extends BaseMapper<TbBlock> {
    List<TbBlock> getTbBlockContainAddress(int ltid,String address);
}
