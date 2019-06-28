package com.lndc.sender.models;

import java.time.LocalDateTime;

public class Logger {
    private String location = "test", type = "Levelogger Edge, M30, 3.0040", totalLogs = "24901 of 40000", memoryMode= "slate", logType = "linear", state = "started";
    private int serial = 121212, battery = 94, logRate = 60 , number = 1;
    private LocalDateTime startLogger;

    public Logger(String location, String type, String totalLogs, String memoryMode, String logType, String state, int serial, int battery, int logRate, int number, LocalDateTime startLogger) {
        this.location = location;
        this.type = type;
        this.totalLogs = totalLogs;
        this.memoryMode = memoryMode;
        this.logType = logType;
        this.state = state;
        this.serial = serial;
        this.battery = battery;
        this.logRate = logRate;
        this.number = number;
        this.startLogger = startLogger;
    }

    public Logger() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotalLogs() {
        return totalLogs;
    }

    public void setTotalLogs(String totalLogs) {
        this.totalLogs = totalLogs;
    }

    public String getMemoryMode() {
        return memoryMode;
    }

    public void setMemoryMode(String memoryMode) {
        this.memoryMode = memoryMode;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getLogRate() {
        return logRate;
    }

    public void setLogRate(int logRate) {
        this.logRate = logRate;
    }

    public LocalDateTime getStartLogger() {
        return startLogger;
    }

    public void setStartLogger(LocalDateTime startLogger) {
        this.startLogger = startLogger;
    }

    @Override
    public String toString() {
        return  "Logger "+number+"\n" +
                "Location: "+location+"\n" +
                "Type: "+type+"\n" +
                "Serial: "+serial+"\n" +
                "Battery: "+battery+"%\n" +
                "Total Logs: 24901 of 40000\n" +
                "Log Rate: "+logRate+" seconds\n" +
                "Memory Mode: "+ memoryMode +"\n" +
                "Log Type: "+logType+"\n" +
                "State: "+state+"\n" +
                "Start Logger: 09/06/2019 12:00:00";
    }
}
