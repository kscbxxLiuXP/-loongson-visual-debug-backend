package com.loongson.debug;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.Command;
import com.loongson.debug.entity.IR1;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonTest {
    ArrayList<IR1> IR1Instr = new ArrayList<>();
    String IR1InstrString = "";
    private HashMap<Integer, ArrayList<IR1>> IR2Map = new HashMap<>();
    String IR2MapString = "";

    void produceData() {

        IR1 ir11 = new IR1(1, "0x123456", new Command("mov", "eax", 1));
        IR1 ir12 = new IR1(2, "0xs2226", new Command("jmp", "ebx", 1));
        IR1 ir13 = new IR1(3, "0xsda56", new Command("jne", "ecx", 1));
        IR1 ir14 = new IR1(4, "0xhfgds56", new Command("push", "itemp0", 1));
        IR1 ir15 = new IR1(5, "0xvsas456", new Command("pop", "eax", 1));

        IR1Instr.add(ir11);
        IR1Instr.add(ir12);
        IR1Instr.add(ir13);
        IR1Instr.add(ir14);
        IR1Instr.add(ir15);

        IR2Map.put(1, IR1Instr);
        IR2Map.put(2, IR1Instr);
        IR2Map.put(3, IR1Instr);

        IR1InstrString = "[{\"address\":\"0x123456\",\"id\":1,\"instruction\":{\"operand\":\"eax\",\"operator\":\"mov\",\"type\":1}},{\"address\":\"0xs2226\",\"id\":2,\"instruction\":{\"operand\":\"ebx\",\"operator\":\"jmp\",\"type\":1}},{\"address\":\"0xsda56\",\"id\":3,\"instruction\":{\"operand\":\"ecx\",\"operator\":\"jne\",\"type\":1}},{\"address\":\"0xhfgds56\",\"id\":4,\"instruction\":{\"operand\":\"itemp0\",\"operator\":\"push\",\"type\":1}},{\"address\":\"0xvsas456\",\"id\":5,\"instruction\":{\"operand\":\"eax\",\"operator\":\"pop\",\"type\":1}}]\n";
        IR2MapString = "{1:[{\"address\":\"0x123456\",\"id\":1,\"instruction\":{\"operand\":\"eax\",\"operator\":\"mov\",\"type\":1}},{\"address\":\"0xs2226\",\"id\":2,\"instruction\":{\"operand\":\"ebx\",\"operator\":\"jmp\",\"type\":1}},{\"address\":\"0xsda56\",\"id\":3,\"instruction\":{\"operand\":\"ecx\",\"operator\":\"jne\",\"type\":1}},{\"address\":\"0xhfgds56\",\"id\":4,\"instruction\":{\"operand\":\"itemp0\",\"operator\":\"push\",\"type\":1}},{\"address\":\"0xvsas456\",\"id\":5,\"instruction\":{\"operand\":\"eax\",\"operator\":\"pop\",\"type\":1}}],2:[{\"$ref\":\"$[1][0]\"},{\"$ref\":\"$[1][1]\"},{\"$ref\":\"$[1][2]\"},{\"$ref\":\"$[1][3]\"},{\"$ref\":\"$[1][4]\"}],3:[{\"$ref\":\"$[1][0]\"},{\"$ref\":\"$[1][1]\"},{\"$ref\":\"$[1][2]\"},{\"$ref\":\"$[1][3]\"},{\"$ref\":\"$[1][4]\"}]}\n";
    }


    @Test
    void convertListToJson() {
        produceData();
        String UserJson = JSON.toJSONString(IR1Instr);
        System.out.println(UserJson);
    }

    @Test
    void convertJsonToList() {
        produceData();
        List<IR1> ir1s = JSON.parseArray(IR1InstrString, IR1.class);
        System.out.println(ir1s);
    }

    @Test
    void convertMapToJson() {
        produceData();
        String map = JSON.toJSONString(IR2Map);
        System.out.println(map);
    }

    @Test
    void convertJsonToMap() {
        produceData();
        HashMap<Integer, ArrayList<IR1>> hashMap = JSON.parseObject(IR2MapString, HashMap.class);
        System.out.println(hashMap);
        System.out.println(hashMap.get(1));
    }
    @Test
    void convertMapToJson2() {
        produceData();
        HashMap<String,Integer> map = new HashMap<>();
        map.put("0x123531",2626);
        map.put("0x123532",12);
        map.put("0x123533",22);
        map.put("0x123534",345);
        map.put("0x123535",3432);
        map.put("0x123536",232424);
        String map1 = JSON.toJSONString(map);
        System.out.println(map1);
    }
}
