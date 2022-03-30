package com.loongson.debug.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.entity.LtLog;
import com.loongson.debug.service.ILtLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class LtLogController {
    @Autowired
    ILtLogService ltLogService;

    @GetMapping("/history")
    public HashMap<String, Object>  getLtLogsByUsername(String username, int currentPage, int limit) {
        IPage<LtLog> ltLogIPage = ltLogService.selectByPage(username, currentPage, limit);
        List<LtLog> ltLogs = ltLogIPage.getRecords();
        long pages = ltLogIPage.getPages();


        HashMap<String, Object> returnRes = new HashMap<>();

        returnRes.put("records", ltLogs);
        returnRes.put("total", ltLogIPage.getTotal());
        returnRes.put("pages", pages);
        return returnRes;
    }

    @GetMapping("/deleteltid")
    public String deleteLtlog(int ltid) {
        ltLogService.deleteLtLog(ltid);
        return "Success!";
    }

}
