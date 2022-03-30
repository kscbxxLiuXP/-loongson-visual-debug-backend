package com.loongson.debug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author liuxp
 * @since 2022-03-15
 */
public class LtLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private Integer userid;

    private String filename;

    private Integer headid;

    private String uploadtime;

    private Long size;

    private Integer line;

    private Boolean traced;

    private Integer traceid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getHeadid() {
        return headid;
    }

    public void setHeadid(Integer headid) {
        this.headid = headid;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Boolean getTraced() {
        return traced;
    }

    public void setTraced(Boolean traced) {
        this.traced = traced;
    }

    public Integer getTraceid() {
        return traceid;
    }

    public void setTraceid(Integer traceid) {
        this.traceid = traceid;
    }


    @Override
    public String toString() {
        return "LtLog{" +
                "uid=" + uid +
                ", userid=" + userid +
                ", filename='" + filename + '\'' +
                ", headid=" + headid +
                ", uploadtime='" + uploadtime + '\'' +
                ", size=" + size +
                ", line=" + line +
                ", traced=" + traced +
                ", traceid=" + traceid +
                '}';
    }
}
