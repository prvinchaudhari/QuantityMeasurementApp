import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Length {

    private final double value;

    private final LengthUnit unit;

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFacotr() {
            return conversionFactor;
        }

        private double toBaseValue(double value) {
            double raw = value * conversionFactor; // compute in base unit (e.g., meters)
            return BigDecimal.valueOf(raw)
                    .setScale(2, RoundingMode.HALF_UP) // 2 decimal places
                    .doubleValue();

            //return value * conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("value must be a finite number");
        }
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return unit.toBaseValue(value);
    }

    public boolean compare(Length thatLength) {
        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
    }
    public double value() { return value; }

    public Length convertTo(LengthUnit targetUnit){

        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }
       /* if (this.unit == targetUnit) {
            // same unit → return this instance if your class is immutable,
            // or create a copy if you prefer: return new Length(this.value, this.unit);
            return this;
        }*/
        // Convert current value to baseUnit, then baseUnit to target unit
        double result = this.value * this.convertToBaseUnit();
        double targetValue = result / targetUnit.toBaseValue(value);
        return  new Length(targetValue,targetUnit);
    }

    @Override
    public String toString() {
        return ""+value;
    }

    @Override
    public boolean equals(Object obj) {
        //Reference
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Length length = (Length) obj;
        if (length.unit == null)
            return false;
        return this.compare(length);
    }

    public static void main(String[] args) {
        Length feet = new Length(3.0,LengthUnit.FEET);
        Length inches = feet.convertTo(LengthUnit.INCHES);
        Length yards = feet.convertTo(LengthUnit.YARDS);
        System.out.println(inches);
        System.out.println(yards);
    }
}
