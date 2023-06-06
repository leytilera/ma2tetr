package ma2tetr.impl;

import java.util.ArrayList;
import java.util.List;

import ma2tetr.api.ITetrahedronCoordCalculator;
import ma2tetr.condition.RadiusCondition;
import ma2tetr.condition.TetrahedronCondition;
import ma2tetr.model.Coords3D;
import ma2tetr.model.IterationState;
import ma2tetr.model.Point3D;
import ma2tetr.model.Tetrahedron;
import ma2tetr.model.Vector3D;

public class TetrahedronScalingCalculator implements ITetrahedronCoordCalculator {

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
        tetrahedron = new Tetrahedron(new Point3D(0, radius, 0), accuracy);
        StateLogger logger = new StateLogger(center, tetrahedron.getPointsSet(), log);
        TetrahedronCondition tinv = new TetrahedronCondition(tetrahedron);
        tinv.setAccuracy(accuracy);
        RadiusCondition rinv = new RadiusCondition(tetrahedron.getPointsSet(), center, radius);
        rinv.setAccuracy(accuracy);
        double scaling = radius / 10;
        while(true) {
            tetrahedron.scale(scaling);
            if (!tinv.isFulfilled()) {
                throw new RuntimeException("Unexpected error");
            } else if (rinv.isFulfilled() || scaling < accuracy) {
                break;
            } else if (rinv.firstDifference() < 0) {
                tetrahedron.scale(-scaling);
                scaling = scaling / 10;
            }
            logger.logState();
        }
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
