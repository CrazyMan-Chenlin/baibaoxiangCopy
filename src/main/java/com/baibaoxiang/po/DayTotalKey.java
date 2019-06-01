package com.baibaoxiang.po;

import java.sql.Date;

public class DayTotalKey {
    private String no;

    private Date time;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}