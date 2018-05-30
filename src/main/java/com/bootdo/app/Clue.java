package com.bootdo.app;

public class Clue {
    // 拜访时间
    private String visitTime;
    // 拜访详情
    private String visitDetail;
    // 拜访题目
    private String visitTitle;

    public String getVisitTitle() {
        return visitTitle;
    }

    public void setVisitTitle(String visitTitle) {
        this.visitTitle = visitTitle;
    }

    public String getVisitDetail() {

        return visitDetail;
    }

    public void setVisitDetail(String visitDetail) {
        this.visitDetail = visitDetail;
    }

    public String getVisitTime() {

        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
