public enum WeightUnit {
    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592),
    TONNE(1000000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value){
        double converted = value * this.getConversionFactor();
        return Math.round(converted * 100.0) / 100.0;   // rounding to 2 decimals
        //return Math.round(fromInches(value) * 100.0) / 100.0;
    }

    public double convertFromBaseUnit(double baseValue){
        double fromUnit = baseValue / this.getConversionFactor();
        return Math.round(fromUnit * 100.0) / 100.0;
        // return Math.round(fromInches(basevalue) * 100.0) / 100.0;   // rounding to 2 decimals
    }
}
