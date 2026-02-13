import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.FileAlreadyExistsException;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasureAppTest {

    private Length feet, inches, yards, centimeter;

    @BeforeEach
    void setUp() {
        feet = new Length(1.0, LengthUnit.FEET);
        inches = new Length(12.0, LengthUnit.INCHES);
        yards = new Length(1.0, LengthUnit.YARDS);
        centimeter = new Length(1.0, LengthUnit.CENTIMETERS);
    }

    // ---------------- UC-1 Test Cases ----------------

    @Test
    void feet_equals_feet_sameValue() {
        Length f1 = new Length(1.0, LengthUnit.FEET);
        assertTrue(feet.equals(f1));
    }

    @Test
    void feet_notEquals_feet_differentValue() {
        Length f2 = new Length(2.0, LengthUnit.FEET);
        assertFalse(feet.equals(f2));
    }

    @Test
    void inches_equals_inches_sameValue() {
        Length i2 = new Length(12.0, LengthUnit.INCHES);
        assertTrue(inches.equals(i2));
    }

    @Test
    void inches_notEquals_inches_differentValue() {
        Length i2 = new Length(24.0, LengthUnit.INCHES);
        assertFalse(inches.equals(i2));
    }

    @Test
    void equals_null_returnsFalse_Feet() {
        assertFalse(feet.equals(null));
    }

    @Test
    void equals_null_returnsFalse_Inches() {
        assertFalse(inches.equals(null));
    }

    @Test
    void equals_differentClass_returnsFalse_Feet() {
        assertFalse(feet.equals("not a length"));
    }

    @Test
    void equals_differentClass_returnsFalse_Inches() {
        assertFalse(inches.equals("not a length"));
    }

    @Test
    void equals_sameReference_returnsTrue_Feet() {
        Length ref = feet;
        assertTrue(feet.equals(ref));
    }

    @Test
    void equals_sameReference_returnsTrue_Inches() {
        Length ref = inches;
        assertTrue(inches.equals(ref));
    }

    // ---------------- UC-3 Test Cases ----------------

    @Test
    void feet_equals_inches_equivalentMagnitude() {
        assertTrue(inches.equals(feet)); // 12 in == 1 ft
    }

    @Test
    void inches_notEquals_feet_nonEquivalent() {
        Length twoInches = new Length(2.0, LengthUnit.INCHES);
        assertFalse(inches.equals(twoInches)); // 12 in != 2 in
    }

    @Test
    void yard_equals_yard_sameValue() {
        assertTrue(yards.equals(new Length(1.0, LengthUnit.YARDS)));
    }

    @Test
    void yard_notEquals_yard_differentValue() {
        assertFalse(yards.equals(new Length(5.0, LengthUnit.YARDS)));
    }

    @Test
    void yard_equals_feet_equivalent() {
        assertTrue(yards.equals(new Length(3.0, LengthUnit.FEET)));
    }

    @Test
    void feet_equals_yard_equivalent() {
        assertTrue(new Length(3.0, LengthUnit.FEET).equals(yards));
    }

    @Test
    void yard_equals_inches_equivalent() {
        assertTrue(yards.equals(new Length(36.0, LengthUnit.INCHES)));
    }

    @Test
    void inches_equals_yard_equivalent() {
        assertTrue(new Length(36.0, LengthUnit.INCHES).equals(yards));
    }

    @Test
    void yard_notEquals_feet_nonEquivalent() {
        assertFalse(yards.equals(feet)); // 1 yd != 1 ft
    }

    @Test
    void centimeter_equals_inches_equivalent() {
        Length oneCm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length inchesForOneCm = new Length(0.393701, LengthUnit.INCHES);
        assertTrue(oneCm.equals(inchesForOneCm));
    }

    @Test
    void centimeter_notEquals_feet_nonEquivalent() {
        assertFalse(new Length(1.0, LengthUnit.CENTIMETERS)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    void equality_transitive_and_symmetric() {
        Length a = new Length(1.0, LengthUnit.YARDS);    // 36 inches
        Length b = new Length(3.0, LengthUnit.FEET);     // 36 inches
        Length c = new Length(36.0, LengthUnit.INCHES);  // 36 inches

        // symmetric
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));

        // transitive
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    // ---------------- Constructor validation ----------------

    @Test
    void constructor_nullUnit_throws() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
        assertTrue(ex.getMessage().toLowerCase().contains("unit"));
    }

    @Test
    void constructor_nanOrInfinite_throws() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.NaN, LengthUnit.FEET));
        assertTrue(ex1.getMessage().toLowerCase().contains("finite"));

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.POSITIVE_INFINITY, LengthUnit.FEET));
        assertTrue(ex2.getMessage().toLowerCase().contains("finite"));
    }

    // ---------------- Conversion API demos (using your app helper) ----------------

    @Test
    void convert_feet_to_inches() {
        Length f = new Length(1.0, LengthUnit.FEET);
        Length i = QuantityMeasureApp.demonstrateLengthConversion(f, LengthUnit.INCHES);
        assertEquals(12.0, i.getValue());
    }

    @Test
    void convert_inches_to_feet() {
        Length i = new Length(24.0, LengthUnit.INCHES);
        Length f = QuantityMeasureApp.demonstrateLengthConversion(i, LengthUnit.FEET);
        assertEquals(2.0, f.getValue());
    }

    @Test
    void convert_yards_to_inches() {
        Length y = new Length(1.0, LengthUnit.YARDS);
        Length i = QuantityMeasureApp.demonstrateLengthConversion(y, LengthUnit.INCHES);
        assertEquals(36.0, i.getValue());
    }

    @Test
    void convert_centimeters_to_inches() {
        Length c = new Length(2.54, LengthUnit.CENTIMETERS);
        Length i = QuantityMeasureApp.demonstrateLengthConversion(c, LengthUnit.INCHES);
        assertEquals(1.0, i.getValue());
    }

    @Test
    void convert_feet_to_yards() {
        Length f = new Length(6.0, LengthUnit.FEET);
        Length y = QuantityMeasureApp.demonstrateLengthConversion(f, LengthUnit.YARDS);
        assertEquals(2.0, y.getValue());
    }

    @Test
    void convert_negative_value_is_supported() {
        Length f = new Length(-1.0, LengthUnit.FEET);
        Length i = QuantityMeasureApp.demonstrateLengthConversion(f, LengthUnit.INCHES);
        assertEquals(-12.0, i.getValue());
    }

    // ---------------- UC6: Addition scenarios ----------------

    @Test
    void add_feet_plus_feet() {
        Length r = new Length(1.0, LengthUnit.FEET)
                .add(new Length(2.0, LengthUnit.FEET));
        assertEquals(new Length(3.0, LengthUnit.FEET), r);
    }

    @Test
    void add_inches_plus_inches() {
        Length r = new Length(12.0, LengthUnit.INCHES)
                .add(new Length(12.0, LengthUnit.INCHES));
        assertEquals(new Length(24.0, LengthUnit.INCHES), r);
    }


    @Test
    void add_feet_plus_inches_returns_in_feet() {
        Length r = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES));
        assertEquals(new Length(2.0, LengthUnit.FEET), r);
        assertEquals(LengthUnit.FEET, r.getUnit());
    }

    @Test
    void add_inches_plus_feet_returns_in_inches() {
        Length r = new Length(12.0, LengthUnit.INCHES)
                .add(new Length(1.0, LengthUnit.FEET));
        assertEquals(new Length(24.0, LengthUnit.INCHES), r);
        assertEquals(LengthUnit.INCHES, r.getUnit());
    }

    @Test
    void add_yard_plus_feet_returns_in_yards() {
        Length r = new Length(1.0, LengthUnit.YARDS)
                .add(new Length(3.0, LengthUnit.FEET));
        assertEquals(new Length(2.0, LengthUnit.YARDS), r);
    }

    @Test
    void add_inches_plus_yard_returns_in_inches() {
        Length r = new Length(36.0, LengthUnit.INCHES)
                .add(new Length(1.0, LengthUnit.YARDS));
        assertEquals(new Length(72.0, LengthUnit.INCHES), r);
    }

    @Test
    void add_centimeters_plus_inches_returns_in_centimeters() {
        Length r = new Length(2.54, LengthUnit.CENTIMETERS)
                .add(new Length(1.0, LengthUnit.INCHES));
        assertEquals(new Length(5.08, LengthUnit.CENTIMETERS), r);
    }

    @Test
    void add_commutativity() {
        Length r = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES));
        Length r1 = new Length(12.0, LengthUnit.INCHES)
                .add(new Length(1.0, LengthUnit.FEET));
        assertEquals(r,r1);
    }

    @Test
    void add_identity_and_negative() {
        Length identity = new Length(0.0, LengthUnit.INCHES);
        Length r1 = new Length(5.0, LengthUnit.FEET).add(identity.convertTo(LengthUnit.FEET));
        assertEquals(new Length(5.0, LengthUnit.FEET), r1);

        Length r2 = new Length(5.0, LengthUnit.FEET)
                .add(new Length(-2.0, LengthUnit.FEET));
        assertEquals(new Length(3.0, LengthUnit.FEET), r2);
    }

    @Test
    void add_NullSecondOperand() {
        Length feet = new Length(5.0, LengthUnit.FEET);
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> feet.add(null)
        );
        assertTrue(ex.getMessage().toLowerCase().contains("cannot be null"));
    }

    @Test
    void add_small_Value() {
        Length r = new Length(0.01, LengthUnit.FEET)
                .add(new Length(0.02, LengthUnit.FEET));
        assertEquals(new Length(0.03, LengthUnit.FEET), r);
    }

    @Test
    void add_large_value() {
        Length r = new Length(1e6, LengthUnit.FEET)
                .add(new Length(1e6, LengthUnit.FEET));
        assertEquals(new Length(2e6, LengthUnit.FEET), r);
    }
   // UC 7 - Test Cases
    @Test
    void testAddition_ExplicitTargetUnit_feet(){
        Length f = new Length(1.0, LengthUnit.FEET).
                add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(new Length(2.0, LengthUnit.FEET),f);
    }

    @Test
    void testAddition_ExplicitTargetUnit_inches(){
        Length i = new Length(1.0, LengthUnit.FEET).
                add(new Length(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        assertEquals(new Length(24.0, LengthUnit.INCHES),i);
    }

    @Test
    void testAddition_ExplicitTargetUnit_yards(){
        Length y = new Length(1.0, LengthUnit.FEET).
                add(new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(new Length(0.67, LengthUnit.YARDS),y);
    }

    @Test
    void testAddition_ExplicitTargetUnit_centimeter(){
        Length c = new Length(1.0, LengthUnit.INCHES).
                add(new Length(1.0, LengthUnit.INCHES), LengthUnit.CENTIMETERS);
        assertEquals(new Length(5.08, LengthUnit.CENTIMETERS),c);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand(){
        Length y = new Length(2.0, LengthUnit.YARDS).
                add(new Length(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        assertEquals(new Length(3.0, LengthUnit.YARDS),y);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand(){
        Length f = new Length(2.0, LengthUnit.YARDS).
                add(new Length(3.0, LengthUnit.FEET), LengthUnit.FEET);
        assertEquals(new Length(9.0, LengthUnit.FEET),f);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity(){
        Length l1 = new Length(1.0, LengthUnit.FEET).
                add(new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        Length l2= new Length(12.0, LengthUnit.INCHES).
                add(new Length(1.0, LengthUnit.FEET), LengthUnit.YARDS);

        assertEquals(true,l1.equals(l2));
    }

    @Test
    void testAddition_ExplicitTargetUnit_withZero(){
        Length l1 = new Length(5.0, LengthUnit.FEET).
                add(new Length(0.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(new Length(1.67, LengthUnit.YARDS),l1);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValue(){
        Length l1 = new Length(5.0, LengthUnit.FEET).
                add(new Length(-2.0, LengthUnit.FEET), LengthUnit.INCHES);
        assertEquals(new Length(36.0, LengthUnit.INCHES),l1);
    }
    @Test
    void testAddition_ExplicitTargetUnit_TargetNullUnit(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
                //.add(new Length(12.0, LengthUnit.INCHES), null);
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> l1.add(new Length(12.0, LengthUnit.INCHES), null)
        );
        assertEquals("Target unit cannot be null", ex.getMessage());
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale(){
        Length l1 = new Length(1000.0, LengthUnit.FEET).
                add(new Length(500.0, LengthUnit.FEET), LengthUnit.INCHES);
        assertEquals(new Length(18000.0, LengthUnit.INCHES),l1);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale(){
        Length l1 = new Length(12.0, LengthUnit.INCHES).
                add(new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(new Length(0.67, LengthUnit.YARDS),l1);
    }

    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombination(){
        Length l1 = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES),LengthUnit.CENTIMETERS)
                        .add(new Length(1.0, LengthUnit.CENTIMETERS),LengthUnit.YARDS);
        assertEquals(new Length(0.68, LengthUnit.YARDS),l1);
    }

    // UC 8 - Test Cases
    @Test
    void testLengthUnitEnum_FeetConstant(){
        System.out.println(LengthUnit.FEET.getConversionFactor());
        assertEquals(12.0,LengthUnit.FEET.getConversionFactor());
    }

    @Test
    void testLengthUnitEnum_InchesConstant(){
        System.out.println(LengthUnit.INCHES.getConversionFactor());
        assertEquals(1.0,LengthUnit.INCHES.getConversionFactor());
    }

    @Test
    void testLengthUnitEnum_YardsConstant(){
        System.out.println(LengthUnit.YARDS.getConversionFactor());
        double value = LengthUnit.YARDS.getConversionFactor();
        assertEquals(36.0,LengthUnit.YARDS.getConversionFactor());
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant(){
        System.out.println(LengthUnit.CENTIMETERS.getConversionFactor());
        assertEquals(0.393701,LengthUnit.CENTIMETERS.getConversionFactor());
    }

    @Test
    void testConvertToBaseUnit_InchesToInches(){
        assertEquals(12,LengthUnit.INCHES.convertToBaseUnit(12.0));
    }

    @Test
    void testConvertToBaseUnit_FeetToInches(){
        assertEquals(12,LengthUnit.FEET.convertToBaseUnit(1.0));
    }

    @Test
    void testConvertToBaseUnit_YardsToInches(){
        assertEquals(36.0,LengthUnit.YARDS.convertToBaseUnit(1.0));
    }

    @Test
    void testConvertToBaseUnit_CentimetersToInches(){
        assertEquals(0.39,LengthUnit.CENTIMETERS.convertToBaseUnit(1.0));
    }

    @Test
    void testConvertFromBaseUnit_InchesToInches(){
        assertEquals(12.0,LengthUnit.INCHES.convertFromUnit(12.0));
    }

    @Test
    void testConvertFromBaseUnit_InchesToFeet(){
        assertEquals(1.0,LengthUnit.FEET.convertFromUnit(12.0));
    }

    @Test
    void testConvertFromBaseUnit_InchesToYards(){
        assertEquals(0.03,LengthUnit.YARDS.convertFromUnit(1.0));
    }

    @Test
    void testConvertFromBaseUnit_InchesToCentimeters(){
        assertEquals(2.54,LengthUnit.CENTIMETERS.convertFromUnit(1.0));
    }

    @Test
    void testQuantityLengthRefactored_Equality(){
        Length f = new Length(1.0,LengthUnit.FEET);
        Length i = new Length(12.0,LengthUnit.INCHES);
        System.out.println(f+"\n"+i);
        assertEquals(true,f.equals(i));
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo(){
        Length f = new Length(1.0,LengthUnit.FEET);
        Length i = f.convertTo(LengthUnit.INCHES);
        System.out.println(f+"\n"+i);
        assertEquals(true,f.equals(i));
    }

    @Test
    void testQuantityLengthRefactored_Add(){
        Length f = new Length(1.0,LengthUnit.FEET);
        Length f2 = f.add(new Length(12.0,LengthUnit.INCHES),LengthUnit.FEET);
        System.out.println(f+"\n"+f2);
        assertEquals(2.0,f2.getValue());
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit(){
        Length f = new Length(1.0,LengthUnit.FEET);
        Length f2 = f.add(new Length(12.0,LengthUnit.INCHES),LengthUnit.YARDS);
        System.out.println(f+"\n"+f2);
        assertEquals(0.67,f2.getValue());
    }

    @Test
    void testQuantityLengthRefactored_NullUnit(){
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Length(12.0, null)
        );
        assertEquals("unit must not be null", ex.getMessage());
    }

    @Test
    void testUnitImmutable() {
        LengthUnit feet = LengthUnit.FEET;
        double original = feet.getConversionFactor();
        // We verify that the value cannot be changed
        assertEquals(original, LengthUnit.FEET.getConversionFactor(),
                "Enum conversion factors must remain immutable.");
    }

    // Weight Measurement App Test Cases
    @Test
    void testEquality_KilogramToKilogram_SameValue(){
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals
                (new Weight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue(){
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals
                (new Weight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue(){
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals
                (new Weight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testEquality_GramToKiloGram_EquivalentValue(){
        assertTrue(new Weight(1000.0, WeightUnit.GRAM).equals
                (new Weight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_WeightToLength_Incompatible(){
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals
                (new Length(1.0, LengthUnit.FEET)));
    }
    @Test
    void testEquality_NullComparison(){
        assertNotEquals(null, new Weight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_SameReference(){
        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight ref = w;
        assertEquals(w, ref);
    }

    @Test
    void testEquality_NullUnit(){
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Weight(1.0, null)
        );
        assertEquals("unit must not be null", ex.getMessage());
    }
    @Test
    void testEquality_transitiveProperty() {
        Weight k = new Weight(1.0, WeightUnit.KILOGRAM);    // 1 Kilogram
        Weight g = new Weight(1000.0, WeightUnit.GRAM);    // 1000 Gram
        Weight k1 = new Weight(1.0, WeightUnit.KILOGRAM);  // 1 Kilogram
        // transitive
        assertTrue(k.equals(g)); //True
        assertTrue(g.equals(k1)); //True
        assertTrue(k.equals(k1)); //True
    }

    @Test
    void testEquality_ZeroValue() {
        Weight k = new Weight(0.0, WeightUnit.KILOGRAM);    // 0 Kilogram
        Weight g = new Weight(0.0, WeightUnit.GRAM);        // 0 Gram
        assertTrue(k.equals(g)); //True
    }

    @Test
    void testEquality_NegativeWeight() {
        Weight k = new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight g = new Weight(-1000.0, WeightUnit.GRAM);
        assertTrue(k.equals(g)); //True
    }

    @Test
    void testEquality_LargeWeightValue() {
        Weight k = new Weight(1000000.0, WeightUnit.GRAM);
        Weight g = new Weight(1000.0, WeightUnit.KILOGRAM);
        assertTrue(k.equals(g)); //True
    }

    @Test
    void testEquality_SmallWeightValue() {
        Weight k = new Weight(0.001, WeightUnit.KILOGRAM);
        Weight g = new Weight(1.0, WeightUnit.GRAM);
        assertTrue(k.equals(g)); //True
    }

    @Test
    void testConversion_PoundToKilogram() {
        Weight p = new Weight(2.20462, WeightUnit.POUND);
        Weight k = p.convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0,k.getValue());
    }

    @Test
    void testConversion_KilogramToPound() {
        Weight k = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight p = k.convertTo(WeightUnit.POUND);
        assertEquals(2.20,p.getValue());
    }

    @Test
    void testConversion_SameUnit() {
        Weight k = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight k1 = k.convertTo(WeightUnit.KILOGRAM);
        assertEquals(5.0,k1.getValue());
    }

    @Test
    void testConversion_ZeroValue() {
        Weight k = new Weight(0.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);
        assertEquals(0.0,k.getValue());
    }

    @Test
    void testConversion_NegativeValue() {
        Weight k = new Weight(-1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);
        assertEquals(-1000.0,k.getValue());
    }

    @Test
    void testConversion_RoundTrip() {
        Weight k = new Weight(1.5, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.5,k.getValue());
    }

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        Weight k = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(2.0,WeightUnit.KILOGRAM));
        assertEquals(3.0,k.getValue());
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        Weight k = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0,WeightUnit.GRAM));
        assertEquals(2.0,k.getValue());
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {
        Weight k = new Weight(2.20462, WeightUnit.POUND)
                .add(new Weight(1.0,WeightUnit.KILOGRAM));
        assertEquals(4.41,k.getValue());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gram() {
        Weight k = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0,WeightUnit.GRAM),WeightUnit.GRAM);
        assertEquals("Quantity(2000.0, GRAM)",k.toString());
    }

    @Test
    void testAddition_Commutativity() {
        Weight w1 = new Weight(1.0,WeightUnit.KILOGRAM).add(new Weight(1000.0,WeightUnit.GRAM));
        Weight w2 = new Weight(1000.0,WeightUnit.GRAM).add(new Weight(1.0,WeightUnit.KILOGRAM));
        assertEquals(true,w1.equals(w2));
        /*assertEquals(true,new Weight(1.0,WeightUnit.KILOGRAM)
                .add(new Weight(1000.0,WeightUnit.GRAM))
                .equals(new Weight(1000.0,WeightUnit.GRAM)
                        .add(new Weight(1.0,WeightUnit.KILOGRAM));*/
    }

    @Test
    void testAddition_withZero() {
        Weight w1 = new Weight(5.0,WeightUnit.KILOGRAM).add(new Weight(0.0,WeightUnit.GRAM));
        assertEquals("Quantity(5.0, KILOGRAM)",w1.toString());
    }

    @Test
    void testAddition_NegativeValues() {
        Weight w1 = new Weight(5.0,WeightUnit.KILOGRAM).add(new Weight(-2000.0,WeightUnit.GRAM));
        assertEquals("Quantity(3.0, KILOGRAM)",w1.toString());
    }

    @Test
    void testAddition_LargeValues() {
        Weight w1 = new Weight(1e6,WeightUnit.KILOGRAM).add(new Weight(1e6,WeightUnit.KILOGRAM));
       // assertEquals("Quantity(2e6, KILOGRAM)",w1.toString());
        assertEquals(2e6,w1.getValue());

    }


}