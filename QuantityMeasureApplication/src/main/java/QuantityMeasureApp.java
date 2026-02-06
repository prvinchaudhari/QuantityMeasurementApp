
public class QuantityMeasureApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1 != null && length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1,double value2, Length.LengthUnit unit2) {
        Length length1 = new Length(value1,unit1);
        Length length2 = new Length(value2,unit2);
        return length1.equals(length2);
    }

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
        Length length=new Length(value,fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        if (length == null || toUnit == null) {
            throw new IllegalArgumentException("length and target unit are required");
        }
        return length.convertTo(toUnit);
    }
    /**
     * Demonstrate addition of second QuantityLength to first QuantityLength.
     */
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        if (length1 == null || length2 == null) {
            throw new IllegalArgumentException("Both lengths are required");
        }
        return length1.add(length2); // result in unit of first operand
    }

    public static void main(String[] args) {
        System.out.println(demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(2.0, Length.LengthUnit.FEET)
        )); // Quantity(3.0, FEET)

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES)
        )); // Quantity(2.0, FEET)

        System.out.println(demonstrateLengthAddition(
                new Length(12.0, Length.LengthUnit.INCHES),
                new Length(1.0, Length.LengthUnit.FEET)
        )); // Quantity(24.0, INCHES)

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.YARDS),
                new Length(3.0, Length.LengthUnit.FEET)
        )); // Quantity(2.0, YARDS)

        System.out.println(demonstrateLengthAddition(
                new Length(36.0, Length.LengthUnit.INCHES),
                new Length(1.0, Length.LengthUnit.YARDS)
        )); // Quantity(72.0, INCHES)

        System.out.println(demonstrateLengthAddition(
                new Length(2.54, Length.LengthUnit.CENTIMETERS),
                new Length(1.0, Length.LengthUnit.INCHES)
        )); // Quantity(~5.08, CENTIMETERS)

        System.out.println(demonstrateLengthAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(0.0, Length.LengthUnit.INCHES)
        )); // Quantity(5.0, FEET)

        System.out.println(demonstrateLengthAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(-2.0, Length.LengthUnit.FEET)
        )); // Quantity(3.0, FEET)
    }

}
