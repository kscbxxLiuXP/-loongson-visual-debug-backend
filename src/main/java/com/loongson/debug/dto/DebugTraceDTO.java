package com.loongson.debug.dto;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.DebugTrace;

import java.util.ArrayList;

public class DebugTraceDTO {
    private Integer uid;

    private Integer debugid;

    private ArrayList<DebugTraceItem> tracelist;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDebugid() {
        return debugid;
    }

    public void setDebugid(Integer debugid) {
        this.debugid = debugid;
    }

    public ArrayList<DebugTraceItem> getTracelist() {
        return tracelist;
    }

    public void setTracelist(ArrayList<DebugTraceItem> tracelist) {
        this.tracelist = tracelist;
    }

    public DebugTraceDTO() {
    }

    //第一次解析解析完成后创建
    public DebugTraceDTO(ArrayList<DebugTraceItem> tracelist, int debugid) {
        this.tracelist = tracelist;
        this.debugid = debugid;
    }

    //由json字符串转义
    public DebugTraceDTO(DebugTrace debugTrace) {
        this.uid = debugTrace.getUid();
        this.debugid = debugTrace.getDebugid();
        this.tracelist = (ArrayList<DebugTraceItem>) JSON.parseArray(debugTrace.getTracelist(), DebugTraceItem.class);

    }

    @Override
    public String toString() {
        return "TraceDTO{" + "uid=" + uid + ", ltid=" + debugid + ", tracelist=" + tracelist + '}';
    }
}
