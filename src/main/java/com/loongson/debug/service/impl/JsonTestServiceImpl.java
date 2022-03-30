package com.loongson.debug.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loongson.debug.entity.JsonTest;
import com.loongson.debug.mapper.JsonTestMapper;
import com.loongson.debug.service.IJsonTestService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuxp
 * @since 2022-03-14
 */
@Service
public class JsonTestServiceImpl extends ServiceImpl<JsonTestMapper, JsonTest> implements IJsonTestService {

}
