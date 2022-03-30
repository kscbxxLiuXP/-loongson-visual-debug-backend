package com.loongson.debug.dto;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.Head;

import java.util.ArrayList;
import java.util.List;

public class HeadDTO {

    private Integer uid;

    private Integer ltid;

    private List<String> headtext;


    @Override
    public String toString() {
        return "HeadDTO{" +
                "uid=" + uid +
                ", ltid=" + ltid +
                ", headtext=" + headtext +
                '}';
    }

    public HeadDTO(Head head) {
        this.uid = head.getUid();
        this.ltid = head.getLtid();
        this.headtext = JSON.parseArray(head.getHeadtext(), String.class);
    }

    public HeadDTO(Integer uid, Integer ltid, ArrayList<String> headtext) {
        this.uid = uid;
        this.ltid = ltid;
        this.headtext = headtext;
    }

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

    public List<String> getHeadtext() {
        return headtext;
    }

    public void setHeadtext(ArrayList<String> headtext) {
        this.headtext = headtext;
    }
}
