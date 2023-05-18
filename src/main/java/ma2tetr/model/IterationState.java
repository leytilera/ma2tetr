package ma2tetr.model;

import java.util.Set;

public class IterationState {
    
    Set<Coords3D> points;

    public IterationState(Set<Coords3D> points) {
        this.points = points;
    }

    public Set<Coords3D> getPoints() {
        return points;
    }

}
