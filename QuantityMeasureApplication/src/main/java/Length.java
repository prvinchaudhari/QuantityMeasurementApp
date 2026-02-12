
public class Length {

    private static final double EPS = 1e-9;

    private double value;

    private LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("value must be a finite number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("unit must not be null");
        }
        this.value = value;
        this.unit = unit;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Length)) return false;
        Length that = (Length) obj;
        return this.compare(that);
    }

    /*@Override
    public int hashCode() {
        long normalized = Double.doubleToLongBits(
                Math.rint(this.convertToBaseUnit() / EPS) * EPS
        );
        return (int) (normalized ^ (normalized >>> 32));
    }*/

    public Length convertTo(LengthUnit targetUnit){
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }
        if (!Double.isFinite(this.value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }
        // 1) Normalize this length to base unit (inches)
        double valueInInches = this.convertToBaseUnit(); // already uses this.value and this.unit

        // 2) Convert from base unit (inches) to target unit
        //double targetValue = targetUnit.fromInches(valueInInches);
        double targetValue = targetUnit.convertFromUnit(valueInInches);
        // 3) Optional: round to 2 decimals to keep UI consistency
        targetValue = Math.round(targetValue * 100.0) / 100.0;

        // 4) Return a new immutable instance
        return new Length(targetValue, targetUnit);
    }
    public Length add(Length thatLength){
        if (thatLength == null) {
            throw new IllegalArgumentException("Length to add cannot be null");
        }
        if (!Double.isFinite(this.value) || !Double.isFinite(thatLength.value)) {
            throw new IllegalArgumentException("Values must be finite numbers");
        }
        // Convert both to base unit (inches)
        double thisInches = this.convertToBaseUnit();
        double thatInches = thatLength.convertToBaseUnit();
        // Sum in base unit
        double sumInches = thisInches + thatInches;

        // Convert result to unit of FIRST operand
        double resultValue = convertFromBaseToTargetUnit(sumInches, this.unit);

        // Return new immutable object
        return new Length(resultValue, this.unit);
    }

    public Length add(Length length, LengthUnit targetUnit){
        if (length == null) {
            throw new IllegalArgumentException("Other length cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        return addAndConvert(length,targetUnit);
    }

    public boolean compare(Length thatLength) {
        if (thatLength == null) return false;
        double a = this.convertToBaseUnit();
        double b = thatLength.convertToBaseUnit();
        return Math.abs(a - b) <= EPS;
    }

    private Length addAndConvert(Length length,LengthUnit targetUnit){
        // Convert both to base unit (inches)
        double thisInInches = this.unit.convertToBaseUnit(this.value);
        double otherInInches = length.unit.convertToBaseUnit(length.value);

       /* double thisInInches = this.unit.toInches(this.value);
        double otherInInches = length.unit.toInches(length.value);
*/
        // Add
        double sumInInches = thisInInches + otherInInches;

        // Convert to target unit
       // double resultValue = targetUnit.fromInches(sumInInches);
        double resultValue = targetUnit.convertFromUnit(sumInInches);
        resultValue = Math.round(resultValue * 100.0) / 100.0;
        return new Length(resultValue, targetUnit);
    }

    private double convertToBaseUnit() {
        //  return unit.toInches(value);
        return unit.convertToBaseUnit(value);
    }

    private double convertFromBaseToTargetUnit(double lengthInches, LengthUnit targetUnit) {
       // double converted = targetUnit.fromInches(lengthInches);
        double converted = targetUnit.convertFromUnit(lengthInches);
        return Math.round(converted * 100.0) / 100.0;   // rounding to 2 decimals
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public double value() { return value; }

    public static void main(String[] args) {
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = feet.convertTo(LengthUnit.INCHES);
        Length yards = feet.convertTo(LengthUnit.YARDS);
        System.out.println(inches);
        System.out.println(yards);
    }
}
