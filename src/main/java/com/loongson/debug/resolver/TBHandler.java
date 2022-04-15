package com.loongson.debug.resolver;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.IR1;
import com.loongson.debug.entity.IR2;
import com.loongson.debug.dto.TBBlockDTO;
import com.loongson.debug.entity.TbBlock;
import com.loongson.debug.service.ITbBlockService;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TBHandler {

    private ITbBlockService iTbBlockService;


    private ColorHandler colorHandler;
    private boolean colorCorrection;
    private IR1Handler ir1Handler;
    private IR2Handler ir2Handler;
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

    public ITbBlockService getiTbBlockService() {
        return iTbBlockService;
    }

    public void setiTbBlockService(ITbBlockService iTbBlockService) {
        this.iTbBlockService = iTbBlockService;
    }

    public void setColorCorrection(boolean colorCorrection) {

        this.colorCorrection = colorCorrection;
    }

    public void handle(BufferedReader br, int index) throws IOException {

        String line = "";
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("splitData/TB" + index + ".txt"))));

        while (!(line = br.readLine()).startsWith("tr_fini OK")) {
            if (colorCorrection) line = colorHandler.handle(line);
            output.append(line + "\r");

        }
        output.append(line);
        output.flush();
        output.close();
        output = null;
    }

    public TbBlock handleT(BufferedReader br, int index, int ltid) throws IOException {
        TBBlockDTO tbBlockDto = null;
        String TBAddress = "";
        String startAddressIR1 = "";
        String endAddressIR1 = "";
        String startAddressIR2 = "";
        String endAddressIR2 = "";
        int IR1Num = 0;
        int IR2Num = 0;
        ArrayList<IR1> IR1Inst = new ArrayList<>();
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
                    String s = matcher.group(1);
                    IR2Map.add(Integer.parseInt(s));
                }

            } else if (line.startsWith("IR2[")) {
                IR2 ir2 = ir2Handler.handle(line);
                IR2Inst.add(ir2);
            }
        }
        startAddressIR1 = IR1Inst.get(0).getAddress();
        startAddressIR2 = IR2Inst.get(0).getAddress();
        endAddressIR1 = IR1Inst.get(IR1Num - 1).getAddress();
        endAddressIR2 = IR2Inst.get(IR2Inst.size() - 1).getAddress();

        //计算map
        int lastid = IR2Inst.get(IR2Inst.size() - 1).getId() + 1;
        IR2Map.add(lastid);


        //map中要保证有
        for (int i = IR2Map.size(); i <= IR1Num; i++) {
            IR2Map.add(lastid);
        }

//        System.out.println(IR1Num);
//        System.out.println(IR2Map);

        //tbblock初始化
        TbBlock tbBlock = new TbBlock(ltid, index, TBAddress, startAddressIR1, endAddressIR1, startAddressIR2, endAddressIR2, IR1Num, IR2Num,
                JSON.toJSONString(IR1Inst),
                JSON.toJSONString(IR2Inst),
                JSON.toJSONString(IR2Map));


        return tbBlock;
    }

    public TBBlockDTO convert(File file, int index) {
        TBBlockDTO tbBlockDto = null;
        String TBAddress = "";
        String startAddressIR1 = "";
        String endAddressIR1 = "";
        String startAddressIR2 = "";
        String endAddressIR2 = "";
        int IR1Num = 0;
        int IR2Num = 0;
        ArrayList<Integer> validLineInvoke = new ArrayList<>();
        ArrayList<IR1> IR1Inst = new ArrayList<>();
        ArrayList<IR2> IR2Inst = new ArrayList<>();

        ArrayList<Integer> IR2Map = new ArrayList<>();
//        HashMap<Integer, ArrayList<IR2>> IR2Map = new HashMap<>();
        try {
            InputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr, 5 * 1024 * 1024);
            String line = "";
            while ((line = br.readLine()) != null) {
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
                    //计算印射关系
                    //拿出括号中的值
                    String s = line.split(",")[0].substring(1);
                    validLineInvoke.add(Integer.parseInt(s));
                } else if (line.startsWith("IR2[")) {
                    IR2 ir2 = ir2Handler.handle(line);
                    IR2Inst.add(ir2);
                }
            }
            startAddressIR1 = IR1Inst.get(0).getAddress();
            startAddressIR2 = IR2Inst.get(0).getAddress();
            endAddressIR1 = IR1Inst.get(IR1Num - 1).getAddress();
            endAddressIR2 = IR2Inst.get(IR2Inst.size() - 1).getAddress();

        } catch (Exception e) {
            e.printStackTrace();
        }


        tbBlockDto = new TBBlockDTO(1, index, TBAddress, startAddressIR1, endAddressIR1, startAddressIR2, endAddressIR2, IR1Num, IR2Num, IR1Inst, IR2Inst, IR2Map);
        return tbBlockDto;
    }
}
