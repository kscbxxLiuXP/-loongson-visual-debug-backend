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
 * @since 2022-04-26
 */
@TableName("ltlog_instruction_pattern")
public class LtlogInstructionPattern implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private Integer ltid;

    private String operator;

    private String operand;

    private String pattern;

    private String ir2instruction;
    private Integer ir2num;


    private Long ir1execute;

    private Long ir2execute;

    private Long sumir1;

    private Long sumir2;

    private Long sumir1all;

    private Long sumir2all;

    public void increaseIr1execute(Long num){
        this.ir1execute+= num;
    }
    public void increaseIr2execute(Long num){
        this.ir2execute+= num;
    }

    public LtlogInstructionPattern() {
    }

    public LtlogInstructionPattern(Integer uid, Integer ltid, String operator, String operand, String pattern, String ir2instruction, Integer ir2num, Long ir1execute, Long ir2execute, Long sumir1, Long sumir2, Long sumir1all, Long sumir2all) {
        this.uid = uid;
        this.ltid = ltid;
        this.operator = operator;
        this.operand = operand;
        this.pattern = pattern;
        this.ir2instruction = ir2instruction;
        this.ir2num = ir2num;
        this.ir1execute = ir1execute;
        this.ir2execute = ir2execute;
        this.sumir1 = sumir1;
        this.sumir2 = sumir2;
        this.sumir1all = sumir1all;
        this.sumir2all = sumir2all;
    }

    public LtlogInstructionPattern(LtlogInstructionMap ltlogInstructionMap) {
        this.ltid = ltlogInstructionMap.getLtid();
        this.operator = ltlogInstructionMap.getOperator();
        this.operand = ltlogInstructionMap.getOperand();
        this.pattern = ltlogInstructionMap.getPattern();
        this.ir2instruction = ltlogInstructionMap.getIr2instruction();
        this.ir2num = ltlogInstructionMap.getIr2num();
        this.ir1execute = ltlogInstructionMap.getIr1execute();
        this.ir2execute = ltlogInstructionMap.getIr2execute();

    }

    public Integer getIr2num() {
        return ir2num;
    }

    public void setIr2num(Integer ir2num) {
        this.ir2num = ir2num;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getIr2instruction() {
        return ir2instruction;
    }

    public void setIr2instruction(String ir2instruction) {
        this.ir2instruction = ir2instruction;
    }

    public Long getIr1execute() {
        return ir1execute;
    }

    public void setIr1execute(Long ir1execute) {
        this.ir1execute = ir1execute;
    }

    public Long getIr2execute() {
        return ir2execute;
    }

    public void setIr2execute(Long ir2execute) {
        this.ir2execute = ir2execute;
    }

    public Long getSumir1() {
        return sumir1;
    }

    public void setSumir1(Long sumir1) {
        this.sumir1 = sumir1;
    }

    public Long getSumir2() {
        return sumir2;
    }

    public void setSumir2(Long sumir2) {
        this.sumir2 = sumir2;
    }

    public Long getSumir1all() {
        return sumir1all;
    }

    public void setSumir1all(Long sumir1all) {
        this.sumir1all = sumir1all;
    }

    public Long getSumir2all() {
        return sumir2all;
    }

    public void setSumir2all(Long sumir2all) {
        this.sumir2all = sumir2all;
    }

    @Override
    public String toString() {
        return "LtlogInstructionPattern{" +
                "uid=" + uid +
                ", ltid=" + ltid +
                ", operator='" + operator + '\'' +
                ", operand='" + operand + '\'' +
                ", pattern='" + pattern + '\'' +
                ", ir2instruction='" + ir2instruction + '\'' +
                ", ir2num=" + ir2num +
                ", ir1execute=" + ir1execute +
                ", ir2execute=" + ir2execute +
                ", sumir1=" + sumir1 +
                ", sumir2=" + sumir2 +
                ", sumir1all=" + sumir1all +
                ", sumir2all=" + sumir2all +
                '}';
    }
}
