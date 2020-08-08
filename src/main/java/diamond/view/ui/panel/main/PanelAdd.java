/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.ui.button.ButtonStepAdd;

/**
 * @author Kei Morisue
 *
 */
public class PanelAdd extends JPanel {
    private Context context;

    public PanelAdd(Context context) {
        this.context = context;
        setLayout(new BorderLayout());
        add(new ButtonStepAdd(context, ButtonStepAdd.ADD), BorderLayout.EAST);
        add(new Consor(), BorderLayout.CENTER);
        add(new ButtonStepAdd(context, ButtonStepAdd.DES), BorderLayout.WEST);
    }

    private class Consor extends JLabel implements Observer {
        public Consor() {
            super("Consor");
            setBackground(Color.RED);
            context.addObserver(this);
        }

        @Override
        public void update(Observable o, Object arg) {
            setText(context.getMouseLocation().toString());
        }
    }

}
