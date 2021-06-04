/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import defox.Config;
import diamond.controller.Context;
import diamond.view.ui.button.ButtonStep;

/**
 * @author Kei Morisue
 *
 */
public class PanelStep extends JPanel {
    private Context context;

    public PanelStep(Context context) {
        this.context = context;
        setLayout(new BorderLayout());
        add(new ButtonStep(ButtonStep.BOTTOM, context),
                BorderLayout.WEST);
        add(new ButtonStep(ButtonStep.TOP, context),
                BorderLayout.EAST);
        add(buildCenter(), BorderLayout.CENTER);
    }

    private JPanel buildCenter() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new ButtonStep(ButtonStep.PREV, context));
        panel.add(new LabelStep());
        panel.add(new JLabel(" of "));
        panel.add(new Steps());
        panel.add(new ButtonStep(ButtonStep.NEXT, context));
        return panel;
    }

    private String lastStep() {
        int lastStep = context.getStepIdndex() + 1;
        return String.valueOf(lastStep);
    }

    private String steps() {
        int steps = context.getDiagram().getSteps().size();
        return String.valueOf(steps);
    }

    private class LabelStep extends JLabel implements Observer {
        public LabelStep() {
            super(lastStep());
            setFont(new Font(Config.FONT, Font.BOLD, 30));
            context.getDiagram().addObserver(this);
        }

        @Override
        public void update(Observable o, Object arg) {
            setText(lastStep());
        }
    }

    private class Steps extends JLabel implements Observer {
        public Steps() {
            super(steps());
            setFont(new Font(Config.FONT, Font.BOLD, 30));
            context.getDiagram().addObserver(this);
        }

        @Override
        public void update(Observable o, Object arg) {
            setText(steps());
        }
    }
}
