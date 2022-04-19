package com.loongson.debug;

import com.loongson.debug.entity.LtlogInstructionMap;
import com.loongson.debug.entity.TBAnalysis;
import org.junit.Test;

import java.util.*;

public class DataAnalysisTest {
    Map<String, List<Integer>> addressInstructionsMap = new HashMap<>();
    List<Integer> instructions1 = new ArrayList<>();
    List<Integer> instructions2 = new ArrayList<>();
    List<Integer> instructions3 = new ArrayList<>();
    ArrayList<TBAnalysis> tbAnalyses = new ArrayList<>();

    public void init() {

        instructions1.add(1);
        instructions1.add(2);
        instructions1.add(3);
        instructions1.add(4);

        instructions2.add(2);
        instructions2.add(3);
        instructions2.add(5);



        instructions3.add(2);
        instructions3.add(4);
        instructions3.add(5);

        addressInstructionsMap.put("0x1", instructions1);
        addressInstructionsMap.put("0x2", instructions2);
        addressInstructionsMap.put("0x3", instructions3);

        TBAnalysis tbAnalysis1 = new TBAnalysis();
        tbAnalysis1.setPc("0x1");
        tbAnalysis1.setExecTimes(100L);
        TBAnalysis tbAnalysis2 = new TBAnalysis();
        tbAnalysis2.setPc("0x2");
        tbAnalysis2.setExecTimes(50L);
        TBAnalysis tbAnalysis3 = new TBAnalysis();
        tbAnalysis3.setPc("0x3");
        tbAnalysis3.setExecTimes(30L);


        tbAnalyses.add(tbAnalysis1);
        tbAnalyses.add(tbAnalysis2);
        tbAnalyses.add(tbAnalysis3);
    }

    @Test
    public void testExecCal() {
        init();
        int ltid = 1;
        //instructionIndex <-> num
        Map<Integer, Long> instructionNums = new HashMap<>();


        ArrayList<String> addressNotFound = new ArrayList<>();
        System.out.println(addressInstructionsMap);
        //获取tb块的运行次数
        for (TBAnalysis tbAnalysis : tbAnalyses) {
            String pc = tbAnalysis.getPc();
            try {
                for (Integer integer : addressInstructionsMap.get(pc)) {
                    if (instructionNums.containsKey(integer)) {
                        long tmp_num = instructionNums.get(integer);
                        instructionNums.replace(integer, tmp_num + tbAnalysis.getExecTimes());
                    } else {
                        instructionNums.put(integer, tbAnalysis.getExecTimes());
                    }
                }
            } catch (Exception e) {
                addressNotFound.add(pc);
            }
        }

        ArrayList<LtlogInstructionMap> updateLtlogInstructionList = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : instructionNums.entrySet()) {
            LtlogInstructionMap ltlogInstructionMap = new LtlogInstructionMap();
            ltlogInstructionMap.setLtid(ltid);
            ltlogInstructionMap.setIndexx(entry.getKey());
            ltlogInstructionMap.setNum(entry.getValue());
            updateLtlogInstructionList.add(ltlogInstructionMap);

        }
        System.out.println("address not found list:" + addressNotFound);
        System.out.println(updateLtlogInstructionList);
        for (LtlogInstructionMap ltlogInstructionMap : updateLtlogInstructionList) {
            System.out.println(ltlogInstructionMap.getIndexx()+":"+ltlogInstructionMap.getNum());
        }

    }
}
