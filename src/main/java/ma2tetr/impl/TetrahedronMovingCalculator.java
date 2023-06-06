package ma2tetr.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ma2tetr.api.ITetrahedronCoordCalculator;
import ma2tetr.condition.RadiusCondition;
import ma2tetr.condition.TetrahedronCondition;
import ma2tetr.model.Coords3D;
import ma2tetr.model.IterationState;
import ma2tetr.model.Point3D;
import ma2tetr.model.Tetrahedron;
import ma2tetr.model.Vector3D;

public class TetrahedronMovingCalculator implements ITetrahedronCoordCalculator {

    private double radius = 1.0;
    private Tetrahedron tetrahedron = null;
    private List<IterationState> log = new ArrayList<>();
    private double accuracy = 0.00000000001;

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
        StateLogger logger = new StateLogger(center, points, log);
        RadiusCondition rinv = new RadiusCondition(points, center, radius);
        rinv.setAccuracy(accuracy);
        TetrahedronCondition tinv = new TetrahedronCondition(top, triangle.getP1(), triangle.getP2(), triangle.getP3());
        tinv.setAccuracy(accuracy);
        double scaling = radius / 10;
        while(true) {
            triangle.move(scaling);
            if (!rinv.isFulfilled()) {
                throw new RuntimeException("Unexpected error");
            } else if (tinv.isFulfilled() || scaling <= accuracy) {
                break;
            } else if (tinv.firstDifference() < 0) {
                triangle.move(-scaling);
                scaling = scaling / 10;
            }
            logger.logState();
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

    @Override
    public List<IterationState> getStateLog() {
        return log;
    }

    @Override
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
    
}
