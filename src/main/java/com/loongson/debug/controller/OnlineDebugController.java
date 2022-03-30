package com.loongson.debug.controller;

import com.loongson.debug.helper.DebugVar;
import com.loongson.debug.helper.GlobalDebugMaintainer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/onlineDebug")
public class OnlineDebugController {


    @GetMapping("/available")
    public List<DebugVar> getAvailable() {
        GlobalDebugMaintainer globalDebugMaintainer = GlobalDebugMaintainer.getInstance();
        Collection<DebugVar> values = globalDebugMaintainer.getAll().values();
        ArrayList<DebugVar> availables = new ArrayList<>(values);
        return availables;
    }
}
