package com.ef.filelog;

import java.time.LocalDateTime;

public class FileLogModel {
    //Date, IP, Request, Status, User Agent (pipe delimited, open the example filelog in text editor)
    private String ip;
    private String request;
    private String status;
    private String userAgent;
    private LocalDateTime dateLog;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LocalDateTime getDateLog() {
        return dateLog;
    }

    public void setDateLog(LocalDateTime dateLog) {
        this.dateLog = dateLog;
    }
}
