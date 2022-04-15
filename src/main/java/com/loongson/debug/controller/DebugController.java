package com.loongson.debug.controller;


import com.loongson.debug.dto.HeadDTO;
import com.loongson.debug.dto.SearchResultDTO;
import com.loongson.debug.dto.TBBlockSimple;
import com.loongson.debug.entity.Head;
import com.loongson.debug.entity.LtLog;
import com.loongson.debug.entity.TbBlock;
import com.loongson.debug.service.IHeadService;
import com.loongson.debug.service.ILtLogService;
import com.loongson.debug.service.ITbBlockService;
import com.loongson.debug.service.SearchService;
import com.loongson.debug.util.RunTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class DebugController {

    @Autowired
    ILtLogService ltLogService;

    @Autowired
    ITbBlockService tbBlockService;

    @Autowired
    IHeadService headService;
    @Autowired
    SearchService searchService;

    @GetMapping("/debugHello")
    public String debugHello() {
        return "Hello,debug";
    }

    @GetMapping("/debug")
    public Object debug(int id) {
        RunTimer timer = new RunTimer(false);

        timer.start();
        //日志文件Lt_Log
        LtLog ltLog = ltLogService.getLtLogsById(id);
        timer.end("读取Lt_log");

        timer.start();
        //head块
        Head head = headService.getHeadById(id);
        HeadDTO headDTO = new HeadDTO(head);
        timer.end("读取TB头");

        //返回TBBlockDTO
        //返回TB简单列表
        //获取全部TbBlocks

        timer.start();
        List<TbBlock> tbBlocks = tbBlockService.getTbBlocksSimple(id);
        timer.end("数据库读取TB块");

        timer.start();
        List<TBBlockSimple> tbBlockSimples = new ArrayList<>();
        Map<String, Integer> addressIndexMap = new HashMap<>();
        for (TbBlock tbBlock : tbBlocks) {
            addressIndexMap.put(tbBlock.getStartaddressir1(), tbBlock.getTbindex());
            TBBlockSimple tbBlockSimple = new TBBlockSimple(tbBlock);
            tbBlockSimples.add(tbBlockSimple);
        }
        timer.end("TB块转化DTO");

        //构建返回参数
        HashMap<String, Object> returnRes = new HashMap<>();
        returnRes.put("ltlog", ltLog);
        returnRes.put("head", headDTO);
        returnRes.put("simpleTbBlocks", tbBlockSimples);
        returnRes.put("addressIndexMap", addressIndexMap);
        return returnRes;
    }

    @GetMapping("/debug/search/address")
    public HashMap<String, Object> debugSearchAddress(int ltid, String address, boolean skipHead) {
//        List<SearchResultDTO> searchResultDTOS = searchService.searchAddress(ltid, address, skipHead);
        List<SearchResultDTO> searchResultDTOS = searchService.searchAddress2(ltid, address);
        HashMap<String, Object> returnRes = new HashMap<>();

        returnRes.put("results", searchResultDTOS);
        returnRes.put("total", searchResultDTOS.size());


        return returnRes;
    }

    @GetMapping("/debug/search/instruction")
    public HashMap<String, Object> debugSearchInstruction(int type, int ltid, String instruction, boolean skipHead) {
        List<SearchResultDTO> searchResultDTOS = searchService.searchInstruction(type, ltid, instruction, skipHead);
        HashMap<String, Object> returnRes = new HashMap<>();

        returnRes.put("results", searchResultDTOS);
        returnRes.put("total", searchResultDTOS.size());


        return returnRes;
    }
}
