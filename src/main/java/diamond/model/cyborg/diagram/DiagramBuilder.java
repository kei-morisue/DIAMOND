/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram;

import java.util.LinkedList;

import diamond.Config;
import diamond.model.cyborg.step.Step;
import diamond.model.cyborg.step.StepBuilder;

/**
 * @author Kei Morisue
 *
 */
public class DiagramBuilder {
    public static Diagram plane() {
        Diagram diagram = new Diagram();
        Step step0 = StepBuilder.step0(
                Config.PAPER_SIZE,
                diagram.getStars());
        diagram.getSteps().add(step0);
        return diagram;
    }

    public static Diagram craneBase() {
        Diagram diagram = new Diagram();
        Stars stars = diagram.getStars();
        LinkedList<Step> steps = diagram.getSteps();
        Step step0 = StepBuilder.step0(Config.PAPER_SIZE, stars);
        steps.add(step0);
        steps.add(StepBuilder.step1(step0, stars));
        steps.add(StepBuilder.craneBase(Config.PAPER_SIZE, stars));
        return diagram;
    }

}
