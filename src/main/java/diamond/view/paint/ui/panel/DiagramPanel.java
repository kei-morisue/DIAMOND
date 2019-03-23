/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.panel;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.cp.Cp;
import diamond.view.paint.ui.button.DiagramIcon;

/**
 * @author long_
 *
 */
public class DiagramPanel extends JScrollPane
        implements Observer {
    private LinkedList<DiagramIcon> diagramIcons = new LinkedList<>();
    private PaintContext paintContext;
    private JPanel panel;

    public DiagramPanel(JPanel panel, PaintContext paintContext) {
        super(panel);
        this.panel = panel;
        this.paintContext = paintContext;
        paintContext.palette.addObserver(this);
        initialize(panel, paintContext);
    }

    private void initialize(JPanel panel, PaintContext paintContext) {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        ButtonGroup buttonGroup = new ButtonGroup();
        diagramIcons.clear();
        LinkedList<Cp> creasePatterns = paintContext.palette
                .getCreasePatterns();
        for (Cp cp : creasePatterns) {
            DiagramIcon button = new DiagramIcon(paintContext.palette, cp);
            diagramIcons.add(button);
            button.setStep(diagramIcons.indexOf(button));
            panel.add(button);
            buttonGroup.add(button);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.initialize(panel, paintContext);
    }

}
