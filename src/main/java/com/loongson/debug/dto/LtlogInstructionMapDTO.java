package com.loongson.debug.dto;


public class LtlogInstructionMapDTO {
    private String uid;

    private Integer ltid;

    private Integer indexx;

    private String operator;

    private String operand;

    private String pattern;


    private String ir2instruction;

    private Integer ir2num;

    //irexecute
    private Long num;

    private Long ir2execute;
    private Long sumir1;
    private Long sumir2;

    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public LtlogInstructionMapDTO() {
    }

    public LtlogInstructionMapDTO(String uid, Integer ltid, Integer indexx, String operator, String operand, String pattern, String ir2instruction, Integer ir2num, Long num, Long ir2execute, Long sumir1, Long sumir2, Long total) {
        this.uid = uid;
        this.ltid = ltid;
        this.indexx = indexx;
        this.operator = operator;
        this.operand = operand;
        this.pattern = pattern;
        this.ir2instruction = ir2instruction;
        this.ir2num = ir2num;
        this.num = num;
        this.ir2execute = ir2execute;
        this.sumir1 = sumir1;
        this.sumir2 = sumir2;
        this.total = total;
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

    public Integer getIr2num() {
        return ir2num;
    }

    public void setIr2num(Integer ir2num) {
        this.ir2num = ir2num;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
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

    @Override
    public String toString() {
        return "LtlogInstructionMapDTO{" +
                "uid='" + uid + '\'' +
                ", ltid=" + ltid +
                ", indexx=" + indexx +
                ", operator='" + operator + '\'' +
                ", operand='" + operand + '\'' +
                ", pattern='" + pattern + '\'' +
                ", ir2num=" + ir2num +
                ", num=" + num +
                ", ir2execute=" + ir2execute +
                ", sumir1=" + sumir1 +
                ", sumir2=" + sumir2 +
                ", total=" + total +
                '}';
    }
}
