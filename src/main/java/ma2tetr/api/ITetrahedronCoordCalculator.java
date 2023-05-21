package ma2tetr.api;

import java.util.List;

import ma2tetr.model.IterationState;
import ma2tetr.model.Tetrahedron;

public interface ITetrahedronCoordCalculator {

    void setRadius(double radius);

    double getRadius();

    void calculate();

    Tetrahedron getTetrahedron();

    List<IterationState> getStateLog();

    void setAccuracy(double accuracy);
    
}
