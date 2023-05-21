package ma2tetr;

import ma2tetr.api.ITetrahedronCoordCalculator;
import ma2tetr.impl.TetrahedronMovingCalculator;
import ma2tetr.impl.TetrahedronScalingCalculator;
import ma2tetr.model.Tetrahedron;

public class App {

    public static void main(String[] args) {
        double radius;
        if (args.length != 2) {
            System.out.println("Required arguments: <calculator> <radius>");
            System.out.println(" - calculator: either 'scaling' or 'moving'");
            System.out.println(" - radius: the sphere radius as a number");
            return;
        }
        try {
            radius = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid radius: " + args[1]);
        }
        ITetrahedronCoordCalculator calc;
        switch(args[0]) {
            case "scaling":
                calc = new TetrahedronScalingCalculator();
                break;
            case "moving":
                calc = new TetrahedronMovingCalculator();
                break;
            default:
                throw new RuntimeException("Invalid calculator: " + args[0]);
        }
        calc.setRadius(radius);
        calc.calculate();
        Tetrahedron t = calc.getTetrahedron();
        System.out.println(t.getTop());
        System.out.println(t.getP1());
        System.out.println(t.getP2());
        System.out.println(t.getP3());
        System.out.println("Iterations needed: " + calc.getStateLog().size());
    }
}
