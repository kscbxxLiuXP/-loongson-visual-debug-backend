package com.loongson.debug.service;

import com.loongson.debug.entity.Head;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuxp
 * @since 2022-02-28
 */
public interface IHeadService extends IService<Head> {

    public int addHead(Head head);

    public Head getHeadById(int ltid);
}
