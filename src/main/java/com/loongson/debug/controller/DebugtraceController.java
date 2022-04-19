package com.loongson.debug.controller;


import com.loongson.debug.dto.DebugTraceDTO;
import com.loongson.debug.service.IDebugtraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuxp
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/debug/debugtrace")
@CrossOrigin
public class DebugtraceController {
    @Autowired
    IDebugtraceService debugtraceService;

    @GetMapping("/get")
    List<DebugTraceDTO> getAllDebugTrace(int id){
        return debugtraceService.getAllTrace(id);
    }
}

