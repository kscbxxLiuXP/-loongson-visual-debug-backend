package com.loongson.debug.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DebugVar {
    private int id;
    private long breakPointAddress;
    //程序能否开始调试
    private boolean canStart;
    private boolean canExecute;
    private boolean DEBUG;
    private String createTime;
    /**
     * 1. 不在运行中
     * </br>
     * 2. 运行中但是没到断点
     * 3. 到断点，正在
     * 4. 执行下一条信息
     * 5.
     */
    private int debugState;
    private long currentAddress;

    public DebugVar(int id) {
        this.id = id;
        this.breakPointAddress = 0;
        this.canExecute = false;
        this.debugState = 0;
        this.DEBUG = false;
        this.currentAddress = 0;
        this.canStart = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        createTime = simpleDateFormat.format(new Date());
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isCanStart() {
        return canStart;
    }

    public void setCanStart(boolean canStart) {
        this.canStart = canStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(long currentAddress) {
        this.currentAddress = currentAddress;
    }

    public long getBreakPointAddress() {
        return breakPointAddress;
    }

    public void setBreakPointAddress(long breakPointAddress) {
        this.breakPointAddress = breakPointAddress;
    }

    public boolean isCanExecute() {
        return canExecute;
    }

    public void setCanExecute(boolean canExecute) {
        this.canExecute = canExecute;
    }

    public boolean isDEBUG() {
        return DEBUG;
    }

    public void setDEBUG(boolean DEBUG) {
        this.DEBUG = DEBUG;
    }

    public int getDebugState() {
        return debugState;
    }

    public void setDebugState(int debugState) {
        this.debugState = debugState;
    }
}
