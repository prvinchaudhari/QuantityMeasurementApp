import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuantityMeasureAppTest {

        private QuantityMeasureApp.Feet feet;
        private QuantityMeasureApp.Inches inches;

        @BeforeEach
        void setUp() {
            feet = new QuantityMeasureApp.Feet(1.0);
            inches=new QuantityMeasureApp.Inches(1.0);
        }

        @Test
        void testFeetEquality_sameValue() {
            QuantityMeasureApp.Feet obj2 = new QuantityMeasureApp.Feet(1.0);
            assertEquals(true,feet.equals(obj2));
        }

    @Test
    void testFeetEquality_differentValue() {
        QuantityMeasureApp.Feet obj2 = new QuantityMeasureApp.Feet(2.0);
        assertEquals(false,feet.equals(obj2));
    }

    @Test
    void testFeetEquality_nullComparison() {
        QuantityMeasureApp.Feet obj2 = null;
        assertEquals(false, feet.equals(obj2));
       // assertEquals(false, Objects.equals(obj1,obj2));
    }

    @Test
    void testFeetEquality_differentClass() {
        Object obj2 = new String();
        assertEquals(false,feet.equals(obj2));
    }

    @Test
    void testFeetEquality_sameReference() {
        QuantityMeasureApp.Feet obj2 = feet;
        assertEquals(true,feet.equals(obj2));
    }

    @Test
    void testInchesEquality_sameValue() {
        QuantityMeasureApp.Inches obj2 = new QuantityMeasureApp.Inches(1.0);
        assertEquals(true,inches.equals(obj2));
    }

    @Test
    void testInchesEquality_differentValue() {
        QuantityMeasureApp.Inches obj2 = new QuantityMeasureApp.Inches(2.0);
        assertEquals(false,inches.equals(obj2));
    }

    @Test
    void testInchesEquality_nullComparison() {
        QuantityMeasureApp.Inches obj2 = null;
        assertEquals(false, inches.equals(obj2));
        // assertEquals(false, Objects.equals(obj1,obj2));
    }

    @Test
    void testInchesEquality_differentClass() {
        Object obj2 = new String();
        assertEquals(false,inches.equals(obj2));
    }

    @Test
    void testInchesEquality_sameReference() {
        QuantityMeasureApp.Inches obj2 = inches;
        assertEquals(true,inches.equals(obj2));
    }

    @Test
    void testEquality_bothFeetAndInchesEquality() {
        QuantityMeasureApp.Inches inches = new QuantityMeasureApp.Inches(12);
        QuantityMeasureApp.Feet feet = new QuantityMeasureApp.Feet(1);
        assertEquals(true,inches.equals(feet));
    }

    @Test
    void testNotEquality_bothFeetAndInchesNotEquality() {
        QuantityMeasureApp.Inches inches = new QuantityMeasureApp.Inches(14);
        QuantityMeasureApp.Feet feet = new QuantityMeasureApp.Feet(1);
        assertEquals(false,inches.equals(feet));
    }

}
