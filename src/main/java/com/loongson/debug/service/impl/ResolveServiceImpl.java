package com.loongson.debug.service.impl;

import com.loongson.debug.entity.Head;
import com.loongson.debug.service.ILtLogAnalysisService;
import com.loongson.debug.service.ILtlogInstructionMapService;
import com.loongson.debug.service.ITbBlockService;
import com.loongson.debug.service.ResolveService;
import com.loongson.debug.resolver.OfflineResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;

@Service
public class ResolveServiceImpl implements ResolveService {


    @Autowired
    ITbBlockService iTbBlockService;
    @Autowired
    ILtlogInstructionMapService ltlogInstructionMapService;
    @Autowired
    ILtLogAnalysisService ltLogAnalysisService;

    OfflineResolver offlineResolver = OfflineResolver.getInstance();

    //将文件解析成TB块并存储在数据库中
    @Override
    public Head resolve(File file, int ltid) {

        Head head = null;
        try {
            offlineResolver.setiTbBlockService(iTbBlockService);
            offlineResolver.setLtlogInstructionMapService(ltlogInstructionMapService);
            offlineResolver.setLtLogAnalysisService(ltLogAnalysisService);
            head = offlineResolver.resolve(file, 5, ltid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return head;
    }
}
