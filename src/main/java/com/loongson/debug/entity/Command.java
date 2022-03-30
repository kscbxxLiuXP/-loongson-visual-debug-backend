package com.loongson.debug.entity;

/*
 * @Description:<br>
 * </>
 * @Param:
 * @Return:
 */
//表示一个汇编指令
//operator 运算符
//operand 操作数
//type:
//  1:x86
//  2:LoongArch
public class Command {
    private String operator;
    private String operand;
    private int type;

    public Command() {
    }

    public Command(String operator, String operand, int type) {
        this.operator = operator;
        this.operand = operand;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Command{" +
                "operator='" + operator + '\'' +
                ", operand='" + operand + '\'' +
                ", type=" + type +
                '}';
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
