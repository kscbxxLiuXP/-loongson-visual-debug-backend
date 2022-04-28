package com.loongson.debug.resolver;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loongson.debug.entity.LtlogAnalysis;
import com.loongson.debug.entity.LtlogInstructionMap;
import com.loongson.debug.entity.LtlogInstructionPattern;
import com.loongson.debug.entity.TBAnalysis;
import com.loongson.debug.service.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProfileHandler {

    private ILtLogAnalysisService iLtLogAnalysisService;
    private ITbAnalysisService tbAnalysisService;
    private ILtlogInstructionMapService ltlogInstructionMapService;
    private ITbBlockService tbBlockService;

    private ILtlogInstructionPatternService ltlogInstructionPatternService;

    public void init(ILtLogAnalysisService iLtLogAnalysisService,
                     ITbAnalysisService tbAnalysisService,
                     ILtlogInstructionMapService ltlogInstructionMapService,
                     ITbBlockService tbBlockService,
                     ILtlogInstructionPatternService ltlogInstructionPatternService
    ) {
        this.iLtLogAnalysisService = iLtLogAnalysisService;
        this.tbAnalysisService = tbAnalysisService;
        this.ltlogInstructionMapService = ltlogInstructionMapService;
        this.tbBlockService = tbBlockService;
        this.ltlogInstructionPatternService = ltlogInstructionPatternService;
    }

    public ArrayList<String> handleT(BufferedReader br, int ltid) throws IOException {
        LtlogAnalysis ltlogAnalysis = new LtlogAnalysis(ltid);
        ltlogAnalysis.setProfiled(true);
        ArrayList<TBAnalysis> tbAnalysisArrayList = new ArrayList<>();

        String line = "";
        while (!line.equals("Translation buffer state:")) {
            line = br.readLine();
        }

        while ((line = br.readLine()) != null) {
            if (line.startsWith("gen code size")) {
                String[] split = line.split(" ");
                String[] data = split[split.length - 1].split("/");
                ltlogAnalysis.setGenCodeSize1(Long.parseLong(data[0]));
                ltlogAnalysis.setGenCodeSize2(Long.parseLong(data[1]));
            } else if (line.startsWith("TB count")) {
                String[] split = line.split(" ");
                String result = split[split.length - 1];
                ltlogAnalysis.setTbCount(Integer.parseInt(result));
            } else if (line.startsWith("TB avg target size")) {
                String[] split = line.split(" ");
                String result1 = split[5];
                String result2 = split[6].split("=")[1];
                ltlogAnalysis.setTbAvgTargetSize(Integer.parseInt(result1));
                ltlogAnalysis.setTbAvgTargetSizeMax(Integer.parseInt(result2));
            } else if (line.startsWith("TB avg host size")) {
                line = StringUtils.deleteWhitespace(line);
                String[] split = line.split(":");
                String result1 = StringUtils.getDigits(split[0]);
                String result2 = split[1].split("\\)")[0];
                ltlogAnalysis.setTbAvgHostSize(Integer.parseInt(result1));
                ltlogAnalysis.setTbAvgHostSizeExpansionRatio(Double.parseDouble(result2));
            } else if (line.startsWith("cross page TB count")) {
                line = StringUtils.deleteWhitespace(line);
                String[] split = StringUtils.split(line, "(");
                String result1 = StringUtils.getDigits(split[0]);
                String result2 = StringUtils.removeEnd(split[1], "%)");
                ltlogAnalysis.setCrossPageTbCount(Integer.parseInt(result1));
                ltlogAnalysis.setCrossPageTbCountPercent(Double.parseDouble(result2));
            } else if (line.startsWith("direct jump count")) {
                String result1;
                String result2;
                String result3;
                String result4;
                //[0]direct jump count   8636
                //[1]76.5%)
                //[2]2 jumps=7108 62.6%)
                String[] split1 = line.split("\\(");
                result1 = StringUtils.getDigits(split1[0]);

                result2 = split1[1].split("%")[0];

                //[0]2
                //[1]jumps=7108
                //[2]62.6%)
                String[] split = split1[2].split(" ");

                result3 = StringUtils.getDigits(split[1]);
                result4 = split[2].split("%")[0];
                ltlogAnalysis.setDirectJumpCount(Integer.parseInt(result1));
                ltlogAnalysis.setDirectJumpCountPercent(Double.parseDouble(result2));
                ltlogAnalysis.setDirectJumpCount2Jumps(Integer.parseInt(result3));
                ltlogAnalysis.setDirectJumpCount2JumpsPercent(Double.parseDouble(result4));
            } else if (line.startsWith("TB exec detail info:")) {
                line = br.readLine();
                line = br.readLine();
                while (!line.equals("-- Summary:")) {
                    String[] split = StringUtils.split(line);
                    TBAnalysis tbAnalysis = new TBAnalysis(ltid);
                    String pc = "0x" + split[0].replaceAll("^(0+)", "");
                    String end_pc = "0x" + split[1].replaceAll("^(0+)", "");
                    tbAnalysis.setPc(pc);
                    tbAnalysis.setEndPc(end_pc);
                    tbAnalysis.setExecTimes(Long.parseLong(split[2]));
                    tbAnalysis.setExitTimes(Integer.parseInt(split[3]));
                    tbAnalysis.setRunIr1Num(Long.parseLong(split[4]));
                    tbAnalysis.setRunIr2Num(Long.parseLong(split[5]));
                    tbAnalysis.setRunExpRate(Double.parseDouble(split[6]));
                    tbAnalysis.setIr1Num(Long.parseLong(split[7]));
                    tbAnalysis.setIr2Num(Long.parseLong(split[8]));
                    tbAnalysis.setExpRate(Double.parseDouble(split[9]));
                    tbAnalysisArrayList.add(tbAnalysis);
                    line = br.readLine();
                }
            } else if (line.startsWith("tb run times ")) {
                String digits = StringUtils.getDigits(line);
                ltlogAnalysis.setTbRunTimes(Long.parseLong(digits));

            } else if (line.startsWith("tb exit times")) {
                String digits = StringUtils.getDigits(line);
                ltlogAnalysis.setTbExitTimes(Long.parseLong(digits));
            } else if (line.startsWith("TB hash buckets")) {
                String[] split = StringUtils.split(line);
                String result1 = split[3].split("/")[0];
                String result2 = split[3].split("/")[1];
                String result3 = split[4].split("%")[0].substring(1);
                ltlogAnalysis.setTbHashBuckets1(Integer.parseInt(result1));
                ltlogAnalysis.setTbHashBuckets2(Integer.parseInt(result2));
                ltlogAnalysis.setTbHashBucketsPercent(Double.parseDouble(result3));
            } else if (line.startsWith("TB hash occupancy")) {
                String[] s = line.split("%")[0].split(" ");
                String result = s[s.length - 1];
                ltlogAnalysis.setTbHashOccupancy(Double.parseDouble(result));
            } else if (line.startsWith("TB hash avg chain")) {
                String[] s = StringUtils.split(line);
                String result = s[4];
                ltlogAnalysis.setTbHashAvgChain(Double.parseDouble(result));
            }
        }
        //更新ltlogAnalysis
        iLtLogAnalysisService.updateById(ltlogAnalysis);
//        System.out.println(ltlogAnalysis);
        //保存tb_analysis
        tbAnalysisService.saveBatch(tbAnalysisArrayList);
//        System.out.println(tbAnalysisArrayList);

        ArrayList<String> addressNotFound = updateInstructionExecuteTime(tbAnalysisArrayList, ltid);
        return addressNotFound;
    }

    //根据TB块的执行次数统计指令执行次数
    //更新lt_log_instruction_map
    public ArrayList<String> updateInstructionExecuteTime(ArrayList<TBAnalysis> tbAnalyses, int ltid) {
        //instructionIndex <-> LtlogInstructionMap
        Map<Integer, LtlogInstructionMap> map = new HashMap<>();
        List<LtlogInstructionMap> ltlogInstructionMapList = ltlogInstructionMapService.list(new QueryWrapper<LtlogInstructionMap>().eq("ltid", ltid));
        for (LtlogInstructionMap ltlogInstructionMap : ltlogInstructionMapList) {
            map.put(ltlogInstructionMap.getIndexx(), ltlogInstructionMap);
        }

        //获取tb块的instructions
        //'0x123'-> [1, 2 , 4 ，1]
        //list中的数字表示tb块中指令对应的id
        Map<String, ArrayList<Integer>> addressInstructionsMap = tbBlockService.getAddressInstructionsMap(ltid);
        ArrayList<String> addressNotFound = new ArrayList<>();
        System.out.println(addressInstructionsMap);
        //获取tb块的运行次数
        for (TBAnalysis tbAnalysis : tbAnalyses) {
            String pc = tbAnalysis.getPc();
            try {
                //给每一个指令计数
                for (Integer integer : addressInstructionsMap.get(pc)) {
                    LtlogInstructionMap ltlogInstructionMap = map.get(integer);
                    long tmp_num = ltlogInstructionMap.getIr1execute();
                    ltlogInstructionMap.setIr1execute(tmp_num + tbAnalysis.getExecTimes());
                }
            } catch (Exception e) {
                addressNotFound.add(pc);
            }
        }
        //总的ir1
        long sumir1 = 0L;
        //总的ir2
        long sumir2 = 0L;
        //patternString -> pattern model
        HashMap<String, LtlogInstructionPattern> patternHashMap = new HashMap<>();
        for (LtlogInstructionMap ltlogInstructionMap : ltlogInstructionMapList) {
            ltlogInstructionMap.setIr2execute(ltlogInstructionMap.getIr1execute() * ltlogInstructionMap.getIr2num());
            String patternKey = ltlogInstructionMap.getOperator() + ltlogInstructionMap.getPattern();
            if (patternHashMap.containsKey(patternKey)) {
                patternHashMap.get(patternKey).increaseIr1execute(ltlogInstructionMap.getIr1execute());
                patternHashMap.get(patternKey).increaseIr2execute(ltlogInstructionMap.getIr2execute());
            } else {
                LtlogInstructionPattern ltlogInstructionPattern = new LtlogInstructionPattern(ltlogInstructionMap);
                patternHashMap.put(patternKey, ltlogInstructionPattern);

            }

            sumir1 += ltlogInstructionMap.getIr1execute();
            sumir2 += ltlogInstructionMap.getIr2execute();
        }
        long finalSumir1 = sumir1;
        long finalSumir2 = sumir2;
        ltlogInstructionMapList.forEach(e -> {
            e.setSumir1(finalSumir1);
            e.setSumir2(finalSumir2);
        });

        //统计pattern总数
        Map<String, List<LtlogInstructionPattern>> afterGroupMap
                = patternHashMap.values().stream().
                collect(Collectors.groupingBy(LtlogInstructionPattern::getOperator));
        ArrayList<LtlogInstructionPattern> ltlogInstructionMaps = new ArrayList<>();
        for (Map.Entry<String, List<LtlogInstructionPattern>> entry : afterGroupMap.entrySet()) {
            long sumir11 = entry.getValue().stream().mapToLong(LtlogInstructionPattern::getIr1execute).sum();
            long sumir22 = entry.getValue().stream().mapToLong(LtlogInstructionPattern::getIr2execute).sum();
            entry.getValue().forEach(e -> {
                        e.setSumir1(sumir11);
                        e.setSumir2(sumir22);
                        e.setSumir1all(finalSumir1);
                        e.setSumir2all(finalSumir2);
                        ltlogInstructionMaps.add(e);
                    }
            );
        }

        ltlogInstructionPatternService.saveBatch(ltlogInstructionMaps);


        System.out.println("address not found list:" + addressNotFound);
        ltlogInstructionMapService.updateBatchById(ltlogInstructionMapList);
        return addressNotFound;
    }
}
