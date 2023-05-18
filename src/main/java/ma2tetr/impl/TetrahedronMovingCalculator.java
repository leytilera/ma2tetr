package ma2tetr.impl;

import java.util.HashSet;
import java.util.Set;

import ma2tetr.api.ITetrahedronCoordCalculator;
import ma2tetr.invariant.RadiusInvariant;
import ma2tetr.invariant.TetrahedronInvariant;
import ma2tetr.model.Coords3D;
import ma2tetr.model.Point3D;
import ma2tetr.model.Tetrahedron;
import ma2tetr.model.Vector3D;

public class TetrahedronMovingCalculator implements ITetrahedronCoordCalculator {

    private double radius = 1.0;
    private Tetrahedron tetrahedron = null;

    @Override
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculate() {
        Coords3D center = new Vector3D(0, 0, 0);
        Point3D top = new Point3D(0, radius, 0);
        TriangleBaseMover triangle = new TriangleBaseMover(radius);
        triangle.move(-radius);
        Set<Coords3D> points = new HashSet<>();
        points.add(top);
        points.add(triangle.getP1());
        points.add(triangle.getP2());
        points.add(triangle.getP3());
        RadiusInvariant rinv = new RadiusInvariant(points, center, radius);
        TetrahedronInvariant tinv = new TetrahedronInvariant(top, triangle.getP1(), triangle.getP2(), triangle.getP3());
        double scaling = radius / 10;
        while(true) {
            triangle.move(scaling);
            if (!rinv.isFulfilled()) {
                throw new RuntimeException("Unexpected error");
            } else if (tinv.isFulfilled()) {
                break;
            } else if (tinv.firstDifference() < 0) {
                triangle.move(-scaling);
                scaling = scaling / 10;
            }
        }
        tetrahedron = new Tetrahedron(top, triangle.getP1(), triangle.getP2(), triangle.getP3());
    }

    @Override
    public Tetrahedron getTetrahedron() {
        return this.tetrahedron;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }
    
}
