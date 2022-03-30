package com.loongson.debug.dto;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.Trace;

import java.util.ArrayList;

public class TraceDTO {
    private Integer uid;

    private Integer ltid;

    private ArrayList<TraceItem> tracelist;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getLtid() {
        return ltid;
    }

    public void setLtid(Integer ltid) {
        this.ltid = ltid;
    }

    public ArrayList<TraceItem> getTracelist() {
        return tracelist;
    }

    public void setTracelist(ArrayList<TraceItem> tracelist) {
        this.tracelist = tracelist;
    }

    public TraceDTO() {
    }

    //第一次解析解析完成后创建
    public TraceDTO(ArrayList<TraceItem> tracelist, int ltid) {
        this.tracelist = tracelist;
        this.ltid = ltid;
    }

    //由json字符串转义
    public TraceDTO(Trace trace) {
        this.ltid = trace.getLtid();
        this.uid = trace.getUid();
        this.tracelist = (ArrayList<TraceItem>) JSON.parseArray(trace.getTracelist(), TraceItem.class);

    }

    @Override
    public String toString() {
        return "TraceDTO{" +
                "uid=" + uid +
                ", ltid=" + ltid +
                ", tracelist=" + tracelist +
                '}';
    }
}
