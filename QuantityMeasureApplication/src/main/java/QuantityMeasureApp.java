
public class QuantityMeasureApp {

    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2, Length.LengthUnit unit2) {
        Length f = new Length(value1,unit1);
        Length f1 = new Length(value2,unit2);
        return f.equals(f1);
    }

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
        Length f=new Length(value,fromUnit);
        return f.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        if (length == null) {
            throw new IllegalArgumentException("length must not be null");
        }
        if (toUnit == null) {
            throw new IllegalArgumentException("toUnit must not be null");
        }
        return length.convertTo(toUnit);
    }



    public static void main(String[] args) {

        System.out.println(
        demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));

        System.out.println(
                demonstrateLengthConversion(new Length(3.0, Length.LengthUnit.FEET), Length.LengthUnit.YARDS));

      /*  System.out.println(
                demonstrateLengthConversion(new Length(3.0, Length.LengthUnit.FEET), null));
*/
        //Demonstrate Feet and Inches Comparison
        System.out.println(demonstrateLengthComparison(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES));

        //Demonstrate Yards and Inches Comparison
        System.out.println(demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 36.0, Length.LengthUnit.INCHES));

        //Demonstrate Centimeters and Inches Comparison
        System.out.println(demonstrateLengthComparison(100.0, Length.LengthUnit.CENTIMETERS, 39.37010, Length.LengthUnit.INCHES));

        //Demonstrate Feet and Yards Comparison
        System.out.println(demonstrateLengthComparison(3.0, Length.LengthUnit.FEET, 1.0, Length.LengthUnit.YARDS));

        //Demonstrate Centimeters and Feet Comparison
        System.out.println(demonstrateLengthComparison(30.48, Length.LengthUnit.CENTIMETERS, 1.0, Length.LengthUnit.FEET));
    }
}
