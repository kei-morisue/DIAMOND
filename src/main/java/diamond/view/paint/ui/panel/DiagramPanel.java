/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.panel;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import diamond.controller.paint.PaintContext;
import diamond.view.paint.ui.DiagramIcon;

/**
 * @author long_
 *
 */
public class DiagramPanel extends JScrollPane implements Observer {
    LinkedList<DiagramIcon> diagramIcons = new LinkedList<>();
    PaintContext paintContext;
    ButtonGroup buttonGroup = new ButtonGroup();
    JPanel panel;

    public DiagramPanel(JPanel panel, PaintContext paintContext) {
        super(panel);
        this.panel = panel;
        this.paintContext = paintContext;
        paintContext.addObserver(this);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        initialize(panel, paintContext, buttonGroup);
    }

    private void initialize(JPanel panel, PaintContext paintContext,
            ButtonGroup buttonGroup) {
        for (int i = 0; i < paintContext.palette.getCreasePatterns()
                .size(); ++i) {
            DiagramIcon button = new DiagramIcon();
            button.setPreferredSize(new Dimension(100, 100));
            diagramIcons.add(button);
            int step = diagramIcons.indexOf(button);
            button.setText(String.valueOf(step));
            panel.add(button);
            buttonGroup.add(button);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.initialize(panel, paintContext, buttonGroup);
    }
}
