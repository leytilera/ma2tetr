package ma2tetr.model;

/**
 * Represents a movable point in space
 */
public class Point3D extends Coords3D {

    public Point3D(double x, double y, double z) {
        super(x, y, z);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void move(Coords3D direction, double multiplier) {
        this.x += multiplier * direction.getX();
        this.y += multiplier * direction.getY();
        this.z += multiplier * direction.getZ();
    }
    
}
