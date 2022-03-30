package com.loongson.debug.dto;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.IR1;
import com.loongson.debug.entity.IR2;
import com.loongson.debug.entity.TbBlock;

import java.util.ArrayList;


///
// 在TB块中要实现下面的功能：
// 1.TB块从什么地址开始
// 2.IR1和IR2的对应关系
//     2.1转换耗费了多少
//
//
public class TBBlockDTO {

    //在数据库中的唯一标识
    private int uid;
    //TB块在输出中的id
    private int tbindex;
    private Integer ltid;
    private String TBAddress;
    private String startAddressIR1;
    private String endAddressIR1;
    private String startAddressIR2;
    private String endAddressIR2;

    private int IR1Num;
    private int IR2Num;
    private ArrayList<IR1> IR1Instr;
    private ArrayList<IR2> IR2Instr;
    private ArrayList<Integer> IR2Map;

    public Integer getLtid() {
        return ltid;
    }

    public void setLtid(Integer ltid) {
        this.ltid = ltid;
    }

    public int getTbindex() {
        return tbindex;
    }

    public void setTbindex(int tbindex) {
        this.tbindex = tbindex;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getTBAddress() {
        return TBAddress;
    }

    public void setTBAddress(String TBAddress) {
        this.TBAddress = TBAddress;
    }

    public String getStartAddressIR1() {
        return startAddressIR1;
    }

    public void setStartAddressIR1(String startAddressIR1) {
        this.startAddressIR1 = startAddressIR1;
    }

    public String getEndAddressIR1() {
        return endAddressIR1;
    }

    public void setEndAddressIR1(String endAddressIR1) {
        this.endAddressIR1 = endAddressIR1;
    }

    public String getStartAddressIR2() {
        return startAddressIR2;
    }

    public void setStartAddressIR2(String startAddressIR2) {
        this.startAddressIR2 = startAddressIR2;
    }

    public String getEndAddressIR2() {
        return endAddressIR2;
    }

    public void setEndAddressIR2(String endAddressIR2) {
        this.endAddressIR2 = endAddressIR2;
    }

    public int getIR1Num() {
        return IR1Num;
    }

    public void setIR1Num(int IR1Num) {
        this.IR1Num = IR1Num;
    }

    public int getIR2Num() {
        return IR2Num;
    }

    public void setIR2Num(int IR2Num) {
        this.IR2Num = IR2Num;
    }

    public ArrayList<IR1> getIR1Instr() {
        return IR1Instr;
    }

    public void setIR1Instr(ArrayList<IR1> IR1Instr) {
        this.IR1Instr = IR1Instr;
    }

    public ArrayList<IR2> getIR2Instr() {
        return IR2Instr;
    }

    public void setIR2Instr(ArrayList<IR2> IR2Instr) {
        this.IR2Instr = IR2Instr;
    }

    public ArrayList<Integer> getIR2Map() {
        return IR2Map;
    }

    public void setIR2Map(ArrayList<Integer> IR2Map) {
        this.IR2Map = IR2Map;
    }

    public TBBlockDTO(TbBlock tbBlock) {
        this.uid = tbBlock.getUid();
        this.ltid = tbBlock.getLtid();
        this.tbindex = tbBlock.getTbindex();
        this.TBAddress = tbBlock.getTbaddress();
        this.startAddressIR1 = tbBlock.getStartaddressir1();
        this.endAddressIR1 = tbBlock.getEndaddressir1();
        this.startAddressIR2 = tbBlock.getStartaddressir2();
        this.endAddressIR2 = tbBlock.getEndaddressir2();
        this.IR1Num = tbBlock.getIr1num();
        this.IR2Num = tbBlock.getIr2num();
        this.IR1Instr = (ArrayList<IR1>) JSON.parseArray(tbBlock.getIr1instr(), IR1.class);
        this.IR2Instr = (ArrayList<IR2>) JSON.parseArray(tbBlock.getIr2instr(), IR2.class);
        this.IR2Map = (ArrayList<Integer>) JSON.parseArray(tbBlock.getIr2map(), Integer.class);
    }

    public TBBlockDTO(int uid, int index, String TBAddress, String startAddressIR1, String endAddressIR1, String startAddressIR2, String endAddressIR2, int IR1Num, int IR2Num, ArrayList<IR1> IR1Instr, ArrayList<IR2> IR2Instr, ArrayList<Integer> IR2Map) {
        this.uid = uid;
        this.tbindex = index;
        this.TBAddress = TBAddress;
        this.startAddressIR1 = startAddressIR1;
        this.endAddressIR1 = endAddressIR1;
        this.startAddressIR2 = startAddressIR2;
        this.endAddressIR2 = endAddressIR2;
        this.IR1Num = IR1Num;
        this.IR2Num = IR2Num;
        this.IR1Instr = IR1Instr;
        this.IR2Instr = IR2Instr;
        this.IR2Map = IR2Map;
    }

    @Override
    public String toString() {
        return "TBBlockDto{" +
                "uid=" + uid +
                ", tbindex=" + tbindex +
                ", ltid=" + ltid +
                ", TBAddress='" + TBAddress + '\'' +
                ", startAddressIR1='" + startAddressIR1 + '\'' +
                ", endAddressIR1='" + endAddressIR1 + '\'' +
                ", startAddressIR2='" + startAddressIR2 + '\'' +
                ", endAddressIR2='" + endAddressIR2 + '\'' +
                ", IR1Num=" + IR1Num +
                ", IR2Num=" + IR2Num +
                ", IR1Instr=" + IR1Instr +
                ", IR2Instr=" + IR2Instr +
                ", IR2Map=" + IR2Map +
                '}';
    }
}
