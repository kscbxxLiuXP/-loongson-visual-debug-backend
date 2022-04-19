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
    public HashMap<String, Object> getLtlogInstructionMaps(int ltid, int currentPage, int limit) {
        IPage<LtlogInstructionMap> ltlogInstructionMapIPage = ltlogInstructionMapService.selectByPage(ltid, currentPage, limit);
        List<LtlogInstructionMap> ltlogInstructionMapList = ltlogInstructionMapIPage.getRecords();
        long pages = ltlogInstructionMapIPage.getPages();

        HashMap<String, Object> returnRes = new HashMap<>();
        returnRes.put("records", ltlogInstructionMapList);
        returnRes.put("total", ltlogInstructionMapIPage.getTotal());
        returnRes.put("pages", pages);
        return returnRes;
    }
}
