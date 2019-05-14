/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.geom.element.origami.OriModel;
import diamond.view.screen.draw.OriModelDrawer;

/**
 * @author long_
 *
 */
public class Goal extends AbstractStep {
    private Diagram diagram;

    public Goal(Diagram diagram, int stepNo) {
        super(diagram, stepNo);
        this.diagram = diagram;
        setBackground(Color.white);
    }

    @Override
    protected void draw(Graphics2D g2d) {
        OriModel model = diagram.getCp().getOriModel();
        OriModelDrawer.drawModel(g2d, model);
    }
}
