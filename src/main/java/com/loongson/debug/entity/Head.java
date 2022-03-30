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
 * @since 2022-02-28
 */
public class Head implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private Integer ltid;

    private String headtext;


    public Integer getLtid() {
        return ltid;
    }

    public void setLtid(Integer ltid) {
        this.ltid = ltid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getHeadtext() {
        return headtext;
    }

    public void setHeadtext(String headtext) {
        this.headtext = headtext;
    }

    @Override
    public String toString() {
        return "Head{" +
                "uid=" + uid +
                ", ltid=" + ltid +
                ", headtext='" + headtext + '\'' +
                '}';
    }
}
