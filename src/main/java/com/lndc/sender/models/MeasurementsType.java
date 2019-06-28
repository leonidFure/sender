package com.lndc.sender.models;

public enum MeasurementsType {
    LEVEL, TEMPERATURE, CONDUCTION;

    @Override
    public String toString() {
        switch (this){
            case LEVEL: return "Level";
            case TEMPERATURE: return "Temperature";
            case CONDUCTION: return "Conductivity";
        }
        return "";
    }
}
