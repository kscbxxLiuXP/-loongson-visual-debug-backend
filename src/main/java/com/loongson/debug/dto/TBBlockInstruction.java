package com.loongson.debug.dto;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.TbBlock;

import java.util.ArrayList;

/**
 * 这个clss主要用于在统计TB指令数量时转化用
 */
public class TBBlockInstruction {
    private Integer tbindex;
    private ArrayList<Integer> instructions;

    public TBBlockInstruction(TbBlock tbBlock) {
        this.tbindex = tbBlock.getTbindex();
        this.instructions = (ArrayList<Integer>) JSON.parseArray(tbBlock.getInstructions(), Integer.class);
    }

    public TBBlockInstruction(Integer tbindex, ArrayList<Integer> instructions) {
        this.tbindex = tbindex;
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "TBBlockInstruction{" +
                "tbindex=" + tbindex +
                ", instructions=" + instructions +
                '}';
    }

    public Integer getTbindex() {
        return tbindex;
    }

    public void setTbindex(Integer tbindex) {
        this.tbindex = tbindex;
    }

    public ArrayList<Integer> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Integer> instructions) {
        this.instructions = instructions;
    }
}
