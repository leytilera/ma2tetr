package ma2tetr.model;

public abstract class Coords3D {
    
    protected double x;
    protected double y;
    protected double z;
    
    public Coords3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double distance(Coords3D other) {
        double xDist = other.x - this.x;
        double yDist = other.y - this.y;
        double zDist = other.z - this.z;
        return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2) + Math.pow(zDist, 2));
    }

    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + ", z=" + z + "]";
    }    

}
