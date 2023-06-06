package ma2tetr.condition;

public abstract class AbstractCondition {
    
    private double accuracy = 0.00000000001;

    public abstract boolean isFulfilled();

    public boolean checkEqual(double v1, double v2) {
        return Math.abs(v1 - v2) <= accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

}
