package com.loongson.debug.dto;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.loongson.debug.entity.DebugTrace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DebugTraceDTO {
    //id与tbindex相对应
    private Integer uid;

    private Integer debugid;

    private ArrayList<String> nextids;
    private ArrayList<String> parents;
    /**
     * 间接跳转目的地址，如果没有则为-1
     */
    private String indirectTo;
    /**
     * 间接跳转源地址，如果没有则为-1
     */
    private String indirectFrom;
    private String skipFrom;
    /**
     * 调试模式直接跳转，如果没有则为-1
     */
    private String skipTo;
    private String address;
    private String tbtype;
    private Map<String, String> registers;


    public DebugTraceDTO() {
        this.indirectFrom = "-1";
        this.indirectTo = "-1";
        this.skipFrom = "-1";
        this.skipTo = "-1";
        this.nextids = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    public DebugTraceDTO(DebugTrace debugTrace) {
        this.uid = debugTrace.getUid();
        this.debugid = debugTrace.getDebugid();
        this.nextids = (ArrayList<String>) JSON.parseArray(debugTrace.getNextids(), String.class);
        this.parents = (ArrayList<String>) JSON.parseArray(debugTrace.getParents(), String.class);
        this.indirectTo = debugTrace.getIndirectTo();
        this.indirectFrom = debugTrace.getIndirectFrom();
        this.skipFrom = debugTrace.getSkipFrom();
        this.skipTo = debugTrace.getSkipTo();
        this.address = debugTrace.getAddress();
        this.tbtype = debugTrace.getTbtype();
        this.registers = JSON.parseObject(debugTrace.getRegisters(), HashMap.class);
    }

    @Override
    public String toString() {
        return address + "(" + uid + ")";
    }

    public String toString1() {
        String s = address + "(" + uid + ")" + nextids + parents + registers;
        if (!indirectTo.equals("-1")) {
            s += "\n\t间接跳转" + address + "->" + indirectTo;
        }
        if (!indirectFrom.equals("-1")) {
            s += "\n\t间接跳转" + indirectFrom + "->" + address;
        }
        return s;
    }

    public String toString2() {
        return "TraceItem{" +
                "id=" + uid +
                ", nextids=" + nextids +
                ", parents=" + nextids +
                ", registerValues=" + nextids +
                ", tbtype='" + tbtype + '\'' +
                '}';
    }

    public String getSkipFrom() {
        return skipFrom;
    }

    public void setSkipFrom(String skipFrom) {
        this.skipFrom = skipFrom;
    }

    public String getSkipTo() {
        return skipTo;
    }

    public void setSkipTo(String skipTo) {
        this.skipTo = skipTo;
    }

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

    public String getIndirectTo() {
        return indirectTo;
    }

    public void setIndirectTo(String indirectTo) {
        this.indirectTo = indirectTo;
    }

    public String getIndirectFrom() {
        return indirectFrom;
    }

    public void setIndirectFrom(String indirectFrom) {
        this.indirectFrom = indirectFrom;
    }

    public Map<String, String> getRegisters() {
        return registers;
    }

    public void setRegisters(Map<String, String> register) {
        this.registers = register;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public ArrayList<String> getNextids() {
        return nextids;
    }

    public void setNextids(ArrayList<String> nextids) {
        this.nextids = nextids;
    }

    public String getTbtype() {
        return tbtype;
    }

    public void setTbtype(String tbtype) {
        this.tbtype = tbtype;
    }

    public ArrayList<String> getParents() {
        return parents;
    }

    public void setParents(ArrayList<String> parents) {
        this.parents = parents;
    }
}
