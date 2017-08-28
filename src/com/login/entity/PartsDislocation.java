package com.login.entity;

/**
 * describtion:.
 * author: zgj
 * date: 2017/8/28
 */
public class PartsDislocation {
    private String id;
    private String partname;
    private int dislocationCount;
    private String inserttime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public int getDislocationCount() {
        return dislocationCount;
    }

    public void setDislocationCount(int dislocationCount) {
        this.dislocationCount = dislocationCount;
    }

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }
}
