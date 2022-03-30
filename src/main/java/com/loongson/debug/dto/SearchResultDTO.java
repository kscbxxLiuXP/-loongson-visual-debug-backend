package com.loongson.debug.dto;

public class SearchResultDTO {
    /*
     * TB块的位置
     * IR1位置
     */
    private int id;
    private int tbid;
    private int type;//ir1 或 ir2
    private int irid;
    private String selectID;

    public SearchResultDTO(int id, int tbid, int type, int irid, String selectID) {
        this.id = id;
        this.tbid = tbid;
        this.type = type;
        this.irid = irid;
        this.selectID = selectID;
    }

    @Override
    public String toString() {
        return "SearchResultDTO{" +
                "id=" + id +
                ", tbid=" + tbid +
                ", type=" + type +
                ", irid=" + irid +
                ", selectID='" + selectID + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTbid() {
        return tbid;
    }

    public void setTbid(int tbid) {
        this.tbid = tbid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIrid() {
        return irid;
    }

    public void setIrid(int irid) {
        this.irid = irid;
    }

    public String getSelectID() {
        return selectID;
    }

    public void setSelectID(String selectID) {
        this.selectID = selectID;
    }
}
