package com.lndc.sender.models;

public enum MeasurementsUnit {
    METER, CENTIMETER, FOOT, PSI, KPA, BAR, MILI_BAR, DEGREE_CELSIUS, DEGREE_FAHREHEIT, MICROSIEMENS_PER_CENTIMETER, MILLISIEMENS_PER_CENTIMETER, DEFAULT;

    @Override
    public String toString() {
        switch (this){
            case METER: return "(m)";
            case CENTIMETER: return "(cm)";
            case FOOT: return "(ft)";
            case PSI: return "(psi)";
            case KPA: return "(kPa)";
            case BAR: return "(bar)";
            case MILI_BAR: return "(mbar)";
            case DEGREE_CELSIUS: return "( C)";
            case DEGREE_FAHREHEIT: return "( F)";
            case MICROSIEMENS_PER_CENTIMETER: return "(uS/cm)";
            case MILLISIEMENS_PER_CENTIMETER: return "(mS/cm)";
        }
        return "";
    }

    public static MeasurementsUnit fromString(String measurement){
        switch (measurement){
            case "(m)": return METER;
            case "(cm)": return CENTIMETER;
            case "(ft)": return FOOT;
            case "(psi)": return PSI;
            case "(kPa)": return KPA;
            case "(bar)": return BAR;
            case "(mbar)": return MILI_BAR;
            case "( C)": return DEGREE_CELSIUS;
            case "( F)": return DEGREE_FAHREHEIT;
            case "(uS/cm)": return MICROSIEMENS_PER_CENTIMETER;
            case "(mS/cm)": return MILLISIEMENS_PER_CENTIMETER;
        }
        return DEFAULT;
    }
}
