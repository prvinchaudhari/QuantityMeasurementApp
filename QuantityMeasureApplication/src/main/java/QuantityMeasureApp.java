import java.util.Objects;
import java.util.Scanner;

public class QuantityMeasureApp {

    public static class Feet {

        private final double value1;

        public Feet(double value1) {
            this.value1 = value1;
        }

        @Override
        public boolean equals(Object obj) {

            //Reference
            if (this == obj) {
                System.out.println("Checking Reference.....");
                return true;
            }

            if (obj == null || getClass() != obj.getClass() ) {
                System.out.println("Checking Null .....");
                return false;
            }

            Feet feet = (Feet) obj;
            System.out.println("Checking double Compare method.....");
            return Double.compare(feet.value1, this.value1) == 0;
        }
    }

    public static void main(String[] args) {

        QuantityMeasureApp.Feet obj4 = null;
        QuantityMeasureApp.Feet obj1 = new QuantityMeasureApp.Feet(100);
        QuantityMeasureApp.Feet obj2 = new QuantityMeasureApp.Feet(100);
        QuantityMeasureApp.Feet obj3=null;


        System.out.println("Both Object Value is :- "+(obj1.equals(obj2)));
        System.out.println("Both Object Value is :- "+(obj1.equals(obj3)));
        //System.out.println("Both Object Value is :- "+(Objects.equals(obj4,obj3)));
        System.out.println("Both Object Value is :- "+(obj3.equals(obj1)));


    }
}
