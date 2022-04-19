package com.loongson.debug.resolver;

import com.loongson.debug.entity.Head;
import com.loongson.debug.entity.LtlogAnalysis;
import com.loongson.debug.entity.LtlogInstructionMap;
import com.loongson.debug.entity.TbBlock;
import com.loongson.debug.service.ILtLogAnalysisService;
import com.loongson.debug.service.ILtlogInstructionMapService;
import com.loongson.debug.service.ITbBlockService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class OfflineResolver {

    private ITbBlockService iTbBlockService;
    private ILtlogInstructionMapService ltlogInstructionMapService;
    private ILtLogAnalysisService ltLogAnalysisService;
    private static OfflineResolver offlineResolver;
    private HeadHandler headHandler;
    private TBHandler tbHandler;

    private OfflineResolver() {
        headHandler = new HeadHandler();
        tbHandler = new TBHandler();

    }

    public void setLtLogAnalysisService(ILtLogAnalysisService ltLogAnalysisService) {
        this.ltLogAnalysisService = ltLogAnalysisService;
    }

    public static OfflineResolver getInstance() {
        if (offlineResolver == null) offlineResolver = new OfflineResolver();
        return offlineResolver;
    }

    public void setLtlogInstructionMapService(ILtlogInstructionMapService ltlogInstructionMapService) {
        this.ltlogInstructionMapService = ltlogInstructionMapService;
    }

    public void setiTbBlockService(ITbBlockService iTbBlockService) {
        this.iTbBlockService = iTbBlockService;
    }

    /**
     * <p>
     * 将文件分割成段存储到文件中
     * </p>
     *
     * @author liuxp
     * @since 2022-02-28
     */
    public Head resolve(File file, int cacheSizeMB, int ltid) {
        Map<String, LtlogInstructionMap> map = new HashMap<>();
        ArrayList<TbBlock> tbBlocks = new ArrayList<>();
        Head head = null;
        try {
            InputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr, cacheSizeMB * 1024 * 1024);

            head = headHandler.handleR(br);

            String line = "";
            int tbnum = 0;
            System.out.println("解析TB块");
            long startTime = System.currentTimeMillis();   //获取开始时间
            while ((line = br.readLine()) != null) {
                TbBlock tbBlock = tbHandler.handleT(br, tbnum, ltid, map);
                tbBlocks.add(tbBlock);
                tbnum++;
            }
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("Java解析结果用时： " + (endTime - startTime) + "ms");

            System.out.println("TB块存入数据库");
            startTime = System.currentTimeMillis();   //获取开始时间

            iTbBlockService.saveBatch(tbBlocks);
            ltlogInstructionMapService.saveBatch(map.values());
            //ltlogAnalysis同时创建
            LtlogAnalysis ltlogAnalysis = new LtlogAnalysis(ltid);
            ltLogAnalysisService.save(ltlogAnalysis);

            endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("TB块存入数据库用时： " + (endTime - startTime) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return head;
    }


}
