package com.lndc.sender.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LoggerSamples {
    private int logerNumber = 1;
    private static final String DATE_FORMATTER= "dd/MM/yyyy HH:mm:ss";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    private Map<MeasurementsType, MeasurementsUnit> measurementsType;
    private List<LocalDateTime> dateTimes;
    private List<List<Double>> listOFMeasurements;

    public LoggerSamples(int logerNumber, Map<MeasurementsType, MeasurementsUnit> measurementsType, List<LocalDateTime> dateTimes, List<List<Double>> listOFMeasurements) {
        this.logerNumber = logerNumber;
        this.measurementsType = measurementsType;
        this.dateTimes = dateTimes;
        this.listOFMeasurements = listOFMeasurements;
    }

    public LoggerSamples() {
    }

    public int getLogerNumber() {
        return logerNumber;
    }

    public void setLogerNumber(int logerNumber) {
        this.logerNumber = logerNumber;
    }

    public Map<MeasurementsType, MeasurementsUnit> getMeasurementsType() {
        return measurementsType;
    }

    public void setMeasurementsType(Map<MeasurementsType, MeasurementsUnit> measurementsType) {
        this.measurementsType = measurementsType;
    }

    public List<List<Double>> getListOFMeasurements() {
        return listOFMeasurements;
    }

    public void setListOFMeasurements(List<List<Double>> listOFMeasurements) {
        this.listOFMeasurements = listOFMeasurements;
    }

    public List<LocalDateTime> getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(List<LocalDateTime> dateTimes) {
        this.dateTimes = dateTimes;
    }

    @Override
    public String toString() {
        final String[] result = {String.format("Logger %d Samples\nTime, ", logerNumber)};
        measurementsType.forEach((k,v) ->
                result[0] += k.toString() + v.toString()+", ");
        result[0] = result[0].substring(0, result[0].length() - 2);
        result[0] +="\n";
        for (int i = 0; i < dateTimes.size(); i++) {
            final String[] line = {""};
            line[0] += dateTimes.get(i).format(formatter)+", ";
            listOFMeasurements.get(i).forEach((measurement) ->{
                line[0] += String.format(Locale.US,"%.4f, ", measurement);
            });
            line[0] = line[0].substring(0, line[0].length() - 2);
            line[0] += "\n";
            result[0] += line[0];
        }
        return result[0];
    }
}
