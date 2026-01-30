import java.util.Objects;
import java.util.Scanner;

public class QuantityMeasureApp {

    public static class Feet {

        private final double value1;

        public Feet(double value1) {
            this.value1 = value1;
        }

        public double toInches(){
            return value1 * 12;
        }

        @Override
        public boolean equals(Object obj) {

            //Reference
            if (this == obj) {
                System.out.println("Checking Both Feet class Reference:- "+this+" , " + obj);
                return true;
            }

            if (obj == null) {
                System.out.println("Checking feet class object  Null:- "+this+" , " + obj);
                return false;
            }

            if ( getClass() != obj.getClass() ) {
                System.out.println("Checking Class Type:- "+this+" , " + obj.getClass());
                return false;
            }

            Feet feet = (Feet) obj;
            System.out.println("Checking feet class Double Compare Method.:- "+feet.value1 +" , " + this.value1);
            return Double.compare(feet.value1, this.value1) == 0;
        }


    }
    public static class Inches {

        private final double value1;

        public Inches(double value1) {
            this.value1 = value1;
        }



        @Override
        public boolean equals(Object obj) {

            //Reference
            if (this == obj) {
                System.out.println("Checking Inches Class Reference:- "+this+" , " + obj);
                return true;
            }

            if (obj == null ) {
                System.out.println("Checking Inches class Null Comparison:- "+this+" , " + obj);
                return false;
            }
            if (obj instanceof Feet)
            {
                Feet feet = (Feet) obj;
                System.out.println("Checking feet class Double Compare Method.:- "+feet.value1 +" , " + this.value1);
                return Double.compare(this.value1, feet.toInches()) == 0;
            }
            if(obj instanceof Inches) {
                Inches inches = (Inches) obj;
                System.out.println("Checking Inches double Compare method:- " + inches.value1 + " , " + this.value1);
                return Double.compare(inches.value1, this.value1) == 0;
            }
            if ( getClass() != obj.getClass() ) {
                System.out.println("Checking Class type:- "+this+" , " + obj.getClass());
                return false;
            }
            return false;

        }

    }
    public static void demonstrateFeetEquality(double number1,double number2){
        QuantityMeasureApp.Feet obj1 = new QuantityMeasureApp.Feet(number1);
        QuantityMeasureApp.Feet obj2 = new QuantityMeasureApp.Feet(number2);
        QuantityMeasureApp.Feet obj3=null;
        System.out.println("Feet Both Object Value is :- "+(obj1.equals(obj2)));
        System.out.println("Feet Both Object Value is :- "+(obj1.equals(obj3)));
    }

    public static void demonstrateInchesEquality(double number1,double number2){
        QuantityMeasureApp.Inches obj1 = new QuantityMeasureApp.Inches(number1);
        QuantityMeasureApp.Inches obj2 = new QuantityMeasureApp.Inches(number2);
        QuantityMeasureApp.Inches obj3=null;
        System.out.println("Inches Both Object Value is :- "+(obj1.equals(obj2)));
        System.out.println("Inches Both Object Value is :- "+(obj1.equals(obj3)));

        QuantityMeasureApp.Inches inches = new QuantityMeasureApp.Inches(number1);
        QuantityMeasureApp.Feet feet = new QuantityMeasureApp.Feet(number2);
        System.out.println(inches.equals(feet)); // True
       // System.out.println(inches.equals(feet)); // false
    }

    public static void main(String[] args) {
        demonstrateFeetEquality(1.0,1.0);
        demonstrateInchesEquality(12,1.0);
       // demonstrateInchesEquality(24,1.0);
    }
}
