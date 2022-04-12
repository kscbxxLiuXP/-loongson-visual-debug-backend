package com.loongson.debug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.entity.OnlineDebug;
import com.loongson.debug.helper.GlobalDebugMaintainer;
import com.loongson.debug.service.IOnlineDebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/onlineDebug")
public class OnlineDebugController {
    @Autowired
    IOnlineDebugService onlineDebugService;

    //查询所有的数据
    @GetMapping("/all")
    public HashMap<String, Object> getAll(int currentPage, int limit) {
        IPage<OnlineDebug> onlineDebugIPage = onlineDebugService.selectByPage(currentPage, limit);
        List<OnlineDebug> records = onlineDebugIPage.getRecords();
        long pages = onlineDebugIPage.getPages();

        HashMap<String, Object> returnRes = new HashMap<>();

        returnRes.put("records", records);
        returnRes.put("total", onlineDebugIPage.getTotal());
        returnRes.put("pages", pages);
        return returnRes;
    }

    //查询近3天数据
    @GetMapping("/available")
    public List<OnlineDebug> getAvailable() {
        return onlineDebugService.available();
    }

    @GetMapping("/get")
    public OnlineDebug getByID(int id) {
        GlobalDebugMaintainer globalDebugMaintainer = GlobalDebugMaintainer.getInstance();
        return globalDebugMaintainer.get(id);
    }
}
