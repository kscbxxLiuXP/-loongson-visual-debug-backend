package com.loongson.debug.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.loongson.debug.dto.TraceDTO;
import com.loongson.debug.entity.Head;
import com.loongson.debug.entity.LtLog;
import com.loongson.debug.entity.Trace;
import com.loongson.debug.entity.User;
import com.loongson.debug.resolver.TraceHandler;
import com.loongson.debug.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@RestController
@CrossOrigin
public class FileController {
    @Autowired
    ITraceService traceService;

    @Autowired
    IHeadService headService;

    @Autowired
    ILtLogService ltLogService;

    @Autowired
    IUserService userService;

    @Autowired
    ITbBlockService tbBlockService;

    @Autowired
    ResolveService resolveService;
    //文件存放地址
    private static final String basicFilePath = "/home/liuxp/文档/毕设/后端/demo/upload";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/uploadTrace")
    public TraceDTO uploadTrace(MultipartFile file, HttpServletRequest req, @RequestParam int ltid) throws IOException {
        //保存文件
        File folder = new File(basicFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        UUID uuid = UUID.randomUUID();
        String newName = uuid + ".trace";
        File traceFile = new File(folder, newName);
        file.transferTo(traceFile);

        //解析Trace
        TraceHandler traceHandler = new TraceHandler();
        traceHandler.init(tbBlockService);
        TraceDTO traceDTO = traceHandler.handle(traceFile, ltid);

        //保存Trace
        Trace trace = new Trace(traceDTO);
        traceService.save(trace);
        traceDTO.setUid(trace.getUid());
        //更新Ltlog
        UpdateWrapper<LtLog> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid", ltid).set("traced", true).set("traceid", trace.getUid());
        ltLogService.update(null, updateWrapper);

        traceFile.delete();
        return traceDTO;
    }

    @PostMapping("/upload")
    public LtLog uploadLog(MultipartFile file, HttpServletRequest req, @RequestParam String username) throws IOException {
        //测试在哪一步消耗的时间比较长

        //先在数据库中建立记录，然后返回数据库中的id，重命名，保存
        //然后启动解析服务进行对象初始化
        User user = userService.getByUsername(username);

        System.out.println("开始创建ltlog");
        long startTime = System.currentTimeMillis();   //获取开始时间

        String time = sdf.format(new Date());
        String filename = file.getOriginalFilename();

        LtLog ltLog = new LtLog();
        ltLog.setUploadtime(time);
        ltLog.setUserid(user.getUid());
        ltLog.setFilename(filename);
        ltLogService.createNewLog(ltLog);

        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("创建ltlog用时： " + (endTime - startTime) + "ms");

        System.out.println("另存上传文件");
        startTime = System.currentTimeMillis();
        //保存文件
        File folder = new File(basicFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String newName = ltLog.getUid() + ".log";
        File logFile = new File(folder, newName);
        file.transferTo(logFile);

        endTime = System.currentTimeMillis();
        System.out.println("复制保存文件用时： " + (endTime - startTime) + "ms");

        int totalLines = getTotalLines(logFile);
        ltLog.setLine(totalLines);
        ltLog.setSize(logFile.length());


        Head head = resolveService.resolve(logFile, ltLog.getUid());

        logFile.delete();

        head.setLtid(ltLog.getUid());
        headService.addHead(head);


        ltLog.setHeadid(head.getUid());
        int i = ltLogService.updateLog(ltLog);

        return ltLog;
    }

    public int getTotalLines(File file) throws IOException {
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        reader.skip(Long.MAX_VALUE);
        int lines = reader.getLineNumber();
        reader.close();
        return lines;
    }
}
