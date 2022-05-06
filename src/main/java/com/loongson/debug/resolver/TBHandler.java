package com.loongson.debug.resolver;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.IR1;
import com.loongson.debug.entity.IR2;
import com.loongson.debug.dto.TBBlockDTO;
import com.loongson.debug.entity.LtlogInstructionMap;
import com.loongson.debug.entity.TbBlock;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TBHandler {


    private final ColorHandler colorHandler;
    private boolean colorCorrection;
    private final IR1Handler ir1Handler;
    private final IR2Handler ir2Handler;
    private final Pattern pattern = Pattern.compile("^\\[(\\d+), \\d+].*$");

    public TBHandler() {
        this.colorHandler = new ColorHandler();
        this.colorCorrection = true;
        this.ir1Handler = new IR1Handler();
        this.ir2Handler = new IR2Handler();


    }

    public TBHandler(boolean colorCorrection) {
        this.colorHandler = new ColorHandler();
        this.colorCorrection = colorCorrection;
        this.ir1Handler = new IR1Handler();
        this.ir2Handler = new IR2Handler();
    }


    public void setColorCorrection(boolean colorCorrection) {

        this.colorCorrection = colorCorrection;
    }


    public TbBlock handleT(BufferedReader br, int index, int ltid, Map<String, LtlogInstructionMap> map, String logType) throws IOException {
        OperandHandler operandHandler = new OperandHandler();
        String TBAddress = "";
        String startAddressIR1 = "";
        String endAddressIR1 = "";
        String startAddressIR2 = "";
        String endAddressIR2 = "";
        int IR1Num = 0;
        int IR2Num = 0;
        ArrayList<IR1> IR1Inst = new ArrayList<>();
        ArrayList<IR2> IR2InstTmp = new ArrayList<>();
        ArrayList<IR2> IR2Inst = new ArrayList<>();
        ArrayList<Integer> IR2Map = new ArrayList<>();
        String line = "";
        while ((line = br.readLine()) != null) {
            if (colorCorrection)
                line = colorHandler.handle(line);
            if (line.startsWith("tr_fini OK"))
                break;
            if (line.charAt(0) == '|') {
                String s = line.split(":")[1];

                String[] split = s.split("\\|");
                TBAddress = line.split(":")[1].split("\\|")[0].replaceAll(" ", "");
            } else if (line.startsWith("IR1 num")) {
                IR1Num = Integer.parseInt(line.split(" ")[3]);
            } else if (line.startsWith("IR2 num")) {
                IR2Num = Integer.parseInt(line.split(" ")[3]);
            } else if (line.startsWith("IR1[")) {
                IR1 ir1 = ir1Handler.handle(line);
                IR1Inst.add(ir1);
            } else if (line.startsWith("[") && line.charAt(1) != 'L') {
                //正则匹配
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int i = Integer.parseInt(matcher.group(1));
                    //处理profile类型日志，首个指令是用来计数的问题
                    if (logType.equals("2") && i == 0) {
                        //跳过计数指令
                        continue;
                    } else if (logType.equals("2")) {
                        i = i - 4;//减去计数指令翻译后的4条指令
                    }

                    IR2Map.add(i);
                }
            } else if (line.startsWith("IR2[")) {
                IR2 ir2 = ir2Handler.handle(line);
                //过滤计数翻译指令
                if (logType.equals("2")) {
                    //profile日志
                    if (ir2.getId() > 4) {
                        ir2.setId(ir2.getId() - 4);
                        IR2InstTmp.add(ir2);
                    }

                } else {
                    //普通日志直接添加
                    IR2InstTmp.add(ir2);

                }
            }
        }
        startAddressIR1 = IR1Inst.get(0).getAddress();
        startAddressIR2 = IR2InstTmp.get(0).getAddress();
        endAddressIR1 = IR1Inst.get(IR1Num - 1).getAddress();
        endAddressIR2 = IR2InstTmp.get(IR2InstTmp.size() - 1).getAddress();

        //计算map

        //处理尾部跳转指令如call，jne,je等等，将一大段的翻译进行缩减
        //最终map=[0,4,9,11,14,16,21,24,27,30,33,36,51](51是末项id)
        //当前map=[0,4,9,11,14,16,21,24,27,30,33,36](51是末项id)
        //先将最后一条指令前的指令存储，之后单独处理最后一个跳转指令
        int lastMapId;
        if (IR1Num!=1){
             lastMapId= IR2Map.get(IR2Map.size() - 1);
        }else{
             lastMapId = -1;
        }

        for (IR2 ir2 : IR2InstTmp) {
            if (ir2.getId() <= lastMapId) {
                //最后一条ir1之前的指令全部存储
                IR2Inst.add(ir2);
            } else {

                //处理最后
                if (ir2.getInstruction().getOperator().equals("b")) {
                    break;
                }
                IR2Inst.add(ir2);

            }
        }
        int lastid_new;
        if (IR2Inst.size()==0){
            //只有单指令 'b **'
             lastid_new = 2;
        }else{
            lastid_new  = IR2Inst.get(IR2Inst.size()-1).getId()+2;
        }
        IR2 lastInst = IR2InstTmp.get(IR2InstTmp.size() - 1);
        lastInst.setId(lastid_new-1);
        IR2Inst.add(lastInst);


        //map中要保证有相匹配数量的index
        for (int i = IR2Map.size(); i <= IR1Num; i++) {
            IR2Map.add(lastid_new);
        }

        //统计IR1指令是否已经翻译过
        //这个list记录的是这个TB块包含哪些指令，这些指令在全局map中的id
        ArrayList<Integer> instructions = new ArrayList<>();
        for (int i = 0; i < IR1Inst.size(); i++) {
            IR1 ir1 = IR1Inst.get(i);
            String key = ir1.getInstruction().getOperator() + ir1.getInstruction().getOperand();
            if (map.containsKey(key)) {
                instructions.add(map.get(key).getIndexx());
            } else {
                LtlogInstructionMap ltlogInstructionMap = new LtlogInstructionMap();
                int indexx = map.size() + 1;
                String id = ltid + "-" + indexx;
                ltlogInstructionMap.setUid(id);
                ltlogInstructionMap.setIndexx(indexx);
                ltlogInstructionMap.setLtid(ltid);

                ltlogInstructionMap.setOperator(ir1.getInstruction().getOperator());
                ltlogInstructionMap.setOperand(ir1.getInstruction().getOperand());
                String operandPattern = operandHandler.pattern(ltlogInstructionMap.getOperand());
                ltlogInstructionMap.setPattern(operandPattern);

                ltlogInstructionMap.setIr1execute(0L);
                int startid = IR2Map.get(i);
                int endid = IR2Map.get(i + 1);
                ArrayList<String> ir1MapIR2 = new ArrayList<>();
                for (IR2 ir2 : IR2Inst) {
                    if (ir2.getId() >= startid && ir2.getId() <= endid) {
                        ir1MapIR2.add(ir2.getInstruction().toStingSimple());
                    }
                }
                ltlogInstructionMap.setIr2instruction(JSON.toJSONString(ir1MapIR2));
                ltlogInstructionMap.setIr2num(ir1MapIR2.size());
                instructions.add(map.size() + 1);
                map.put(key, ltlogInstructionMap);
            }
        }
//        System.out.println(instructions);
//        System.out.println(IR1Num);
//        System.out.println(IR2Map);

        //tbblock初始化
        TbBlock tbBlock = new TbBlock(ltid, index, TBAddress, startAddressIR1, endAddressIR1, startAddressIR2, endAddressIR2, IR1Num, IR2Num,
                JSON.toJSONString(IR1Inst),
                JSON.toJSONString(IR2Inst),
                JSON.toJSONString(IR2Map), JSON.toJSONString(instructions));
        return tbBlock;
    }
}
