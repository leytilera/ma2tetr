package ma2tetr.condition;

import ma2tetr.model.Coords3D;
import ma2tetr.model.Tetrahedron;

public class TetrahedronCondition extends AbstractCondition {

    private Coords3D top;
    private Coords3D p1;
    private Coords3D p2;
    private Coords3D p3;

    private double len1;
    private double len2;
    private double len3;
    private double lenbase;

    private EquilateralTriangleCondition base;

    public TetrahedronCondition(Coords3D top, Coords3D p1, Coords3D p2, Coords3D p3) {
        this.top = top;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.base = new EquilateralTriangleCondition(p1, p2, p3);
    }

    public TetrahedronCondition(Tetrahedron t) {
        this(t.getTop(), t.getP1(), t.getP2(), t.getP3());
    }

    private void recalcDistance() {
        len1 = top.distance(p1);
        len2 = top.distance(p2);
        len3 = top.distance(p3);
        lenbase = base.getDistance();
    }

    public boolean isFulfilled() {
        this.recalcDistance();
        return checkEqual(lenbase, len1) && checkEqual(lenbase, len2) && checkEqual(lenbase, len3);
    }

    public double firstDifference() {
        recalcDistance();
        if (!checkEqual(lenbase, len1)) {
            return len1 - lenbase;
        } else if (!checkEqual(lenbase, len2)) {
            return len2 - lenbase;
        } else if (!checkEqual(lenbase, len3)) {
            return len3 - lenbase;
        }
        return 0;
    }

    @Override
    public void setAccuracy(double accuracy) {
        base.setAccuracy(accuracy);
        super.setAccuracy(accuracy);
    }
    
}
