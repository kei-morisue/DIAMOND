/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import java.awt.Graphics2D;
import java.util.List;

import javax.swing.border.BevelBorder;

import diamond.model.geom.element.origami.OriModel;
import diamond.view.screen.draw.OriModelDrawer;

/**
 * @author long_
 *
 */
public class Goal extends AbstractStep {
    public Goal(List<Diagram> diagrams) {
        super(diagrams.get(diagrams.size() - 1));
        setBorder(new BevelBorder(BevelBorder.RAISED));

    }

    @Override
    protected void draw(Graphics2D g2d) {
        OriModel model = diagram.getCp().getOriModel();
        OriModelDrawer.drawModel(g2d, model);
    }
}
