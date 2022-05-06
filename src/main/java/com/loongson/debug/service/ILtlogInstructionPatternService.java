package com.loongson.debug.service;

import com.loongson.debug.dto.PatternDTO;
import com.loongson.debug.entity.LtlogInstructionPattern;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuxp
 * @since 2022-04-26
 */
public interface ILtlogInstructionPatternService extends IService<LtlogInstructionPattern> {
    List<LtlogInstructionPattern> getPatterns(int ltid);
}
