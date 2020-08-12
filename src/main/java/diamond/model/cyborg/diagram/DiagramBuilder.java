/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram;

import java.util.LinkedList;

import diamond.Config;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.diagram.step.StepBuilder;

/**
 * @author Kei Morisue
 *
 */
public class DiagramBuilder {
    public static Diagram plane() {
        Diagram diagram = new Diagram();
        Step step0 = StepBuilder.step0(
                Config.PAPER_SIZE);
        diagram.getSteps().add(step0);
        return diagram;
    }

    public static Diagram craneBase() {
        Diagram diagram = new Diagram();
        LinkedList<Step> steps = diagram.getSteps();
        Step step0 = StepBuilder.step0(Config.PAPER_SIZE);
        steps.add(step0);
        steps.add(StepBuilder.squareBase(step0));
        steps.add(StepBuilder.craneBase(Config.PAPER_SIZE));
        return diagram;
    }

}
