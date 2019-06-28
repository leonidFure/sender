package com.lndc.sender.models;

import java.time.LocalDateTime;

public class Sender {
    private String location = "test", state = "reporting";
    private int serial = 111111, battery =75, sampleRate = 1, reportRate = 12;
    private LocalDateTime startReport = LocalDateTime.now().minusYears(20);

    public Sender(String location, String state, int serial, int battery, int sampleRate, int reportRate, LocalDateTime startReport) {
        this.location = location;
        this.state = state;
        this.serial = serial;
        this.battery = battery;
        this.sampleRate = sampleRate;
        this.reportRate = reportRate;
        this.startReport = startReport;
    }

    public Sender() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getReportRate() {
        return reportRate;
    }

    public void setReportRate(int reportRate) {
        this.reportRate = reportRate;
    }

    public LocalDateTime getStartReport() {
        return startReport;
    }

    public void setStartReport(LocalDateTime startReport) {
        this.startReport = startReport;
    }

    @Override
    public String toString() {
        return "LevelSender\n" +
                "Serial: " + serial + "\n" +
                "Location: " + location + "\n" +
                "Battery: " + battery + "%\n" +
                "Sample Rate: " + sampleRate + " minutes\n" +
                "Report Rate: " + reportRate + " hours\n" +
                "State: reporting \n" +
                "Start Report: 10/06/2000 07:00:00\n";
    }
}
