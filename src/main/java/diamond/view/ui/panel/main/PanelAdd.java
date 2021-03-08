/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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

    private class Consor extends JPanel implements Observer {
        private JLabel l0 = new JLabel();
        private JLabel l1 = new JLabel();
        private JLabel l2 = new JLabel();

        public Consor() {
            super();
            setBackground(Color.WHITE);
            setLayout(new GridLayout(1, 3));
            context.addObserver(this);
            add(l0);
            add(l1);
            add(l2);
        }

        @Override
        public void update(Observable o, Object arg) {
            //            l0.setText(context.getMouseLocation().toString());
            //            l1.setText(context.getPaintAction().getInfo());
        }
    }

}
