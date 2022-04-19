package com.loongson.debug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author liuxp
 * @since 2022-03-01
 */
public class TbBlock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private Integer ltid;

    private Integer tbindex;

    @TableField("TBAddress")
    private String tbaddress;

    @TableField("startAddressIR1")
    private String startaddressir1;

    @TableField("endAddressIR1")
    private String endaddressir1;

    @TableField("startAddressIR2")
    private String startaddressir2;

    @TableField("endAddressIR2")
    private String endaddressir2;

    @TableField("IR1Num")
    private Integer ir1num;

    @TableField("IR2Num")
    private Integer ir2num;

    @TableField("IR1Instr")
    private String ir1instr;

    @TableField("IR2Instr")
    private String ir2instr;

    @TableField("IR2Map")
    private String ir2map;

    private String instructions;

    public TbBlock(Integer ltid, Integer tbindex, String tbaddress, String startaddressir1, String endaddressir1, String startaddressir2, String endaddressir2, Integer ir1num, Integer ir2num, String ir1instr, String ir2instr, String ir2map, String instructions) {
        this.ltid = ltid;
        this.tbindex = tbindex;
        this.tbaddress = tbaddress;
        this.startaddressir1 = startaddressir1;
        this.endaddressir1 = endaddressir1;
        this.startaddressir2 = startaddressir2;
        this.endaddressir2 = endaddressir2;
        this.ir1num = ir1num;
        this.ir2num = ir2num;
        this.ir1instr = ir1instr;
        this.ir2instr = ir2instr;
        this.ir2map = ir2map;
        this.instructions = instructions;
    }

    public TbBlock() {
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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

    public String getIr1instr() {
        return ir1instr;
    }

    public void setIr1instr(String ir1instr) {
        this.ir1instr = ir1instr;
    }

    public String getIr2instr() {
        return ir2instr;
    }

    public void setIr2instr(String ir2instr) {
        this.ir2instr = ir2instr;
    }

    public String getIr2map() {
        return ir2map;
    }

    public void setIr2map(String ir2map) {
        this.ir2map = ir2map;
    }

    @Override
    public String toString() {
        return "TbBlock{" +
                "uid=" + uid +
                ", ltid=" + ltid +
                ", tbindex=" + tbindex +
                ", tbaddress=" + tbaddress +
                ", startaddressir1=" + startaddressir1 +
                ", endaddressir1=" + endaddressir1 +
                ", startaddressir2=" + startaddressir2 +
                ", endaddressir2=" + endaddressir2 +
                ", ir1num=" + ir1num +
                ", ir2num=" + ir2num +
                ", ir1instr=" + ir1instr +
                ", ir2instr=" + ir2instr +
                ", ir2map=" + ir2map +
                "}";
    }
}
