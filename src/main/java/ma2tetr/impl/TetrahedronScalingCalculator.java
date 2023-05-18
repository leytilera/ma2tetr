package ma2tetr.impl;

import ma2tetr.api.ITetrahedronCoordCalculator;
import ma2tetr.invariant.RadiusInvariant;
import ma2tetr.invariant.TetrahedronInvariant;
import ma2tetr.model.Coords3D;
import ma2tetr.model.Tetrahedron;
import ma2tetr.model.Vector3D;

public class TetrahedronScalingCalculator implements ITetrahedronCoordCalculator {

    private double radius = 1.0;
    private Tetrahedron tetrahedron = null;

    @Override
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculate() {
        Coords3D center = new Vector3D(0, 0, 0);
        tetrahedron = new Tetrahedron(0, radius, 0);
        TetrahedronInvariant tinv = new TetrahedronInvariant(tetrahedron);
        RadiusInvariant rinv = new RadiusInvariant(tetrahedron.getPointsSet(), center, radius);
        double scaling = radius / 10;
        while(true) {
            tetrahedron.scale(scaling);
            if (!tinv.isFulfilled()) {
                throw new RuntimeException("Unexpected error");
            } else if (rinv.isFulfilled()) {
                break;
            } else if (rinv.firstDifference() < 0) {
                tetrahedron.scale(-scaling);
                scaling = scaling / 10;
            }
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
    
}
