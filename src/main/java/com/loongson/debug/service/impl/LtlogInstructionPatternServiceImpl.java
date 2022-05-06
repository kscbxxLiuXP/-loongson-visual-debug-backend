package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loongson.debug.dto.PatternDTO;
import com.loongson.debug.entity.LtlogInstructionPattern;
import com.loongson.debug.mapper.LtlogInstructionPatternMapper;
import com.loongson.debug.service.ILtlogInstructionPatternService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuxp
 * @since 2022-04-26
 */
@Service
public class LtlogInstructionPatternServiceImpl extends ServiceImpl<LtlogInstructionPatternMapper, LtlogInstructionPattern> implements ILtlogInstructionPatternService {
    @Autowired
    LtlogInstructionPatternMapper ltlogInstructionPatternMapper;

    @Override
    public List<LtlogInstructionPattern> getPatterns(int ltid) {
        return ltlogInstructionPatternMapper.selectList(new QueryWrapper<LtlogInstructionPattern>().eq("ltid", ltid));
    }
}
