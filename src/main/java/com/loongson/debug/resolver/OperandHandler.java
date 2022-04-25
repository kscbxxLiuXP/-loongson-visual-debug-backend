package com.loongson.debug.resolver;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class OperandHandler {

    private final String[] reg8 = {"ah", "al", "bh", "bl", "ch", "cl", "dh", "dl", "sil", "dil", "bpl", "spl", "r8b", "r9b", "r10b", "r11b", "r12b", "r13b", "r14b", "r15b",};
    private final String[] reg16 = {"ax", "bx", "cx", "dx", "si", "di", "bp", "sp", "r8w", "r9w", "r10w", "r11w", "r12w", "r13w", "r14w", "r15w",};
    private final String[] reg32 = {"eax", "ebx", "ecx", "edx", "esi", "edi", "ebp", "esp", "r8d", "r9d", "r10d", "r11d", "r12d", "r13d", "r14d", "r15d",};
    private final String[] reg64 = {"rax", "rbx", "rcx", "rdx", "rsi", "rdi", "rbp", "rsp", "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15",};
    private final String[] xmm = {"xmm0", "xmm1", "xmm2", "xmm3", "xmm4", "xmm5", "xmm6", "xmm7", "xmm8", "xmm9", "xmm10", "xmm11", "xmm12", "xmm13", "xmm14", "xmm15"};
    private final String[] mmx = {"mm0", "mm1", "mm2", "mm3", "mm4", "mm5", "mm6", "mm7", "mm8", "mm9", "mm10", "mm11", "mm12", "mm13", "mm14", "mm15"};

    private final String[] ptr = {"word ptr,byte ptr,dword ptr,qword 64"};

    private final List<String> reg8s = Arrays.asList(reg8);
    private final List<String> reg16s = Arrays.asList(reg16);
    private final List<String> reg32s = Arrays.asList(reg32);
    private final List<String> reg64s = Arrays.asList(reg64);
    private final List<String> xmms = Arrays.asList(xmm);
    private final List<String> mmxs = Arrays.asList(mmx);

    public String pattern(String operand) {
        if (operand.equals("")) {
            return "NaN";
        }
        String[] split = operand.split(",");
        String part1 = "";
        String part2 = ",";
        String pattern = "";
        if (split.length == 1) {
            part1 = resolve(split[0].trim());
            pattern = part1;
        } else if (split.length == 2) {
            part1 = resolve(split[0].trim());
            part2 = resolve(split[1].trim());
            pattern = part1 + "," + part2;
        } else {
            pattern = "error";
        }
        return pattern;
    }

    public String resolve(String s) {
        String result = "";
        //0,0x25,0x1234,0x12345678,0x
        if (s.startsWith("0x") || s.startsWith("-") || StringUtils.isNumeric(s)) {
            //立即数
            //去除-号
            if (s.startsWith("-0x")) {
                s = s.substring(1);
            }
            result += "imm";

        } else if (s.contains("[")) {
            //处理ptr
            //TYPE1: [ ]
            //TYPE2: *word ptr [ ]
            //TYPE3: *word ptr *:[ ]
            String addressingInfo = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
            if (s.startsWith("[")) {
                //处理TYPE1[]
                //do nothing

            } else if (s.contains(":")) {
                String s1 = s.split(":")[0].trim();
                result += s1;
            } else {
                String s1 = s.split("\\[")[0].trim();
                result += s1;
            }
            result += "[" + processAddressing(addressingInfo) + "]";

        } else {
            //处理寄存器名称
            result = getRegType(s);
        }
        return result;
    }

    public String processAddressing(String s) {
        StringBuilder res = new StringBuilder();
        //处理[]中的寻址模式
        String[] elements = s.split(" ");
        for (String element : elements) {
            if (element.startsWith("0x") || StringUtils.isNumeric(element)) {
                res.append("imm ");
            } else if (element.equals("+") || element.equals("-")) {
                res.append("+/- ");
            } else if (element.contains("*")) {
                String[] split = element.split("\\*");
                res.append(getRegType(split[0]));
                res.append("*");
                res.append(split[1]);
                res.append(" ");
            } else {
                res.append(getRegType(element));
                res.append(" ");
            }
        }

        return res.toString().trim();
    }

    /**
     * 处理rax,rbx,mmx这种寄存器分类
     */
    public String getRegType(String s) {

        if (s.equals("rip")) {
            return "rip";
        } else if (reg8s.contains(s)) {
            return "reg8";
        } else if (reg16s.contains(s)) {
            return "reg16";
        } else if (reg32s.contains(s)) {
            return "reg32";
        } else if (reg64s.contains(s)) {
            return "reg64";
        } else if (xmms.contains(s)) {
            return "xmm";
        } else if (mmxs.contains(s)) {
            return "mmx";
        } else {
            return "unknowReg";
        }
    }
}
