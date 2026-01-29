import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuantityMeasureAppTest {

        private QuantityMeasureApp.Feet feet;

        @BeforeEach
        void setUp() {
            feet = new QuantityMeasureApp.Feet(100);
        }

        @Test
        void testFeetEquality_sameValue() {
            QuantityMeasureApp.Feet obj1 = new QuantityMeasureApp.Feet(100);
            QuantityMeasureApp.Feet obj2 = new QuantityMeasureApp.Feet(100);
            assertEquals(true,obj1.equals(obj2));
        }

    @Test
    void testFeetEquality_differentValue() {
        QuantityMeasureApp.Feet obj1 = new QuantityMeasureApp.Feet(100);
        QuantityMeasureApp.Feet obj2 = new QuantityMeasureApp.Feet(200);
        assertEquals(false,obj1.equals(obj2));
    }

    @Test
    void testFeetEquality_nullComparison() {
      //  QuantityMeasureApp.Feet obj1 = new QuantityMeasureApp.Feet(100);
        QuantityMeasureApp.Feet obj1 = null;
        QuantityMeasureApp.Feet obj2 = new QuantityMeasureApp.Feet(100);
        assertEquals(false, obj1.equals(obj2));
       // assertEquals(false, Objects.equals(obj1,obj2));
    }

    @Test
    void testFeetEquality_differentClass() {
        QuantityMeasureApp.Feet obj1 = new QuantityMeasureApp.Feet(100);
        Object obj2 = new String();
        assertEquals(false,obj1.equals(obj2));
    }

    @Test
    void testFeetEquality_sameReference() {
        QuantityMeasureApp.Feet obj1 = new QuantityMeasureApp.Feet(100);
        QuantityMeasureApp.Feet obj2 = obj1;
        assertEquals(true,obj1.equals(obj2));
    }

}
