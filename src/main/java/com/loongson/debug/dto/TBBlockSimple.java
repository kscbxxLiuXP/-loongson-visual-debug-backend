package com.loongson.debug.dto;

import com.loongson.debug.entity.TbBlock;

import java.util.ArrayList;

public class TBBlockSimple {
    private Integer tbindex;
    private String tbaddress;
    private String startaddressir1;
    private String endaddressir1;
    private String startaddressir2;
    private String endaddressir2;
    private Integer ir1num;
    private Integer ir2num;

    public TBBlockSimple(TbBlock tbBlock) {
        this.tbindex = tbBlock.getTbindex();
        this.tbaddress = tbBlock.getTbaddress();
        this.startaddressir1 = tbBlock.getStartaddressir1();
        this.endaddressir1 = tbBlock.getEndaddressir1();
        this.startaddressir2 = tbBlock.getStartaddressir2();
        this.endaddressir2 = tbBlock.getEndaddressir2();
        this.ir1num = tbBlock.getIr1num();
        this.ir2num = tbBlock.getIr2num();
    }

    @Override
    public String toString() {
        return "TBBlockSimple{" +
                "tbindex=" + tbindex +
                ", tbaddress='" + tbaddress + '\'' +
                ", startaddressir1='" + startaddressir1 + '\'' +
                ", endaddressir1='" + endaddressir1 + '\'' +
                ", startaddressir2='" + startaddressir2 + '\'' +
                ", endaddressir2='" + endaddressir2 + '\'' +
                ", ir1num=" + ir1num +
                ", ir2num=" + ir2num +
                '}';
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

    public String getStartaddressir1() {
        return startaddressir1;
    }

    public void setStartaddressir1(String startaddressir1) {
        this.startaddressir1 = startaddressir1;
    }

    public String getEndaddressir1() {
        return endaddressir1;
    }

    public void setEndaddressir1(String endaddressir1) {
        this.endaddressir1 = endaddressir1;
    }

    public String getStartaddressir2() {
        return startaddressir2;
    }

    public void setStartaddressir2(String startaddressir2) {
        this.startaddressir2 = startaddressir2;
    }

    public String getEndaddressir2() {
        return endaddressir2;
    }

    public void setEndaddressir2(String endaddressir2) {
        this.endaddressir2 = endaddressir2;
    }

    public Integer getIr1num() {
        return ir1num;
    }

    public void setIr1num(Integer ir1num) {
        this.ir1num = ir1num;
    }

    public Integer getIr2num() {
        return ir2num;
    }

    public void setIr2num(Integer ir2num) {
        this.ir2num = ir2num;
    }
}

