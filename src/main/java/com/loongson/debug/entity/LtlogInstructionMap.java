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
@TableName("ltlog_instruction_map")
public class LtlogInstructionMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String uid;

    private Integer ltid;

    private Integer indexx;

    private String operator;

    private String operand;

    private String pattern;


    private String ir2instruction;

    //ir2instruction中的个数
    private Integer ir2num;

    //ir1execute即TB块执行的次数
    private Long ir1execute;
    //ir2execute = ir1execute * ir2num
    private  Long ir2execute;
    //所有ir1的执行次数,最终总计
    private  Long sumir1;
    //所有ir2的执行次数，最终总计
    private  Long sumir2;

    public LtlogInstructionMap() {
    }public LtlogInstructionMap(LtlogInstructionPattern ltlogInstructionPattern) {
        this.operator = ltlogInstructionPattern.getOperator();
        this.operand = ltlogInstructionPattern.getOperand();
        this.pattern = ltlogInstructionPattern.getPattern();
        this.ir2instruction = ltlogInstructionPattern.getIr2instruction();
        this.ir2num = ltlogInstructionPattern.getIr2num();
        this.ir1execute = ltlogInstructionPattern.getIr1execute();
        this.ir2execute = ltlogInstructionPattern.getIr2execute();
        this.sumir1 = ltlogInstructionPattern.getSumir1all();
        this.sumir2 = ltlogInstructionPattern.getSumir2all();
    }

    public LtlogInstructionMap(String uid, Integer ltid, Integer indexx, String operator, String operand, String pattern, String ir2instruction, Integer ir2num, Long ir1execute, Long ir2execute, Long sumir1, Long sumir2) {
        this.uid = uid;
        this.ltid = ltid;
        this.indexx = indexx;
        this.operator = operator;
        this.operand = operand;
        this.pattern = pattern;
        this.ir2instruction = ir2instruction;
        this.ir2num = ir2num;
        this.ir1execute = ir1execute;
        this.ir2execute = ir2execute;
        this.sumir1 = sumir1;
        this.sumir2 = sumir2;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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

    public Integer getIr2num() {
        return ir2num;
    }

    public void setIr2num(Integer ir2num) {
        this.ir2num = ir2num;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getLtid() {
        return ltid;
    }

    public void setLtid(Integer ltid) {
        this.ltid = ltid;
    }

    public Integer getIndexx() {
        return indexx;
    }

    public void setIndexx(Integer indexx) {
        this.indexx = indexx;
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

    @Override
    public String toString() {
        return "LtlogInstructionMap{" +
                "uid='" + uid + '\'' +
                ", ltid=" + ltid +
                ", indexx=" + indexx +
                ", operator='" + operator + '\'' +
                ", operand='" + operand + '\'' +
                ", pattern='" + pattern + '\'' +
                ", ir2instruction='" + ir2instruction + '\'' +
                ", ir2num=" + ir2num +
                ", ir1execute=" + ir1execute +
                ", ir2execute=" + ir2execute +
                ", sumir1=" + sumir1 +
                ", sumir2=" + sumir2 +
                '}';
    }
}
