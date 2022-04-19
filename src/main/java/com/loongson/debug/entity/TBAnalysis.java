package com.loongson.debug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author liuxp
 * @since 2022-04-18
 */
@TableName("tb_analysis")
public class TBAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private Integer ltid;

    private String pc;

    private String endPc;

    private Long execTimes;

    private Integer exitTimes;

    private Long runIr1Num;

    private Long runIr2Num;

    private Double runExpRate;

    private Long ir1Num;

    private Long ir2Num;

    private Double expRate;

    public TBAnalysis() {
    }

    public TBAnalysis(int ltid) {
        this.ltid = ltid;
    }

    public TBAnalysis(Integer uid, Integer ltid, String pc, String endPc, Long execTimes, Integer exitTimes, Long runIr1Num, Long runIr2Num, Double runExpRate, Long ir1Num, Long ir2Num, Double expRate) {
        this.uid = uid;
        this.ltid = ltid;
        this.pc = pc;
        this.endPc = endPc;
        this.execTimes = execTimes;
        this.exitTimes = exitTimes;
        this.runIr1Num = runIr1Num;
        this.runIr2Num = runIr2Num;
        this.runExpRate = runExpRate;
        this.ir1Num = ir1Num;
        this.ir2Num = ir2Num;
        this.expRate = expRate;
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

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getEndPc() {
        return endPc;
    }

    public void setEndPc(String endPc) {
        this.endPc = endPc;
    }

    public Long getExecTimes() {
        return execTimes;
    }

    public void setExecTimes(Long execTimes) {
        this.execTimes = execTimes;
    }

    public Integer getExitTimes() {
        return exitTimes;
    }

    public void setExitTimes(Integer exitTimes) {
        this.exitTimes = exitTimes;
    }

    public Long getRunIr1Num() {
        return runIr1Num;
    }

    public void setRunIr1Num(Long runIr1Num) {
        this.runIr1Num = runIr1Num;
    }

    public Long getRunIr2Num() {
        return runIr2Num;
    }

    public void setRunIr2Num(Long runIr2Num) {
        this.runIr2Num = runIr2Num;
    }

    public Double getRunExpRate() {
        return runExpRate;
    }

    public void setRunExpRate(Double runExpRate) {
        this.runExpRate = runExpRate;
    }

    public Long getIr1Num() {
        return ir1Num;
    }

    public void setIr1Num(Long ir1Num) {
        this.ir1Num = ir1Num;
    }

    public Long getIr2Num() {
        return ir2Num;
    }

    public void setIr2Num(Long ir2Num) {
        this.ir2Num = ir2Num;
    }

    public Double getExpRate() {
        return expRate;
    }

    public void setExpRate(Double expRate) {
        this.expRate = expRate;
    }

    @Override
    public String toString() {
        return "" +
                uid + '\t' +
                ltid + '\t' +
                pc + '\t' +
                endPc + '\t' +
                execTimes + '\t' +
                exitTimes + '\t' +
                runIr1Num + '\t' +
                runIr2Num + '\t' +
                runExpRate + '\t' +
                ir1Num + '\t' +
                ir2Num + '\t' +
                expRate + '\t' + "\n";


    }
}
