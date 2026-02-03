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
            return value * conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return unit.toBaseValue(value);
    }

    public boolean compare(Length thatLength) {
        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
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
        Scanner scan = new Scanner(System.in);
        double first_Value,second_Value;
        String first_Unit,second_Unit;

        LengthUnit unit1,unit2;

        System.out.println("Please Enter first numeric value");
        if(!scan.hasNextDouble())
            throw new InputMismatchException("Please provide numeric value only.");
        first_Value=scan.nextDouble();
        System.out.println("Please Enter Unit");
        first_Unit=scan.next();
        unit1 = LengthUnit.valueOf(first_Unit);

        System.out.println("Please Enter Second numeric value");
        if(!scan.hasNextDouble())
            throw new InputMismatchException("Please provide numeric value only.");
        second_Value= scan.nextDouble();
        System.out.println("Please Enter Unit");
        second_Unit=scan.next();
        unit2 = LengthUnit.valueOf(second_Unit);

        Length length1 = new Length(first_Value, unit1);
        Length length2 = new Length(second_Value, unit2);

        System.out.println("Both Unit Object Value is :- "+(length1.equals(length2)));

    }
}
