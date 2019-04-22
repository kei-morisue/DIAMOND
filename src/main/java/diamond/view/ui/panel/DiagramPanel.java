/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.Color;

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

        setBorder(new LineBorder(Color.red));
        //add(new JLabel(String.valueOf(stepNo + 1)));
        setBackground(Color.white);
    }
    //TODO repaint models fairly
    //    @Override
    //    protected void paintComponent(Graphics g) {
    //        Graphics2D g2d = (Graphics2D) g;
    //        ScreenTransform transform = new ScreenTransform(diagram.getTransform());
    //        transform.zoom(0.5);
    //        g2d.setTransform(transform.getTransform());
    //        //OriModel model = diagram.getCp().getOriModel();
    //        //OriDrawer.drawModel(g2d, model);
    //        //OriDrawer.drawStepNo(g2d, stepNo + 1);
    //    }
}
