import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();

    }

    public static void task1() {
        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[]{15, 21, 30, 44, 6}, 0);//стоимость 100г продукта
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>(); //Ограничения
        constraints.add((new LinearConstraint(new double[]{12.07, 26.3, 23, 13.8, 12.6}, Relationship.GEQ, 60))); //Ограничение нормы белков
        constraints.add((new LinearConstraint(new double[]{11.5, 45.2, 1.2, 61.3, 2.6}, Relationship.GEQ, 70))); //жиры
        constraints.add((new LinearConstraint(new double[]{0.7, 9.7, 53.3, 10.2, 68}, Relationship.GEQ, 280)));//углеводы
        constraints.add((new LinearConstraint(new double[]{157, 550, 316, 647, 345}, Relationship.GEQ, 1826)));//норма каллорий

        constraints.add((new LinearConstraint(new double[]{1, 0, 0, 0, 0}, Relationship.LEQ, 3)));
        constraints.add((new LinearConstraint(new double[]{0, 1, 0, 0, 0}, Relationship.LEQ, 3)));
        constraints.add((new LinearConstraint(new double[]{0, 0, 1, 0, 0}, Relationship.LEQ, 3)));
        constraints.add((new LinearConstraint(new double[]{0, 0, 0, 1, 0}, Relationship.LEQ, 3)));
        constraints.add((new LinearConstraint(new double[]{0, 0, 0, 0, 1}, Relationship.LEQ, 3)));

        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(new MaxIter(1000), f, new LinearConstraintSet(constraints), GoalType.MINIMIZE, new NonNegativeConstraint(true));

        double fs = solution.getValue();
        System.out.println(fs);

        double[] fx = solution.getPoint();
        for (int i = 0; i < fx.length; i++) {
            System.out.println(fx[i]);
        }
    }

    public static void task2() {
        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[]{1894, 1552, 3026, 2352, 1748, 3558, 2630, 2262, 3358}, 0);
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>(); //Ограничения
        constraints.add((new LinearConstraint(new double[]{1, 1, 1, 0, 0, 0, 0, 0, 0}, Relationship.EQ, 1000)));
        constraints.add((new LinearConstraint(new double[]{0, 0, 0, 1, 1, 1, 0, 0, 0}, Relationship.EQ, 2000)));
        constraints.add((new LinearConstraint(new double[]{0, 0, 0, 0, 0, 0, 1, 1, 1}, Relationship.EQ, 1200)));
        constraints.add((new LinearConstraint(new double[]{1, 0, 0, 1, 0, 0, 1, 0, 0}, Relationship.EQ, 2000)));
        constraints.add((new LinearConstraint(new double[]{0, 1, 0, 0, 1, 0, 0, 1, 0}, Relationship.EQ, 1100)));
        constraints.add((new LinearConstraint(new double[]{0, 0, 1, 0, 0, 1, 0, 0, 1}, Relationship.EQ, 1100)));

        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(new MaxIter(1000), f, new LinearConstraintSet(constraints), GoalType.MINIMIZE, new NonNegativeConstraint(true));

        double fs = solution.getValue();
        System.out.println(fs);

        double[] fx = solution.getPoint();
        for (int i = 0; i < fx.length; i++) {
            System.out.println(fx[i]);
        }
    }
}

