package ma2tetr.model;

public class EquilateralTriangleBuilder {

    private Point3D p1;
    private Point3D p2;
    private Point3D p3;

    private Coords3D center;
    private double radius;

    public EquilateralTriangleBuilder(Coords3D center, double radius, double rotation) {
        this.center = center;
        this.radius = radius;
    }

    public void build() {
        p1 = new Point3D(0, center.getY(), radius);

        double p2x = (p1.getX() * Math.cos((Math.PI * 2)/3)) - (p1.getZ() * Math.sin((Math.PI * 2)/3));
        double p2z = (p1.getX() * Math.sin((Math.PI * 2)/3)) + (p1.getZ() * Math.cos((Math.PI * 2)/3));
        p2 = new Point3D(p2x, center.getY(), p2z);

        double p3x = (p1.getX() * Math.cos((Math.PI * 4)/3)) - (p1.getZ() * Math.sin((Math.PI * 4)/3));
        double p3z = (p1.getX() * Math.sin((Math.PI * 4)/3)) + (p1.getZ() * Math.cos((Math.PI * 4)/3));
        p3 = new Point3D(p3x, center.getY(), p3z);

        p1.setX(p1.getX() + center.getX());
        p1.setZ(p1.getZ() + center.getZ());
        p2.setX(p2.getX() + center.getX());
        p2.setZ(p2.getZ() + center.getZ());
        p3.setX(p3.getX() + center.getX());
        p3.setZ(p3.getZ() + center.getZ());
    }

    public Point3D getP1() {
        return p1;
    }

    public Point3D getP2() {
        return p2;
    }

    public Point3D getP3() {
        return p3;
    }  
    
}
