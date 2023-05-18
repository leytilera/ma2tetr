package ma2tetr.invariant;

import ma2tetr.model.Coords3D;

public class EquilateralTriangleInvariant extends AbstractInvariant {
    
    private Coords3D p1;
    private Coords3D p2;
    private Coords3D p3;

    private double len12;
    private double len23;
    private double len13;

    public EquilateralTriangleInvariant(Coords3D p1, Coords3D p2, Coords3D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    private void recalcDistance() {
        len12 = p1.distance(p2);
        len23 = p2.distance(p3);
        len13 = p1.distance(p3);
    }

    @Override
    public boolean isFulfilled() {
        this.recalcDistance();
        return checkEqual(len12, len23) && checkEqual(len12, len13);
    }

    public double getDistance() {
        return this.isFulfilled() ? len12 : -1;
    }

}
