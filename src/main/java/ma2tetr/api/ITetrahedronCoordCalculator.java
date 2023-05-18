package ma2tetr.api;

import ma2tetr.model.Tetrahedron;

public interface ITetrahedronCoordCalculator {

    void setRadius(double radius);

    double getRadius();

    void calculate();

    Tetrahedron getTetrahedron();
    
}
