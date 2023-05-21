package ma2tetr;

import ma2tetr.api.ITetrahedronCoordCalculator;
import ma2tetr.impl.TetrahedronMovingCalculator;
import ma2tetr.impl.TetrahedronScalingCalculator;
import ma2tetr.model.Tetrahedron;

public class App {

    public static void main(String[] args) {
        double radius;
        if (args.length != 2 && args.length != 3) {
            System.out.println("Arguments: <calculator> <radius> [<accuracy>]");
            System.out.println(" - calculator: either 'scaling' or 'moving'");
            System.out.println(" - radius: the sphere radius as a number");
            System.out.println(" - accuracy: the accuracy for double comparison");
            return;
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
        try {
            radius = Double.parseDouble(args[1]);
            if (args.length == 3) {
                calc.setAccuracy(Double.parseDouble(args[2]));
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid radius: " + args[1]);
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
