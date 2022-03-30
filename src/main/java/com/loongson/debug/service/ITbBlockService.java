package com.loongson.debug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.dto.TraceItem;
import com.loongson.debug.entity.TbBlock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuxp
 * @since 2022-02-28
 */
public interface ITbBlockService extends IService<TbBlock> {

    List<TbBlock> getTbBlocks(int ltid);

    List<TbBlock> getTbBlocksSimple(int ltid);

    IPage<TbBlock> selectByPage(int id, int currentPage, int limit);//定义分页功能

    TbBlock getTbBlockByStartAddress(String address, int ltid);

    List<TbBlock> getTbBlocksByAddresses(Set<String> addresses, int ltid);

    Map<String, TraceItem> getStringTbBlockTraceItemMap(int ltid);
}
