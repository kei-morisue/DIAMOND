/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import diamond.model.geom.element.diagram.Diagram;
import diamond.model.geom.element.origami.OriModel;
import diamond.view.screen.ScreenTransform;
import diamond.view.screen.draw.OriModelDrawer;
import diamond.view.screen.draw.StringDrawer;
import diamond.view.screen.draw.style.FontStyle;

/**
 * @author long_
 *
 */
public class DiagramPanel extends JPanel {
    private Diagram diagram;
    private int stepNo;

    public DiagramPanel(Diagram diagram, int stepNo) {
        super();
        this.diagram = diagram;
        this.stepNo = stepNo;
        setBorder(new LineBorder(Color.black));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        ScreenTransform transform = new ScreenTransform(diagram.getTransform());
        setBackground(Color.white);
        transform.focus(getWidth(), getHeight());
        transform.zoom(0.3);

        AffineTransform transform2 = g2d.getTransform();
        int x = (int) transform2.getTranslateX();
        int y = (int) transform2.getTranslateY();
        transform2.concatenate(transform.getTransform());
        g2d.setTransform(transform2);
        OriModel model = diagram.getCp().getOriModel();
        OriModelDrawer.drawModel(g2d, model);
        StringDrawer.drawStepNo(
                g2d,
                stepNo + 1,
                FontStyle.DIAGRAM_STEP_NO, x, y);
        g2d.dispose();
    }
}
