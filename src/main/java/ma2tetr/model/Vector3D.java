package ma2tetr.model;

public class Vector3D extends Coords3D {

    public Vector3D(double x, double y, double z) {
        super(x, y, z);
    }

    public static Vector3D createFromPoints(Coords3D p1, Coords3D p2) {
        double x = p2.getX() - p1.getX();
        double y = p2.getY() - p1.getY();
        double z = p2.getZ() - p1.getZ();
        return new Vector3D(x, y, z);
    }
    
}
