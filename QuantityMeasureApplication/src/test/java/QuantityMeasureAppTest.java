import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasureAppTest {

    private static final double EPS = 1e-6;
        private  Length feet,inches,yards,centimeter;

        @BeforeEach
        void setUp() {
            feet = new Length(1.0, Length.LengthUnit.FEET);
            inches = new Length(12.0, Length.LengthUnit.INCHES);
            yards = new Length(1.0, Length.LengthUnit.YARDS);
            centimeter = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        }

        @Test
        void testEquality_FeetToFeet_sameValue() {
            Length f1 = new Length(1.0, Length.LengthUnit.FEET);
            assertEquals(true,feet.equals(f1));
        }

    @Test
    void testEquality_FeetToFeet_differentValue() {
        Length f2 = new Length(2.0, Length.LengthUnit.FEET);
        assertEquals(false,feet.equals(f2));
    }

    @Test
    void testEquality_Feet_nullComparison() {
        Length f3 =null;
        assertEquals(false, feet.equals(f3));
       // assertEquals(false, Objects.equals(obj1,obj2));
    }

    @Test
    void testEquality_Feet_differentClass() {
        Object obj2 = new String();
        assertEquals(false,feet.equals(obj2));
    }

    @Test
    void testEquality_Feet_sameReference() {
            Length f4 = feet;
        assertEquals(true,feet.equals(f4));
    }

    @Test
    void testEquality_InchesToInches_sameValue() {
        Length i2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertEquals(true,inches.equals(i2));
    }

    @Test
    void testEquality_InchesToInches_differentValue() {
        Length i2 = new Length(24.0, Length.LengthUnit.INCHES);
        assertEquals(false,inches.equals(i2));
    }

    @Test
    void testEquality_Inches_nullComparison() {
        Length i3 = null;
        assertEquals(false, inches.equals(i3));
        // assertEquals(false, Objects.equals(obj1,obj2));
    }

    @Test
    void testEquality_Inches_differentClass() {
        Object obj2 = new String();
        assertEquals(false,inches.equals(obj2));
    }

    @Test
    void testEquality_Inches_sameReference() {
        Length i3 = inches;
        assertEquals(true,inches.equals(i3));
    }

    @Test
    void testEquality_FeetTOInches_Equality() {
        assertEquals(true,inches.equals(feet));
    }

    @Test
    void testEquality_FeetToInches_NotEquality() {
        Length feet = new Length(2.0, Length.LengthUnit.INCHES);
        assertEquals(false,inches.equals(feet));
    }

    @Test
    void testEquality_YardToYard_sameValue() {
        Length y1 = new Length(1.0, Length.LengthUnit.YARDS);
        assertEquals(true,yards.equals(y1));
    }

    @Test
    void testEquality_YardTOYard_differentValue() {
        Length y2 = new Length(5.0, Length.LengthUnit.YARDS);
        assertEquals(false,yards.equals(y2));
    }

    @Test
    void testEquality_YardTOFeet_EquivalentValue() {
         Length f = new Length(3,Length.LengthUnit.FEET);
        assertEquals(true,yards.equals(f));
    }

    @Test
    void testEquality_FeetTOYard_EquivalentValue() {
        Length f = new Length(3,Length.LengthUnit.FEET);
        assertEquals(true,f.equals(yards));
    }
    @Test
    void testEquality_YardTOInches_EquivalentValue() {
        Length i = new Length(36, Length.LengthUnit.INCHES);
        assertEquals(true,yards.equals(i));
    }
    @Test
    void testEquality_InchesTOYard_EquivalentValue() {
        Length i = new Length(36, Length.LengthUnit.INCHES);
        assertEquals(true,i.equals(yards));
    }

    @Test
    void testEquality_YardTOFeet_NotEquivalentValue() {
        assertEquals(false,yards.equals(feet));
    }

    @Test
    void testEquality_CentimetersTOInches_EquivalentValue() {
            Length centimeter = new Length(1, Length.LengthUnit.CENTIMETERS);
            Length inches =new Length(0.393701, Length.LengthUnit.INCHES);
        assertEquals(true,centimeter.equals(inches));
    }

    @Test
    void testEquality_CentimetersTOFeet_NotEquivalentValue() {
        Length centimeter = new Length(1, Length.LengthUnit.CENTIMETERS);
        Length feet =new Length(1, Length.LengthUnit.FEET);
        assertEquals(false,centimeter.equals(feet));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

    }

    @Test
    void testEquality_YardWithNullUnit() {
        Length feet =new Length(1, null);
        assertEquals(false,yards.equals(feet));

    }

    @Test
    void testEquality_YardSameReference() {
        Length y =yards;
        assertEquals(true,yards.equals(y));
    }

    @Test
    void testEquality_YardNullComparison() {
        Length y =null;
        assertEquals(false,yards.equals(y));
    }

    @Test
    void testEquality_CentimeterWithNullUnit() {
        //Length feet =new Length(1, null);
        Length feet1 =null;
        //  assertEquals(false,yards.equals(feet));
        assertEquals(false, Objects.equals(centimeter,feet1));
    }

    @Test
    void testEquality_CentimeterSameReference() {
        Length c =centimeter;
        assertEquals(true,centimeter.equals(c));
    }

    @Test
    void testEquality_CentimeterNullComparison() {
        Length c =null;
        assertEquals(false,centimeter.equals(c));
    }
    @Test
    void testEquality_AllUnit_ComplexScenario() {
            Length yards = new Length(2.0, Length.LengthUnit.YARDS);
            Length feet = new Length(6.0, Length.LengthUnit.FEET);
            Length inches = new Length(72.0, Length.LengthUnit.INCHES);
            assertAll(
                    () -> assertEquals(true,yards.equals(feet)),
                    () -> assertEquals(true,yards.equals(inches))
            );
    }

    @Test
    void testEquality_FeetTOInches() {
        Length f = new Length(1, Length.LengthUnit.FEET);
        Length i = QuantityMeasureApp.demonstrateLengthConversion(f, Length.LengthUnit.INCHES);
        assertEquals(12.0,i.value());
    }

    @Test
    void testEquality_InchesTOFeet() {
        Length i = new Length(24, Length.LengthUnit.INCHES);
        Length f = QuantityMeasureApp.demonstrateLengthConversion(i, Length.LengthUnit.FEET);
        assertEquals(2.0,f.value());
    }

    @Test
    void testEquality_YardsTOInches() {
        Length y = new Length(1.0, Length.LengthUnit.YARDS);
        Length i = QuantityMeasureApp.demonstrateLengthConversion(y, Length.LengthUnit.INCHES);
        assertEquals(36.0,i.value());
    }

    @Test
    void testEquality_CentimeterTOInches() {
        Length c = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length i = QuantityMeasureApp.demonstrateLengthConversion(c, Length.LengthUnit.INCHES);
        assertEquals(1.0,i.value());
    }

    @Test
    void testEquality_FeetTOYard() {
        Length f = new Length(6, Length.LengthUnit.FEET);
        Length y = QuantityMeasureApp.demonstrateLengthConversion(f, Length.LengthUnit.YARDS);
        assertEquals(2.0,y.value());
    }

    @Test
    void testEquality_NegativeValue() {
        Length f = new Length(-1.0, Length.LengthUnit.FEET);
        Length y = QuantityMeasureApp.demonstrateLengthConversion(f, Length.LengthUnit.INCHES);
        assertEquals(-12.0,y.value());
    }

    @Test
    void testEquality_NaNOrInfinite_Throws() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.NaN, Length.LengthUnit.FEET));
        assertTrue(ex.getMessage().toLowerCase().contains("finite"));
    }

}
