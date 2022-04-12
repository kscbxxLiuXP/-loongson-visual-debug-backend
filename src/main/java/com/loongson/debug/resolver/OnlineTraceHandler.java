package com.loongson.debug.resolver;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loongson.debug.dto.DebugTraceDTO;
import com.loongson.debug.entity.DebugTrace;
import com.loongson.debug.entity.OnlineDebug;
import com.loongson.debug.helper.GlobalDebugMaintainer;
import com.loongson.debug.service.IDebugtraceService;
import com.loongson.debug.util.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnlineTraceHandler {
    private IDebugtraceService debugtraceService;

    private IDebugtraceService debugtraceService() {
        if (debugtraceService == null) {
            this.debugtraceService = BeanUtils.getBean(IDebugtraceService.class);
        }
        return debugtraceService;
    }


    public void handleOnlineTrace(int debugid, String address, String tbtype, String registers) {
        GlobalDebugMaintainer globalDebugMaintainer = GlobalDebugMaintainer.getInstance();
        OnlineDebug onlineDebug = globalDebugMaintainer.get(debugid);
        boolean exist = false;
        DebugTrace debugTrace = debugtraceService().getOne(new QueryWrapper<DebugTrace>().eq("debugid", debugid).eq("address", address));
        DebugTraceDTO debugTraceDTO = null;
        if (debugTrace == null) {
            debugTraceDTO = new DebugTraceDTO();
            debugTraceDTO.setAddress(address);
            debugTraceDTO.setDebugid(debugid);
        } else {
            exist = true;
            debugTraceDTO = new DebugTraceDTO(debugTrace);
        }

        debugTraceDTO.setTbtype(tbtype);
        debugTraceDTO.setRegisters(convertRegister(registers));
        //设置间接跳转关系
        if (!onlineDebug.getPreviousTrace().equals("-1")) {
            //设置当前Trace间接跳转信息
            debugTraceDTO.setIndirectFrom(onlineDebug.getPreviousTrace());
            //设置上一个Trace间接跳转信息
            debugtraceService().setIndirectTo(debugid, onlineDebug.getPreviousTrace(), address);
            //addressTraceItemMap.get(previousTrace).setIndirectTo(address);

        }
        if (exist) {
            debugtraceService().updateById(new DebugTrace(debugTraceDTO));

        } else {
            debugtraceService().save(new DebugTrace(debugTraceDTO));
        }

        globalDebugMaintainer.setPreviousTrace(onlineDebug.getUid(), address);
    }

    public Map<String, String> convertRegister(String registers) {
        Map<String, String> map = new HashMap<>();
        String[] split = registers.split(",");
        map.put("EAX", split[0]);
        map.put("ECX", split[1]);
        map.put("EDX", split[2]);
        map.put("EBX", split[3]);
        map.put("ESP", split[4]);
        map.put("EBP", split[5]);
        map.put("ESI", split[6]);
        map.put("EDI", split[7]);
        return map;
    }

    public void handleTBLink(int debugid, String linkFrom, String linkTo) {
        GlobalDebugMaintainer globalDebugMaintainer = GlobalDebugMaintainer.getInstance();
        globalDebugMaintainer.setPreviousTrace(debugid, "-1");


        DebugTrace debugTrace1 = debugtraceService().getOne(new QueryWrapper<DebugTrace>().eq("debugid", debugid).eq("address", linkFrom));
        ArrayList<String> list1 = (ArrayList<String>) JSON.parseArray(debugTrace1.getNextids(), String.class);
        list1.add(linkTo);
        debugTrace1.setNextids(JSON.toJSONString(list1));
        debugtraceService().updateById(debugTrace1);

        DebugTrace debugTrace2 = debugtraceService().getOne(new QueryWrapper<DebugTrace>().eq("debugid", debugid).eq("address", linkTo));
        ArrayList<String> list2 = null;
        if (debugTrace2 == null) {
            list2 = new ArrayList<>();
        } else {
            list2 = (ArrayList<String>) JSON.parseArray(debugTrace2.getParents(), String.class);
        }
        list2.add(linkFrom);

        if (debugTrace2 == null) {
            debugTrace2 = new DebugTrace();
            debugTrace2.setDebugid(debugid);
            debugTrace2.setAddress(linkTo);
            debugTrace2.setParents(JSON.toJSONString(list2));

            debugtraceService().save(debugTrace2);
        } else {
            debugTrace2.setParents(JSON.toJSONString(list2));

            debugtraceService().updateById(debugTrace2);
        }
    }
}
