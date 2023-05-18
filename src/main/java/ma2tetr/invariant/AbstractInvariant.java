package ma2tetr.invariant;

public abstract class AbstractInvariant {
    
    private double accuracy = 0.00000000001;

    public abstract boolean isFulfilled();

    public boolean checkEqual(double v1, double v2) {
        return Math.abs(v1 - v2) <= accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

}
