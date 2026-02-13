public class Weight {

    private static final double EPS = 1e-9;

    private double value;

    private WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
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
        if (!(obj instanceof Weight)) return false;
        Weight that = (Weight) obj;
        return this.compare(that);
    }

    /*@Override
    public int hashCode() {
        long normalized = Double.doubleToLongBits(
                Math.rint(this.convertToBaseUnit() / EPS) * EPS
        );
        return (int) (normalized ^ (normalized >>> 32));
    }*/

    public Weight convertTo(WeightUnit targetUnit){
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }
        if (!Double.isFinite(this.value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }
        // 1) Normalize this weight to base unit (Kilogram)
        double valueInKilogram = this.convertToBaseUnit(); // already uses this.value and this.unit

        // 2) Convert from base unit (Kilogram) to target unit
        double targetValue = targetUnit.convertFromBaseUnit(valueInKilogram);
        // 3) Optional: round to 2 decimals to keep UI consistency
        targetValue = Math.round(targetValue * 100.0) / 100.0;

        // 4) Return a new immutable instance
        return new Weight(targetValue, targetUnit);
    }
    public Weight add(Weight thatWeight){
        if (thatWeight == null) {
            throw new IllegalArgumentException("Length to add cannot be null");
        }
        if (!Double.isFinite(this.value) || !Double.isFinite(thatWeight.value)) {
            throw new IllegalArgumentException("Values must be finite numbers");
        }
        // Convert both to base unit (Kilogram)
        double thisKilogram = this.convertToBaseUnit();
        double thatKilogram = thatWeight.convertToBaseUnit();
        // Sum in base unit
        double sumKilogram = thisKilogram + thatKilogram;

        // Convert result to unit of FIRST operand
        double resultValue = convertFromBaseToTargetUnit(sumKilogram, this.unit);

        // Return new immutable object
        return new Weight(resultValue, this.unit);
    }

    public Weight add(Weight weight, WeightUnit targetUnit){
        if (weight == null) {
            throw new IllegalArgumentException("Other length cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        return addAndConvert(weight,targetUnit);
    }

    public boolean compare(Weight thatWeight) {
        if (thatWeight == null) return false;
        double a = this.convertToBaseUnit();
        double b = thatWeight.convertToBaseUnit();
        return Math.abs(a - b) <= EPS;
    }

    private Weight addAndConvert(Weight weight,WeightUnit targetUnit){
        // Convert both to base unit (Kilogram)
        double thisInKilogram = this.unit.convertToBaseUnit(this.value);
        double otherInKilogram = weight.unit.convertToBaseUnit(weight.value);

        // Add
        double sumInKilogram = thisInKilogram + otherInKilogram;

        // Convert to target unit
        double resultValue = targetUnit.convertFromBaseUnit(sumInKilogram);
        resultValue = Math.round(resultValue * 100.0) / 100.0;
        return new Weight(resultValue, targetUnit);
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private double convertFromBaseToTargetUnit(double weightInches, WeightUnit targetUnit) {
        double converted = targetUnit.convertFromBaseUnit(weightInches);
        return Math.round(converted * 100.0) / 100.0;   // rounding to 2 decimals
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public double value() { return value; }

    public static void main(String[] args) {
        Weight kilogram = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight inches = kilogram.convertTo(WeightUnit.GRAM);
        System.out.println(inches);
    }
}
