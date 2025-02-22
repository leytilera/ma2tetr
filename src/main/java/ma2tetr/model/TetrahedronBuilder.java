package ma2tetr.model;

import ma2tetr.condition.EquilateralTriangleCondition;

public class TetrahedronBuilder {

    private Point3D p1;
    private Point3D p2;
    private Point3D p3;

    private Point3D top;

    private double accuracy;

    public TetrahedronBuilder(Point3D top, double accuracy) {
        this.top = top;
        this.accuracy = accuracy;
    }

    public void build() {
        EquilateralTriangleBuilder trib = new EquilateralTriangleBuilder(top, 1);
        trib.build();
        p1 = trib.getP1();
        p2 = trib.getP2();
        p3 = trib.getP3();
        EquilateralTriangleCondition inv = new EquilateralTriangleCondition(p1, p2, p3);
        inv.setAccuracy(accuracy);
        if (!inv.isFulfilled()) {
            throw new RuntimeException("Unexpected error");
        }
        double c = inv.getDistance();
        double b = top.distance(p1);
        double a = Math.sqrt(Math.pow(c, 2) - Math.pow(b, 2));
        p1.setY(p1.getY() - a);
        p2.setY(p2.getY() - a);
        p3.setY(p3.getY() - a);
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
