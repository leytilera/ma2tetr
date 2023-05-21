package ma2tetr.model;

import java.util.HashSet;
import java.util.Set;

public class Tetrahedron {

    private Point3D top;
    private Point3D p1;
    private Point3D p2;
    private Point3D p3;

    private Vector3D v1;
    private Vector3D v2;
    private Vector3D v3;

    public Tetrahedron(Point3D top, double accuracy) {
        this.top = top;
        TetrahedronBuilder builder = new TetrahedronBuilder(top, accuracy);
        builder.build();
        p1 = builder.getP1();
        p2 = builder.getP2();
        p3 = builder.getP3();

        v1 = Vector3D.createFromPoints(top, p1);
        v2 = Vector3D.createFromPoints(top, p2);
        v3 = Vector3D.createFromPoints(top, p3);

        this.scale(-1);
    }

    

    public Tetrahedron(Point3D top, Point3D p1, Point3D p2, Point3D p3) {
        this.top = top;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

        v1 = Vector3D.createFromPoints(top, p1);
        v2 = Vector3D.createFromPoints(top, p2);
        v3 = Vector3D.createFromPoints(top, p3);
    }

    public Coords3D getTop() {
        return top;
    }

    public Coords3D getP1() {
        return p1;
    }

    public Coords3D getP2() {
        return p2;
    }

    public Coords3D getP3() {
        return p3;
    }

    public Set<Coords3D> getPointsSet() {
        Set<Coords3D> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(top);
        return set;
    }

    public void scale(double factor) {
        p1.move(v1, factor);
        p2.move(v2, factor);
        p3.move(v3, factor);
    }
    
    public void move(Coords3D direction, double multiplier) {
        top.move(direction, multiplier);
        p1.move(direction, multiplier);
        p2.move(direction, multiplier);
        p3.move(direction, multiplier);
    }
    
}
