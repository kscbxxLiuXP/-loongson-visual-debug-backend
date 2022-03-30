package com.loongson.debug.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Trace implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private Integer ltid;

    private String tracelist;


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

    public String getTracelist() {
        return tracelist;
    }

    public void setTracelist(String tracelist) {
        this.tracelist = tracelist;
    }

    public Trace() {
    }

    public Trace(TraceDTO traceDTO) {
        this.uid = traceDTO.getUid();
        this.ltid = traceDTO.getLtid();
        this.tracelist = JSON.toJSONString(traceDTO.getTracelist());
    }

    @Override
    public String toString() {
        return "Trace{" +
                "uid=" + uid +
                ", ltid=" + ltid +
                ", tracelist=" + tracelist +
                "}";
    }
}
