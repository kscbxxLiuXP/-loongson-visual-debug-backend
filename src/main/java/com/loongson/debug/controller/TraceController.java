package com.loongson.debug.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.loongson.debug.dto.TraceDTO;
import com.loongson.debug.entity.LtLog;
import com.loongson.debug.entity.Trace;
import com.loongson.debug.service.ILtLogService;
import com.loongson.debug.service.ITraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liuxp
 * @since 2022-03-17
 */
@RestController
@RequestMapping("/trace")
@CrossOrigin
public class TraceController {
    @Autowired
    ILtLogService ltLogService;
    @Autowired
    ITraceService traceService;

    @GetMapping("/get")
    public TraceDTO getTrace(int id) {
        Trace trace = traceService.getById(id);
        return new TraceDTO(trace);
    }

    @GetMapping("/delete")
    public String deleteTrace(int ltid, int traceid) {
        UpdateWrapper<LtLog> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid", ltid).set("traced", false).set("traceid", null);
        ltLogService.update(null, updateWrapper);

        traceService.removeById(traceid);
        return "Success";
    }
}

