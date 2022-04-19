package com.loongson.debug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liuxp
 * @since 2022-04-11
 */
public class OnlineDebug implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @TableField("breakPointAddress")
    private Long breakPointAddress;

    //程序能否开始调试
    @TableField("canStart")
    private Boolean canStart;

    @TableField("canExecute")
    private Boolean canExecute;

    @TableField("DEBUG")
    private Boolean debug;

    @TableField("isEnd")
    private Boolean isend;

    @TableField("createTime")
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
    @TableField("debugState")
    private Integer debugState;

    @TableField("currentAddress")
    private Long currentAddress;

    @TableField("lastCommunicateTime")
    private Long lastCommunicateTime;

    @TableField("previousTrace")
    //用于trace解析间接跳转,前一个trace的地址
    private String previousTrace;

    @TableField("skipExecute")
    private boolean skipExecute;


    public OnlineDebug() {
    }

    public OnlineDebug(String ip) {
        this.breakPointAddress = -1L;
        this.canExecute = false;
        this.debugState = 1;
        this.debug = false;
        this.currentAddress = -1L;
        this.canStart = false;
        this.isend = false;
        this.skipExecute = false;
        this.ip = ip;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTime = simpleDateFormat.format(new Date());
        this.previousTrace = "-1";
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

    public boolean getSkipExecute() {
        return skipExecute;
    }

    public void setSkipExecute(boolean skipExecute) {
        this.skipExecute = skipExecute;
    }

    public String getPreviousTrace() {
        return previousTrace;
    }

    public void setPreviousTrace(String previousTrace) {
        this.previousTrace = previousTrace;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getBreakpointaddress() {
        return breakPointAddress;
    }

    public void setBreakpointaddress(Long breakpointaddress) {
        this.breakPointAddress = breakpointaddress;
    }

    public Boolean getCanstart() {
        return canStart;
    }

    public void setCanstart(Boolean canstart) {
        this.canStart = canstart;
    }

    public Boolean getCanexecute() {
        return canExecute;
    }

    public void setCanexecute(Boolean canexecute) {
        this.canExecute = canexecute;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public Boolean getIsend() {
        return isend;
    }

    public void setIsend(Boolean isend) {
        this.isend = isend;
    }

    public String getCreatetime() {
        return createTime;
    }

    public void setCreatetime(String createtime) {
        this.createTime = createtime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getDebugstate() {
        return debugState;
    }

    public void setDebugstate(Integer debugstate) {
        this.debugState = debugstate;
    }

    public Long getCurrentaddress() {
        return currentAddress;
    }

    public void setCurrentaddress(Long currentaddress) {
        this.currentAddress = currentaddress;
    }

    public Long getLastcommunicatetime() {
        return lastCommunicateTime;
    }

    public void setLastcommunicatetime(Long lastcommunicatetime) {
        this.lastCommunicateTime = lastcommunicatetime;
    }

    @Override
    public String toString() {
        return "OnlineDebug{" +
                "uid=" + uid +
                ", breakpointaddress=" + breakPointAddress +
                ", canstart=" + canStart +
                ", canexecute=" + canExecute +
                ", debug=" + debug +
                ", isend=" + isend +
                ", createtime=" + createTime +
                ", ip=" + ip +
                ", debugstate=" + debugState +
                ", currentaddress=" + currentAddress +
                ", lastcommunicatetime=" + lastCommunicateTime +
                "}";
    }
}
