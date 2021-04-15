package com.dj.iotlite.events;

public class LogEvent {
    public LogEvent(String msg) {
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    String msg="";
}
