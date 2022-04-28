package com.loongson.debug.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.api.client.json.Json;

import java.util.List;

public class QueryInstructionAllDTO {
    private String operator;
    private String orderby;
    private String order;
    private int ltid;
    private int currentPage;
    private int limit;

    private String comboed;

    private String hidden;
    private String hiddenOperator;

    public QueryInstructionAllDTO() {
    }

    public QueryInstructionAllDTO(String operator, String orderby, String order, int ltid, int currentPage, int limit, String comboed, String hidden,String hiddenOperator) {
        this.operator = operator;
        this.orderby = orderby;
        this.order = order;
        this.ltid = ltid;
        this.currentPage = currentPage;
        this.limit = limit;
        this.comboed = comboed;
        this.hidden = hidden;
        this.hiddenOperator = hiddenOperator;
    }

    @Override
    public String toString() {
        return "QueryInstructionAllDTO{" +
                "operator='" + operator + '\'' +
                ", orderby='" + orderby + '\'' +
                ", order='" + order + '\'' +
                ", ltid=" + ltid +
                ", currentPage=" + currentPage +
                ", limit=" + limit +
                ", comboed=" + getComboed() +
                ", hidden=" + getHidden() +
                ", hiddenOperator=" + getHiddenOperator() +
                '}';
    }

    public List<String> getHiddenOperator() {
        return JSON.parseArray(hiddenOperator,String.class);
    }

    public void setHiddenOperator(String hiddenOperator) {
        this.hiddenOperator = hiddenOperator;
    }

    public List<PatternFilter> getComboed() {
        return JSON.parseArray(comboed,PatternFilter.class);
    }

    public List<PatternFilter> getHidden() {
        return JSON.parseArray(hidden,PatternFilter.class);

    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public void setComboed(String comboed) {
        this.comboed = comboed;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getLtid() {
        return ltid;
    }

    public void setLtid(int ltid) {
        this.ltid = ltid;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
