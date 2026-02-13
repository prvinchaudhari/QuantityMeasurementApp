public enum LengthUnit {
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
    public double getConversionFactor(){
        return conversionFactor;
    }

   /* public double toInches(double value) {
        return value * conversionFactor;
    }

    public double fromInches(double inchesValue) {
        return inchesValue / conversionFactor;
    }*/

    public double convertToBaseUnit(double value) {
        double converted = value * this.getConversionFactor();
        return Math.round(converted * 100.0) / 100.0;   // rounding to 2 decimals
        //return Math.round(fromInches(value) * 100.0) / 100.0;
    }

    public double convertFromUnit(double baseValue) {
        double fromUnit = baseValue / this.getConversionFactor();
        return Math.round(fromUnit * 100.0) / 100.0;
       // return Math.round(fromInches(basevalue) * 100.0) / 100.0;   // rounding to 2 decimals
    }
}