package com.loongson.debug.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.dto.LtlogInstructionMapDTO;
import com.loongson.debug.dto.PatternDTO;
import com.loongson.debug.dto.QueryInstructionAllDTO;
import com.loongson.debug.entity.LtlogInstructionMap;
import com.loongson.debug.entity.LtlogInstructionPattern;
import com.loongson.debug.service.ILtlogInstructionMapService;
import com.loongson.debug.service.ILtlogInstructionPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liuxp
 * @since 2022-04-18
 */
@RestController
@CrossOrigin
@RequestMapping("/ltlogInstructionMap")
public class LtlogInstructionMapController {
    @Autowired
    ILtlogInstructionMapService ltlogInstructionMapService;
    @Autowired
    ILtlogInstructionPatternService ltlogInstructionPatternService;

    @GetMapping("/getPatterns")
    public List<LtlogInstructionPattern> getPatterns(int ltid) {
        return ltlogInstructionPatternService.getPatterns(ltid);

    }

    @PostMapping("/getAll")
    public HashMap<String, Object> getLtlogInstructionMapsAll(@RequestBody QueryInstructionAllDTO queryInstructionAllDTO) {
        return ltlogInstructionMapService.getLtlogInstructionMapsAll(queryInstructionAllDTO);
    }
    @PostMapping("/getSpecific")
    public HashMap<String, Object> getLtlogInstructionMapsSpecific(@RequestBody QueryInstructionAllDTO queryInstructionAllDTO) {
        return ltlogInstructionMapService.getLtlogInstructionMapsSpecific(queryInstructionAllDTO);
    }

    @PostMapping("/getAllPatterned")
    public HashMap<String, Object> getLtlogInstructionMapsAllPatterned(@RequestBody QueryInstructionAllDTO queryInstructionAllDTO) {
        return ltlogInstructionMapService.getLtlogInstructionMapsAllPatterned(queryInstructionAllDTO);
    }

    @GetMapping("/getAll1")
    public HashMap<String, Object> getLtlogInstructionMaps(String operator, String orderby, String order, int ltid, int currentPage, int limit) {


        return ltlogInstructionMapService.selectByPage(operator, orderby, order, ltid, currentPage, limit);
    }

    @GetMapping("/getCombo")

    public List<LtlogInstructionPattern> getLtlogInstructionMapsComboed(int ltid) {

        return ltlogInstructionMapService.getLtlogInstructionMapsComboed(ltid);
    }

    @GetMapping("/getChart")
    public List<LtlogInstructionMap> getChartData(int ltid) {
        return ltlogInstructionMapService.getChartData(ltid);
    }

    @GetMapping("/getInstructionTypes")
    public List<String> getInstructionTypes(int ltid) {
        return ltlogInstructionMapService.getInstructionTypes(ltid);
    }
}
