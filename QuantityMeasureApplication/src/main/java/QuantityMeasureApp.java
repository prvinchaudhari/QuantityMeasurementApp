
public class QuantityMeasureApp {

    public static boolean demonstrateWeightEquality(Weight weight1, Weight weight2) {
        return weight1 != null && weight1.equals(weight2);
    }

    public static boolean demonstrateWeightComparison(double value1, WeightUnit unit1,double value2, WeightUnit unit2) {
        Weight weight1=new Weight(value1,unit1);
        Weight weight2=new Weight(2,unit2);
        return weight1.equals(weight2);
    }

    public static Weight demonstrateWeightConversion(double value,WeightUnit fromUnit, WeightUnit toUnit) {
        Weight weight=new Weight(value,fromUnit);
        return weight.convertTo(toUnit);
    }

    public static Weight demonstrateWeightConversion(Weight weight, WeightUnit toUnit) {
        if (weight == null || toUnit == null) {
            throw new IllegalArgumentException("weight and target unit are required");
        }
        return weight.convertTo(toUnit);
    }
    /**
     * Demonstrate addition of second Weight to first Weight.
     */
    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2) {
        if (weight1 == null || weight2 == null) {
            throw new IllegalArgumentException("Both weight are required");
        }
        return weight1.add(weight2); // result in unit of first operand
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2, WeightUnit targetUnit) {
        if (weight1 == null || weight2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Both weight are required");
        }
        return weight1.add(weight2,targetUnit);
    }
    /* Length Measurement Application Method.*/
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1 != null && length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1,double value2, LengthUnit unit2) {
        Length length1 = new Length(value1,unit1);
        Length length2 = new Length(value2,unit2);
        return length1.equals(length2);
    }

    public static Length demonstrateLengthConversion(double value,LengthUnit fromUnit, LengthUnit toUnit) {
        Length length=new Length(value,fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
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

    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit) {
        if (length1 == null || length2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Both lengths are required");
        }
        return length1.add(length2,targetUnit);
    }

    public static void main(String[] args) {
        System.out.println(demonstrateLengthAddition(
                new Length(1.0,LengthUnit.FEET),
                new Length(2.0,LengthUnit.FEET)
        )); // Quantity(3.0, FEET)

        System.out.println(demonstrateLengthAddition(
                new Length(1.0,LengthUnit.FEET),
                new Length(12.0,LengthUnit.INCHES)
        )); // Quantity(2.0, FEET)

        System.out.println(demonstrateLengthAddition(
                new Length(12.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.FEET)
        )); // Quantity(24.0, INCHES)

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET)
        )); // Quantity(2.0, YARDS)

        System.out.println(demonstrateLengthAddition(
                new Length(36.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.YARDS)
        )); // Quantity(72.0, INCHES)

        System.out.println(demonstrateLengthAddition(
                new Length(2.54, LengthUnit.CENTIMETERS),
                new Length(1.0, LengthUnit.INCHES)
        )); // Quantity(~5.08, CENTIMETERS)

        System.out.println(demonstrateLengthAddition(
                new Length(5.0, LengthUnit.FEET),
                new Length(0.0, LengthUnit.INCHES)
        )); // Quantity(5.0, FEET)

        System.out.println(demonstrateLengthAddition(
                new Length(5.0, LengthUnit.FEET),
                new Length(-2.0,LengthUnit.FEET)
        )); // Quantity(3.0, FEET)

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS
        )); //// Quantity(2.0, FEET)
    }
}
