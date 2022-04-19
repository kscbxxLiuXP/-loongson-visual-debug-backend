package com.loongson.debug.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.entity.TBAnalysis;
import com.loongson.debug.service.ITbAnalysisService;
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
@RequestMapping("/tbAnalysis")
public class TbAnalysisController {
    @Autowired
    ITbAnalysisService tbAnalysisService;

    @GetMapping("/getAll")
    public HashMap<String, Object> getTbAnalyse(int ltid, int currentPage, int limit) {
        IPage<TBAnalysis> tbAnalysisIPage = tbAnalysisService.selectByPage(ltid, currentPage, limit);
        List<TBAnalysis> tbAnalyses = tbAnalysisIPage.getRecords();
        long pages = tbAnalysisIPage.getPages();

        HashMap<String, Object> returnRes = new HashMap<>();
        returnRes.put("records", tbAnalyses);
        returnRes.put("total", tbAnalysisIPage.getTotal());
        returnRes.put("pages", pages);
        return returnRes;
    }
}
