package ma2tetr.impl;

import ma2tetr.model.EquilateralTriangleBuilder;
import ma2tetr.model.Point3D;
import ma2tetr.model.Vector3D;

public class TriangleBaseMover {
    
    private Point3D p1;
    private Point3D p2;
    private Point3D p3;

    private Point3D center;

    private Vector3D v1;
    private Vector3D v2;
    private Vector3D v3;

    private Vector3D yVec = new Vector3D(0, 1, 0);
    private Vector3D origin = new Vector3D(0, 0, 0);

    private double radius;

    public TriangleBaseMover(double radius) {
        this.center = new Point3D(0, 0, 0);
        this.radius = radius;
        EquilateralTriangleBuilder builder = new EquilateralTriangleBuilder(center, 1, 0);
        builder.build();
        p1 = builder.getP1();
        p2 = builder.getP2();
        p3 = builder.getP3();

        v1 = Vector3D.createFromPoints(center, p1);
        v2 = Vector3D.createFromPoints(center, p2);
        v3 = Vector3D.createFromPoints(center, p3);

        scalePoints(radius);
    }

    public void move(double yScale) {
        double oldScaling = getScaling();
        movePoints(yScale);
        double factor = calcNewScaling(oldScaling);
        scalePoints(factor);
    }

    private void scalePoints(double factor) {
        p1.move(v1, factor);
        p2.move(v2, factor);
        p3.move(v3, factor);
    }

    private void movePoints(double yScale) {
        p1.move(yVec, yScale);
        p2.move(yVec, yScale);
        p3.move(yVec, yScale);
        center.move(yVec, yScale);
    }

    private double calcNewScaling(double oldScaling) {
        double b = center.distance(origin);
        double c = radius;
        double a = Math.sqrt(Math.pow(c, 2) - Math.pow(b, 2));
        return a - oldScaling;
    }

    private double getScaling() {
        return center.distance(p1);
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

    public Point3D getCenter() {
        return center;
    }

}
