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
    private boolean end;
    private String createTime;
    private String ip;
    /**
     * 1. 准备就绪
     * 2. 执行中
     * 3. 调试中
     * 4. 已结束
     * 5. 被终止
     * 6. 连接丢失
     */
    private int debugState;
    private long currentAddress;
    private long lastCommunicateTime;

    public DebugVar(int id, String ip) {
        this.id = id;
        this.breakPointAddress = 0;
        this.canExecute = false;
        this.debugState = 1;
        this.DEBUG = false;
        this.currentAddress = 0;
        this.canStart = false;
        this.end = false;
        this.ip = ip;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        createTime = simpleDateFormat.format(new Date());
    }

    public void communicate() {
        this.lastCommunicateTime = new Date().getTime();
    }

    public boolean connectLost() {
        Date date = new Date();
        long cha = date.getTime() - lastCommunicateTime;
        if (cha <= 300000) {
            return false; //说明小于5分钟
        } else {
            return true;
        }
    }

    public long getLastCommunicateTime() {
        return lastCommunicateTime;
    }

    public void setLastCommunicateTime(long lastCommunicateTime) {
        this.lastCommunicateTime = lastCommunicateTime;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
