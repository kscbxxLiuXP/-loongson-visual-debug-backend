package com.loongson.debug.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.loongson.debug.dto.DebugTraceDTO;
import com.loongson.debug.dto.TraceDTO;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author liuxp
 * @since 2022-03-17
 */
public class DebugTrace implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private Integer debugid;

    private String tracelist;


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

    public String getTracelist() {
        return tracelist;
    }

    public void setTracelist(String tracelist) {
        this.tracelist = tracelist;
    }

    public DebugTrace() {
    }

    public DebugTrace(DebugTraceDTO debugTraceDTO) {
        this.uid = debugTraceDTO.getUid();
        this.debugid = debugTraceDTO.getDebugid();
        this.tracelist = JSON.toJSONString(debugTraceDTO.getTracelist());
    }

    @Override
    public String toString() {
        return "Trace{" + "uid=" + uid + ", debugos=" + debugid + ", tracelist=" + tracelist + "}";
    }
}
