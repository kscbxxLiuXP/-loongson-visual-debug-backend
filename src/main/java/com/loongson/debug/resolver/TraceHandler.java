package com.loongson.debug.resolver;

import com.loongson.debug.dto.TraceDTO;
import com.loongson.debug.dto.TraceItem;
import com.loongson.debug.service.ITbBlockService;

import java.io.*;
import java.util.*;

public class TraceHandler {

    private ITbBlockService tbBlockService;

    public void init(ITbBlockService tbBlockService) {
        this.tbBlockService = tbBlockService;
    }

    /**
     * 给定一个输入行，返回指定个数的寄存器信息
     *
     * @param line 输入行信息
     * @param num  解析寄存器个数
     * @return 寄存器键值信息
     * @example EAX=00000000 EBX=00000000 ECX=00000000 EDX=00000000
     */
    public Map<String, String> getRegisterInfo(String line, int num) {
        Map<String, String> re = new HashMap<>();
        String[] split = line.split(" ");
        for (int i = 0; i < num; i++) {
            String sub = split[i];//EAX=00000000
            String[] subSplit = sub.split("=");
            String registerName = subSplit[0];
            String registerValue = subSplit[1];
            re.put(registerName, registerValue);
        }
        return re;
    }

    public TraceDTO handle(File file, int ltid) throws IOException {
        /*
          address(x86)->TraceItem的映射
          TraceItem已经初始化过，对应的TB块信息已经被赋值
         */
        Map<String, TraceItem> addressTraceItemMap = tbBlockService.getStringTbBlockTraceItemMap(ltid);

        String line = "";
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr, 5 * 1024 * 1024);

        // 记录上一个解析的是Trace还是TB Link，-1表示上一个不是Trace，
        // 如果上一个是Trace，下一个又是Trace，那么可以说明间接跳转关系
        String previousTrace = "-1";
        while ((line = br.readLine()) != null) {
            //映射头
            if (line.startsWith("Trace")) {
                Map<String, String> registers = new HashMap<>();
                //文本分割的方式获取
                //String[] split = line.split(" ");
                //String[] split2 = split[3].split("/");
                //String address = "0x"+split2[1];

                //subString的方式获取
                String address = "0x" + line.substring(32, 40);

                //地址与TraceItem的类型的对应关系 ['0x3f7a79b2']->['syscall']
                String type = null;
                if (line.length() > 51) {
                    type = line.substring(51);
                    addressTraceItemMap.get(address).setTbtype(type);
                }

                //第一行寄存器信息 [EAX ~ EDX]
                line = br.readLine();
                registers.putAll(getRegisterInfo(line, 4));

                //第二行寄存器信息 [ESI ~ EDI]
                line = br.readLine();
                registers.putAll(getRegisterInfo(line, 4));

                //第三行寄存器信息 [EIP ~ EFL]
                line = br.readLine();
                registers.putAll(getRegisterInfo(line, 2));

                //上一个解析单元也是Trace
                if (!previousTrace.equals("-1")) {

                    //设置当前Trace间接跳转信息
                    addressTraceItemMap.get(address).setIndirectFrom(previousTrace);
                    //设置上一个Trace间接跳转信息
                    addressTraceItemMap.get(previousTrace).setIndirectTo(address);

                }

                //设置TraceItem的寄存器状态
                addressTraceItemMap.get(address).setRegisters(registers);


                previousTrace = address;

            } else if (line.startsWith("Linking TBs")) {
                previousTrace = "-1";
                //split方法提取
                //String[] split = line.split(" ");
                ////fromAddress->toAddress
                //String fromAddress = split[2];
                //String toAddress = split[7];

                String fromAddress = "0x" + line.substring(26, 34);
                String toAddress = "0x" + line.substring(61, 69);

                if (!addressTraceItemMap.containsKey(toAddress)) {
                    System.out.println(fromAddress);
                }
                addressTraceItemMap.get(fromAddress).getNextids().add(toAddress);
                addressTraceItemMap.get(toAddress).getParents().add(fromAddress);

            }
        }
        //将map转化成list
        ArrayList<TraceItem> list = new ArrayList<>(addressTraceItemMap.values()) ;

        list.sort(new MapKeyComparator());
        for (TraceItem traceItem : list) {
            System.out.println(traceItem.toString1());
        }
        return new TraceDTO(list, ltid);
    }

    public void printMap(Map<TraceItem, List<TraceItem>> map) {

    }

    public Map<TraceItem, List<TraceItem>> sortMapByKey(Map<TraceItem, List<TraceItem>> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<TraceItem, List<TraceItem>> sortMap = new TreeMap<TraceItem, List<TraceItem>>(new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

    static class MapKeyComparator implements Comparator<TraceItem> {
        @Override
        public int compare(TraceItem traceItem1, TraceItem traceItem2) {
            return Integer.compare(traceItem1.getId(), traceItem2.getId());
//            return addressCompare(traceItem1.getAddress(), traceItem2.getAddress());
        }
    }

    public int addressCompare(String address1, String address2) {
        long l = Long.parseLong(address1.substring(2), 16);
        long l2 = Long.parseLong(address2.substring(2), 16);

        if (l == l2) return 0;
        if (l > l2) return 1;
        else return -1;
    }
}
