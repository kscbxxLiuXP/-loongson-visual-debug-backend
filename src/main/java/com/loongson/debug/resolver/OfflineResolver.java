package com.loongson.debug.resolver;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.Head;
import com.loongson.debug.entity.TbBlock;
import com.loongson.debug.service.ITbBlockService;
import com.loongson.debug.util.FileOutputUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


public class OfflineResolver {

    private ITbBlockService iTbBlockService;
    private static OfflineResolver offlineResolver;
    private HeadHandler headHandler;
    private TBHandler tbHandler;

    private OfflineResolver() {
        headHandler = new HeadHandler();
        tbHandler = new TBHandler();

    }

    public static OfflineResolver getInstance() {
        if (offlineResolver == null) offlineResolver = new OfflineResolver();
        return offlineResolver;
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
        tbHandler.setiTbBlockService(iTbBlockService);
        HashMap<Integer, Object> resultMap = new HashMap<>();
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
                TbBlock tbBlock = tbHandler.handleT(br, tbnum, ltid);
                tbBlocks.add(tbBlock);
                tbnum++;
            }
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("Java解析结果用时： " + (endTime - startTime) + "ms");

            System.out.println("TB块存入数据库");
            startTime = System.currentTimeMillis();   //获取开始时间

//            BufferedWriter bufferedWriter = FileOutputUtil.getBufferedWriter("data/0414/tbBlocksAnalysis.txt");
//
//            tbBlocks.sort(new Comparator<TbBlock>() {
//                @Override
//                public int compare(TbBlock tbBlock, TbBlock t1) {
//                    return t1.getIr2num()-tbBlock.getIr2num();
//                }
//            });
//            for(int i=0;i<150;i++){
//                TbBlock block = tbBlocks.get(i);
//                bufferedWriter.write("["+block.getIr2num()+"]"+JSON.toJSONString(block.getIr2instr())+ "\n");
//            }
            iTbBlockService.saveBatch(tbBlocks);

            endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("TB块存入数据库用时： " + (endTime - startTime) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return head;
    }


}
