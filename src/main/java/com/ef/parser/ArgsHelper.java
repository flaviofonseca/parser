package com.ef.parser;

import com.ef.util.UtilDate;

import java.time.LocalDateTime;

public class ArgsHelper {

    private String filePath;
    private LocalDateTime startDate;
    private Integer threshold;
    private DurationType duration;

    public ArgsHelper(String args[]) {
        this.setFilePath(args[0].replaceAll("--accesslog=", ""));
        this.setStartDate(UtilDate.converteStringToLocalDateTime(args[1].replaceAll("--startDate=", ""), "yyyy-MM-dd.HH:mm:ss"));
        this.setDuration(DurationType.getDurationType(args[2].replaceAll("--duration=", "")));
        this.setThreshold(Integer.parseInt(args[3].replaceAll("--threshold=", "")));
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public DurationType getDuration() {
        return duration;
    }

    public void setDuration(DurationType duration) {
        this.duration = duration;
    }
}
