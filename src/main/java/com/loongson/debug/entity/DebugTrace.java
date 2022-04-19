package com.loongson.debug.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.loongson.debug.dto.DebugTraceDTO;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author liuxp
 * @since 2022-04-11
 */
public class DebugTrace implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private Integer debugid;

    private String nextids;

    private String parents;

    /**
     * 间接跳转目的地址，如果没有则为-1
     */
    @TableField("indirectTo")
    private String indirectTo;
    /**
     * 间接跳转源地址，如果没有则为-1
     */
    @TableField("indirectFrom")
    private String indirectFrom;

    /**
     * 调试模式直接跳转，如果没有则为-1
     */
    @TableField("skipFrom")
    private String skipFrom;
    /**
     * 调试模式直接跳转，如果没有则为-1
     */
    @TableField("skipTo")
    private String skipTo;

    private String address;

    private String tbtype;

    private String registers;

    public DebugTrace() {
        this.indirectFrom = "-1";
        this.indirectTo = "-1";
        this.skipFrom = "-1";
        this.skipTo = "-1";
        this.nextids = "[]";
        this.parents = "[]";
    }

    public DebugTrace(DebugTraceDTO debugTraceDTO) {
        this.uid = debugTraceDTO.getUid();
        this.debugid = debugTraceDTO.getDebugid();
        this.indirectTo = debugTraceDTO.getIndirectTo();
        this.indirectFrom = debugTraceDTO.getIndirectFrom();
        this.skipTo = debugTraceDTO.getSkipTo();
        this.skipFrom = debugTraceDTO.getSkipFrom();
        this.address = debugTraceDTO.getAddress();
        this.tbtype = debugTraceDTO.getTbtype();
        this.nextids = JSON.toJSONString(debugTraceDTO.getNextids());
        this.parents = JSON.toJSONString(debugTraceDTO.getParents());
        this.registers = JSON.toJSONString(debugTraceDTO.getRegisters());
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

    public String getNextids() {
        return nextids;
    }

    public void setNextids(String nextids) {
        this.nextids = nextids;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTbtype() {
        return tbtype;
    }

    public void setTbtype(String tbtype) {
        this.tbtype = tbtype;
    }

    public String getRegisters() {
        return registers;
    }

    public void setRegisters(String registers) {
        this.registers = registers;
    }


    public String toString2() {
        return "Debugtrace{" +
                "uid=" + uid +
                ", debugid=" + debugid +
                ", nextids=" + nextids +
                ", parents=" + parents +
                ", indirectto=" + indirectTo +
                ", indirectfrom=" + indirectFrom +
                ", address=" + address +
                ", tbtype=" + tbtype +
                ", registers=" + registers +
                "}";
    }

    @Override
    public String toString() {
        return address;
    }

    public String toString1() {
        String s = address + nextids + parents + registers;
        if (!indirectTo.equals("-1")) {
            s += "\n\t间接跳转" + address + "->" + indirectTo;
        }
        if (!indirectFrom.equals("-1")) {
            s += "\n\t间接跳转" + indirectFrom + "->" + address;
        }
        return s;
    }
}
