/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import diamond.model.geom.element.diagram.Diagram;

/**
 * @author long_
 *
 */
public class DiagramPanel extends JPanel {
    private Diagram diagram;
    private int stepNo;

    public DiagramPanel(Diagram diagram, int stepNo) {
        this.diagram = diagram;
        this.stepNo = stepNo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        setBorder(new LineBorder(Color.red));
        setBackground(Color.white);
        //        Graphics2D g2d = (Graphics2D) g;
        //        ScreenTransform transform = new ScreenTransform(diagram.getTransform());
        //                    transform.zoom(0.5);
        //                    g2d.setTransform(transform.getTransform());
        //OriModel model = diagram.getCp().getOriModel();
        //OriDrawer.drawModel(g2d, model);
        //OriDrawer.drawStepNo(g2d, stepNo + 1);
    }
}
