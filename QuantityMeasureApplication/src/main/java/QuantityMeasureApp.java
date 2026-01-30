import java.util.Objects;
import java.util.Scanner;

public class QuantityMeasureApp {

    public static void demonstrateFeetEquality(){
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(2.0, Length.LengthUnit.FEET);
        Length feet3 = new Length(1.0, Length.LengthUnit.FEET);
        System.out.println("Feet Both Object Value is :- "+(feet1.equals(feet2)));
        System.out.println("Feet Both Object Value is :- "+(feet1.equals(feet3)));
    }

    public static void demonstrateInchesEquality(){
        Length inches1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(24.0, Length.LengthUnit.INCHES);
        Length inches3 = new Length(12.0, Length.LengthUnit.INCHES);
        System.out.println("Inches Both Object Value is :- "+(inches1.equals(inches2)));
        System.out.println("Inches Both Object Value is :- "+(inches1.equals(inches3)));

        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(2.0, Length.LengthUnit.FEET);
        System.out.println("Both Feet and Inches Object Value is :- "+(inches1.equals(feet1)));
        System.out.println("Both Feet and Inches Object Value is :- "+(inches1.equals(feet2)));

    }


    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
    }
}
