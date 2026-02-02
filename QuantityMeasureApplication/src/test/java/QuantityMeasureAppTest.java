import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuantityMeasureAppTest {

        private  Length length,length1;

        @BeforeEach
        void setUp() {
            length = new Length(1.0, Length.LengthUnit.FEET);
            length1 = new Length(12.0, Length.LengthUnit.INCHES);
        }

        @Test
        void testFeetEquality_sameValue() {
            Length feet = new Length(1.0, Length.LengthUnit.FEET);
            assertEquals(true,feet.equals(length));
        }

    @Test
    void testFeetEquality_differentValue() {
        Length feet = new Length(2.0, Length.LengthUnit.FEET);
        assertEquals(false,length.equals(feet));
    }

    @Test
    void testFeetEquality_nullComparison() {
        Length feet =null;
        assertEquals(false, length.equals(feet));
       // assertEquals(false, Objects.equals(obj1,obj2));
    }

    @Test
    void testFeetEquality_differentClass() {
        Object obj2 = new String();
        assertEquals(false,length.equals(obj2));
    }

    @Test
    void testFeetEquality_sameReference() {
            Length feet = length;
        assertEquals(true,length.equals(feet));
    }

    @Test
    void testInchesEquality_sameValue() {
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        assertEquals(true,length1.equals(inches));
    }

    @Test
    void testInchesEquality_differentValue() {
        Length inches = new Length(24.0, Length.LengthUnit.INCHES);
        assertEquals(false,length1.equals(inches));
    }

    @Test
    void testInchesEquality_nullComparison() {
        Length inches = null;
        assertEquals(false, length1.equals(inches));
        // assertEquals(false, Objects.equals(obj1,obj2));
    }

    @Test
    void testInchesEquality_differentClass() {
        Object obj2 = new String();
        assertEquals(false,length1.equals(obj2));
    }

    @Test
    void testInchesEquality_sameReference() {
        Length inches = length1;
        assertEquals(true,length1.equals(inches));
    }

    @Test
    void testEquality_bothFeetAndInchesEquality() {
        assertEquals(true,length.equals(length1));
    }

    @Test
    void testNotEquality_bothFeetAndInchesNotEquality() {
        Length feet = new Length(2.0, Length.LengthUnit.INCHES);
        assertEquals(false,length1.equals(feet));
    }

}
