package com.lndc.sender.utils;

import com.lndc.sender.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageMaker {
    private String eMail;
    private int rate, number;
    private LocalDateTime intervalFrom, intervalTo;
    private boolean hasBarologger, hasLevelLoggerConduction, hasBaroLoggerConduction;
    private MeasurementsUnit lvlTempUnit, lvlLevelUnit, lvlCondUnit, baroTempUnit, baroLevelUnit, baroCondUnit;
    private double lvlTempFrom, lvlTempTo, lvlLevelFrom, lvlLevelTo, lvlCondFrom, lvlCondTo, baroTempFrom, baroTempTo, baroLevelFrom, baroLevelTo, baroCondFrom, baroCondTo;

    public MessageMaker(String eMail, int rate,
                        LocalDateTime intervalFrom, LocalDateTime intervalTo,
                        boolean hasBarologger, boolean hasLevelLoggerConduction, boolean hasBaroLoggerConduction,
                        MeasurementsUnit lvlTempUnit, MeasurementsUnit lvlLevelUnit, MeasurementsUnit lvlCondUnit,
                        MeasurementsUnit baroTempUnit, MeasurementsUnit baroLevelUnit, MeasurementsUnit baroCondUnit,
                        double lvlTempFrom, double lvlTempTo,
                        double lvlLevelFrom, double lvlLevelTo,
                        double lvlCondFrom, double lvlCondTo,
                        double baroTempFrom, double baroTempTo,
                        double baroLevelFrom, double baroLevelTo,
                        double baroCondFrom, double baroCondTo) {
        this.eMail = eMail;
        this.rate = rate;
        this.intervalFrom = intervalFrom;
        this.intervalTo = intervalTo;
        this.hasBarologger = hasBarologger;
        this.hasLevelLoggerConduction = hasLevelLoggerConduction;
        this.hasBaroLoggerConduction = hasBaroLoggerConduction;
        this.lvlTempUnit = lvlTempUnit;
        this.lvlLevelUnit = lvlLevelUnit;
        this.lvlCondUnit = lvlCondUnit;
        this.baroTempUnit = baroTempUnit;
        this.baroLevelUnit = baroLevelUnit;
        this.baroCondUnit = baroCondUnit;
        this.lvlTempFrom = lvlTempFrom;
        this.lvlTempTo = lvlTempTo;
        this.lvlLevelFrom = lvlLevelFrom;
        this.lvlLevelTo = lvlLevelTo;
        this.lvlCondFrom = lvlCondFrom;
        this.lvlCondTo = lvlCondTo;
        this.baroTempFrom = baroTempFrom;
        this.baroTempTo = baroTempTo;
        this.baroLevelFrom = baroLevelFrom;
        this.baroLevelTo = baroLevelTo;
        this.baroCondFrom = baroCondFrom;
        this.baroCondTo = baroCondTo;
    }

    public MessageMaker() {
    }

    public String createMessage(){
        Logger level = new Logger();
        level.setNumber(1);
        Logger baro = new Logger();
        baro.setNumber(2);
        baro.setSerial(222222);
        baro.setType("Barologger Edge, M1.5, 3.0040");
        Sender sender = new Sender();
        sender.setSerial(number);


        List<List<Double>> levelMeasurements = new ArrayList<>();
        LoggerSamples levelLoggerSamples = new LoggerSamples();
        List<LocalDateTime> dateTimes = new ArrayList<>();

        List<List<Double>> baroMeasurements = new ArrayList<>();
        LoggerSamples baroLoggerSamples = new LoggerSamples();

        LocalDateTime curDateTime = intervalFrom;
        while (curDateTime.isBefore(intervalTo) || curDateTime.isEqual(intervalTo)){
            dateTimes.add(curDateTime);
            curDateTime = curDateTime.plusSeconds(rate);
        }
        for(LocalDateTime i: dateTimes){
            List<Double> curMeasurements = new ArrayList<>();
            List<Double> curMeasurementsBaro = new ArrayList<>();
            setValuesForLogger(levelMeasurements, curMeasurements, hasLevelLoggerConduction);
            if(hasBarologger){
                setValuesForLogger(baroMeasurements, curMeasurementsBaro, hasBaroLoggerConduction);
            }
        }
        setSamples(dateTimes, levelMeasurements, levelLoggerSamples, lvlTempUnit, lvlLevelUnit, hasLevelLoggerConduction, lvlCondUnit, 1);
        if(hasBarologger)
            setSamples(dateTimes, baroMeasurements, baroLoggerSamples, baroTempUnit, baroLevelUnit, hasBaroLoggerConduction, baroCondUnit, 2);

        if(hasBarologger){
            return sender.toString() + "\n" + level.toString() + "\n\n" + baro.toString() + "\n\n\n" + levelLoggerSamples.toString() + "\n" +baroLoggerSamples.toString() + "\n" + "MESSAGES: Email report 33, LS reporting, L1 started, L2 started, ";
        }
        else{
            return sender.toString() + "\n" + level.toString() + "\n\n" + "Logger 2\n" + "NA\n\n" + "\n" + levelLoggerSamples.toString() + "\n" + "MESSAGES: Email report 33, LS reporting, L1 started, L2 off, ";
        }
    }

    private void setSamples(List<LocalDateTime> dateTimes, List<List<Double>> baroMeasurements, LoggerSamples baroLoggerSamples, MeasurementsUnit baroTempUnit, MeasurementsUnit baroLevelUnit, boolean hasBaroLoggerConduction, MeasurementsUnit baroCondUnit, int i2) {
        baroLoggerSamples.setDateTimes(dateTimes);
        baroLoggerSamples.setListOFMeasurements(baroMeasurements);
        Map<MeasurementsType, MeasurementsUnit> baroTypeUnits = new HashMap<>();
        baroTypeUnits.put(MeasurementsType.TEMPERATURE, baroTempUnit);
        baroTypeUnits.put(MeasurementsType.LEVEL, baroLevelUnit);
        if(hasBaroLoggerConduction)
            baroTypeUnits.put(MeasurementsType.CONDUCTION, baroCondUnit);
        baroLoggerSamples.setLogerNumber(i2);
        baroLoggerSamples.setMeasurementsType(baroTypeUnits);
        baroLoggerSamples.setListOFMeasurements(baroMeasurements);
        baroLoggerSamples.setDateTimes(dateTimes);
    }

    private void setValuesForLogger(List<List<Double>> baroMeasurements, List<Double> curMeasurementsBaro, boolean hasBaroLoggerConduction) {
        curMeasurementsBaro.add((Math.random() * ((lvlLevelTo - lvlLevelFrom) + 1)) + lvlLevelFrom);
        curMeasurementsBaro.add((Math.random() * ((lvlTempTo - lvlTempFrom) + 1)) + lvlTempFrom);
        if(hasBaroLoggerConduction){
            curMeasurementsBaro.add((Math.random() * ((lvlCondTo - lvlCondFrom) + 1)) + lvlCondFrom);
        }
        baroMeasurements.add(curMeasurementsBaro);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public LocalDateTime getIntervalFrom() {
        return intervalFrom;
    }

    public void setIntervalFrom(LocalDateTime intervalFrom) {
        this.intervalFrom = intervalFrom;
    }

    public LocalDateTime getIntervalTo() {
        return intervalTo;
    }

    public void setIntervalTo(LocalDateTime intervalTo) {
        this.intervalTo = intervalTo;
    }

    public boolean isHasBarologger() {
        return hasBarologger;
    }

    public void setHasBarologger(boolean hasBarologger) {
        this.hasBarologger = hasBarologger;
    }

    public boolean isHasLevelLoggerConduction() {
        return hasLevelLoggerConduction;
    }

    public void setHasLevelLoggerConduction(boolean hasLevelLoggerConduction) {
        this.hasLevelLoggerConduction = hasLevelLoggerConduction;
    }

    public boolean isHasBaroLoggerConduction() {
        return hasBaroLoggerConduction;
    }

    public void setHasBaroLoggerConduction(boolean hasBaroLoggerConduction) {
        this.hasBaroLoggerConduction = hasBaroLoggerConduction;
    }

    public MeasurementsUnit getLvlTempUnit() {
        return lvlTempUnit;
    }

    public void setLvlTempUnit(MeasurementsUnit lvlTempUnit) {
        this.lvlTempUnit = lvlTempUnit;
    }

    public MeasurementsUnit getLvlLevelUnit() {
        return lvlLevelUnit;
    }

    public void setLvlLevelUnit(MeasurementsUnit lvlLevelUnit) {
        this.lvlLevelUnit = lvlLevelUnit;
    }

    public MeasurementsUnit getLvlCondUnit() {
        return lvlCondUnit;
    }

    public void setLvlCondUnit(MeasurementsUnit lvlCondUnit) {
        this.lvlCondUnit = lvlCondUnit;
    }

    public MeasurementsUnit getBaroTempUnit() {
        return baroTempUnit;
    }

    public void setBaroTempUnit(MeasurementsUnit baroTempUnit) {
        this.baroTempUnit = baroTempUnit;
    }

    public MeasurementsUnit getBaroLevelUnit() {
        return baroLevelUnit;
    }

    public void setBaroLevelUnit(MeasurementsUnit baroLevelUnit) {
        this.baroLevelUnit = baroLevelUnit;
    }

    public MeasurementsUnit getBaroCondUnit() {
        return baroCondUnit;
    }

    public void setBaroCondUnit(MeasurementsUnit baroCondUnit) {
        this.baroCondUnit = baroCondUnit;
    }

    public double getLvlTempFrom() {
        return lvlTempFrom;
    }

    public void setLvlTempFrom(double lvlTempFrom) {
        this.lvlTempFrom = lvlTempFrom;
    }

    public double getLvlTempTo() {
        return lvlTempTo;
    }

    public void setLvlTempTo(double lvlTempTo) {
        this.lvlTempTo = lvlTempTo;
    }

    public double getLvlLevelFrom() {
        return lvlLevelFrom;
    }

    public void setLvlLevelFrom(double lvlLevelFrom) {
        this.lvlLevelFrom = lvlLevelFrom;
    }

    public double getLvlLevelTo() {
        return lvlLevelTo;
    }

    public void setLvlLevelTo(double lvlLevelTo) {
        this.lvlLevelTo = lvlLevelTo;
    }

    public double getLvlCondFrom() {
        return lvlCondFrom;
    }

    public void setLvlCondFrom(double lvlCondFrom) {
        this.lvlCondFrom = lvlCondFrom;
    }

    public double getLvlCondTo() {
        return lvlCondTo;
    }

    public void setLvlCondTo(double lvlCondTo) {
        this.lvlCondTo = lvlCondTo;
    }

    public double getBaroTempFrom() {
        return baroTempFrom;
    }

    public void setBaroTempFrom(double baroTempFrom) {
        this.baroTempFrom = baroTempFrom;
    }

    public double getBaroTempTo() {
        return baroTempTo;
    }

    public void setBaroTempTo(double baroTempTo) {
        this.baroTempTo = baroTempTo;
    }

    public double getBaroLevelFrom() {
        return baroLevelFrom;
    }

    public void setBaroLevelFrom(double baroLevelFrom) {
        this.baroLevelFrom = baroLevelFrom;
    }

    public double getBaroLevelTo() {
        return baroLevelTo;
    }

    public void setBaroLevelTo(double baroLevelTo) {
        this.baroLevelTo = baroLevelTo;
    }

    public double getBaroCondFrom() {
        return baroCondFrom;
    }

    public void setBaroCondFrom(double baroCondFrom) {
        this.baroCondFrom = baroCondFrom;
    }

    public double getBaroCondTo() {
        return baroCondTo;
    }

    public void setBaroCondTo(double baroCondTo) {
        this.baroCondTo = baroCondTo;
    }
}
