/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram;

import diamond.Config;
import diamond.model.cyborg.step.StepBuilder;

/**
 * @author Kei Morisue
 *
 */
public class DiagramBuilder {
    public static Diagram plane() {
        Diagram diagram = new Diagram();
        diagram.getSteps().add(StepBuilder.step0(Config.PAPER_SIZE));
        return diagram;
    }

    public static Diagram craneBase() {
        Diagram diagram = new Diagram();
        diagram.getSteps().add(StepBuilder.craneBase(Config.PAPER_SIZE));
        return diagram;
    }

}
