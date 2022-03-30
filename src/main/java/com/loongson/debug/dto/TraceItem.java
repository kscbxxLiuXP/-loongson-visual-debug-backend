package com.loongson.debug.dto;


import com.loongson.debug.entity.TbBlock;

import java.util.ArrayList;
import java.util.Map;

/**
 * s
 */
public class TraceItem {
    //id与tbindex相对应
    private int id;

    private ArrayList<Integer> nextids;
    private ArrayList<Integer> parents;
    /**
     * 间接跳转目的地址，如果没有则为-1
     */
    private String indirectTo;
    /**
     * 间接跳转源地址，如果没有则为-1
     */
    private String indirectFrom;

    private String address;

    /**
     * 在分析Trace文件时，以IR2的起始地址为索引
     */
    private String address2;

    private String tbtype;

    private Integer tbindex;
    private String tbaddress;
    private Map<String, String> registers;


    public TraceItem() {
        this.nextids = new ArrayList<>();
        this.parents = new ArrayList<>();
    }


    //初始化TraceItem,此处初始化是为了建立address与TraceItem的映射关系
    public TraceItem(TbBlock tbBlock) {
        this.id = tbBlock.getTbindex();
        this.nextids = new ArrayList<>();
        this.parents = new ArrayList<>();

        this.indirectFrom = "-1";
        this.indirectTo = "-1";
        this.address = tbBlock.getStartaddressir1();
        this.address2 = tbBlock.getStartaddressir2();
        this.tbindex = tbBlock.getTbindex();
        this.tbaddress = tbBlock.getTbaddress();

    }

    @Override
    public String toString() {
        return address + "(" + id + ")";
    }

    public String toString1() {
        String s = address + "(" + id + ")" + nextids + parents + registers;
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
                "id=" + id +
                ", nextids=" + nextids +
                ", parents=" + nextids +
                ", registerValues=" + nextids +
                ", tbtype='" + tbtype + '\'' +
                ", tbindex=" + tbindex +
                ", tbaddress='" + tbaddress + '\'' +
                '}';
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

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getNextids() {
        return nextids;
    }

    public void setNextids(ArrayList<Integer> nextids) {
        this.nextids = nextids;
    }

    public String getTbtype() {
        return tbtype;
    }

    public void setTbtype(String tbtype) {
        this.tbtype = tbtype;
    }

    public Integer getTbindex() {
        return tbindex;
    }

    public void setTbindex(Integer tbindex) {
        this.tbindex = tbindex;
    }

    public String getTbaddress() {
        return tbaddress;
    }

    public void setTbaddress(String tbaddress) {
        this.tbaddress = tbaddress;
    }

    public ArrayList<Integer> getParents() {
        return parents;
    }

    public void setParents(ArrayList<Integer> parents) {
        this.parents = parents;
    }
}
