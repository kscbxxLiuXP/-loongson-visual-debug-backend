package com.loongson.debug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loongson.debug.dto.PatternDTO;
import com.loongson.debug.entity.LtlogInstructionPattern;
import com.loongson.debug.service.ILtlogInstructionPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liuxp
 * @since 2022-04-26
 */
@RestController
@CrossOrigin
@RequestMapping("/ltlogInstructionPattern")
public class LtlogInstructionPatternController {
    @Autowired
    ILtlogInstructionPatternService ltlogInstructionPatternService;
    @GetMapping("/getPatternsByOperator")
    public List<LtlogInstructionPattern> getPatternsByOperator(int ltid, String operator) {
        System.out.println(ltid+" "+operator);
        List<LtlogInstructionPattern> list = ltlogInstructionPatternService.list(new QueryWrapper<LtlogInstructionPattern>().eq("ltid", ltid).eq("operator", operator));
        return list;
    }

}
