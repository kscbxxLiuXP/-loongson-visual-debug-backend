package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loongson.debug.entity.Head;
import com.loongson.debug.mapper.HeadMapper;
import com.loongson.debug.service.IHeadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuxp
 * @since 2022-02-28
 */
@Service
public class HeadServiceImpl extends ServiceImpl<HeadMapper, Head> implements IHeadService {

    @Autowired
    HeadMapper headMapper;

    @Override
    public int addHead(Head head) {
        int insert = headMapper.insert(head);
        return insert;
    }

    @Override
    public Head getHeadById(int ltid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ltid",ltid);
        Head head = headMapper.selectOne(queryWrapper);
        return head;
    }
}
