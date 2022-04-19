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

    private String ir2instruction;

    private Long num;

    private transient long sum;

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public void numIncrease() {
        this.num++;
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

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "LtlogInstructionMap{" +
                "uid=" + uid +
                ", ltid=" + ltid +
                ", indexx=" + indexx +
                ", operator=" + operator +
                ", operand=" + operand +
                ", ir2instruction=" + ir2instruction +
                ", num=" + num +
                "}";
    }
}
