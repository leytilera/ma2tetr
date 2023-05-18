package ma2tetr.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ma2tetr.model.Coords3D;
import ma2tetr.model.IterationState;
import ma2tetr.model.Vector3D;

public class StateLogger {

    private Coords3D center;
    private Set<Coords3D> points;
    private List<IterationState> log;

    public StateLogger(Coords3D center, Set<Coords3D> points, List<IterationState> log) {
        this.center = center;
        this.points = points;
        this.log = log;
    }

    public void logState() {
        Set<Coords3D> coords = new HashSet<>();
        for (Coords3D point : points) {
            coords.add(Vector3D.createFromPoints(center, point));
        }
        log.add(new IterationState(coords));
    }
    
}
