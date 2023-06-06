package ma2tetr.condition;

import java.util.Set;

import ma2tetr.model.Coords3D;

public class RadiusCondition extends AbstractCondition {

    private Set<Coords3D> points;
    private Coords3D center;
    private double radius;

    public RadiusCondition(Set<Coords3D> points, Coords3D center, double radius) {
        this.points = points;
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean isFulfilled() {
        for (Coords3D point : points) {
            double len = center.distance(point);
            if (!checkEqual(len, radius)) {
                return false;
            }
        }
        return true;
    }    

    public double firstDifference() {
        for (Coords3D point : points) {
            double len = center.distance(point);
            if (!checkEqual(len, radius)) {
                return radius - len;
            }
        }
        return 0.0;
    }
    
}
