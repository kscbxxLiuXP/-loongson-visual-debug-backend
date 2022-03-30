package com.loongson.debug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.entity.LtLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuxp
 * @since 2022-02-28
 */
public interface ILtLogService extends IService<LtLog> {

    public void createNewLog(LtLog ltLog);

    public int updateLog(LtLog ltLog);

    IPage<LtLog> selectByPage(String username, int currentPage, int limit);

    public List<LtLog> getLtLogsByUsername(String username);

    public LtLog getLtLogsById(int ltid);

    public int deleteLtLog(int ltid);
}
