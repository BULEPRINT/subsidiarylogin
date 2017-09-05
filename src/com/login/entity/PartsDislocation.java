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

    private String studentno;
    private String examTaskTime;
    private String examTime;
    private String stduname;

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public String getExamTaskTime() {
        return examTaskTime;
    }

    public void setExamTaskTime(String examTaskTime) {
        this.examTaskTime = examTaskTime;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getStduname() {
        return stduname;
    }

    public void setStduname(String stduname) {
        this.stduname = stduname;
    }

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
