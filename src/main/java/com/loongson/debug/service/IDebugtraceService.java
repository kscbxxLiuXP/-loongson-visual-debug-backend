package com.loongson.debug.service;

import com.loongson.debug.dto.DebugTraceDTO;
import com.loongson.debug.entity.DebugTrace;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuxp
 * @since 2022-04-11
 */
public interface IDebugtraceService extends IService<DebugTrace> {
    //设置父节点的间接跳转
    void setIndirectTo(int debugid,String from,String to);

    void setSkipTo(int debugid,String from,String to);
    //获取全部的trace
    List<DebugTraceDTO> getAllTrace(int debugid);
}
