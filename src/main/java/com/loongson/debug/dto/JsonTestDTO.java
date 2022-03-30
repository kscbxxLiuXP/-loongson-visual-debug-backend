package com.loongson.debug.dto;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.IR1;
import com.loongson.debug.entity.JsonTest;

import java.util.List;

public class JsonTestDTO {
    private Integer id;

    private List<IR1> jsonText;

    public JsonTestDTO(JsonTest jsonTest) {
        this.id = jsonTest.getId();
        this.jsonText = JSON.parseArray(jsonTest.getJsonText(), IR1.class);
    }

    @Override
    public String toString() {
        return "JsonTestDTO{" +
                "id=" + id +
                ", jsonText=" + jsonText +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<IR1> getJsonText() {
        return jsonText;
    }

    public void setJsonText(List<IR1> jsonText) {
        this.jsonText = jsonText;
    }
}
