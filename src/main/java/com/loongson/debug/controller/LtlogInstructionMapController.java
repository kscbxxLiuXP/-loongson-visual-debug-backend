package com.loongson.debug.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.entity.LtlogInstructionMap;
import com.loongson.debug.service.ILtlogInstructionMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getAll")
    public HashMap<String, Object> getLtlogInstructionMaps(String operator,String orderby,String order,int ltid, int currentPage, int limit) {


        return  ltlogInstructionMapService.selectByPage(operator,orderby,order,ltid, currentPage, limit);
    }

    @GetMapping("/getCombo")

    public List<LtlogInstructionMap> getLtlogInstructionMapsComboed(int ltid) {

        return ltlogInstructionMapService.getLtlogInstructionMapsComboed(ltid);
    }

    @GetMapping("/getChart")
    public List<LtlogInstructionMap> getChartData(int ltid) {
        return ltlogInstructionMapService.getChartData(ltid);
    }

    @GetMapping("/getInstructionTypes")
    public List<String> getInstructionTypes(int ltid){
        return ltlogInstructionMapService.getInstructionTypes(ltid);
    }
}
