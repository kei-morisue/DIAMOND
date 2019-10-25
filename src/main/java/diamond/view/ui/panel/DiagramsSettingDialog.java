/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import diamond.Initials;
import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.Palette;
import diamond.model.geom.element.diagram.Diagram;
import diamond.view.screen.ModelScreen;

/**
 * @author long_
 *
 */
public class DiagramsSettingDialog extends JPanel {

    private JComboBox<Integer> i0;
    private ModelScreen modelScreen0;
    private JComboBox<Integer> i1;
    private ModelScreen modelScreen1;
    private Palette palette0;
    private Palette palette1;

    public DiagramsSettingDialog(Context context) {
        i0 = new JComboBox<Integer>();
        i1 = new JComboBox<Integer>();
        Palette palette = context.getPalette();
        this.palette0 = new Palette(palette);
        this.palette1 = new Palette(palette);

        Vector<Diagram> diagrams = palette.getDiagrams();
        for (int i = 0; i < diagrams.size(); i++) {
            i0.addItem(i + 1);
            i1.addItem(i + 1);
        }
        i0.addActionListener(new Action());
        i1.addActionListener(new Action());

        setLayout(new GridLayout(1, 2));
        JPanel panel0 = new JPanel();
        JPanel panel1 = new JPanel();

        panel0.setLayout(new BorderLayout());
        panel1.setLayout(new BorderLayout());

        add(panel0);
        add(panel1);

        panel0.add(i0, BorderLayout.NORTH);
        panel1.add(i1, BorderLayout.NORTH);

        modelScreen0 = new ModelScreen(new Context(palette0));
        modelScreen1 = new ModelScreen(new Context(palette1));

        panel0.add(modelScreen0, BorderLayout.CENTER);
        panel1.add(modelScreen1, BorderLayout.CENTER);

    }

    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int s0 = (int) i0.getSelectedItem() - 1;
            int s1 = (int) i1.getSelectedItem() - 1;
            Context c0 = modelScreen0.getContext();
            Context c1 = modelScreen1.getContext();
            palette0.setStepNo(s0, c0);
            palette1.setStepNo(s1, c1);
            modelScreen0.repaint();
            modelScreen1.repaint();
        }
    }

    public void showDialog() {
        JOptionPane option = new JOptionPane(this, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null);
        JDialog dialog = option.createDialog(null, "Select Repeat Range");
        dialog.setSize(
                new Dimension(Initials.DIALOG_WIDTH, Initials.DIALOG_HEIGHT));
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
    }

}
