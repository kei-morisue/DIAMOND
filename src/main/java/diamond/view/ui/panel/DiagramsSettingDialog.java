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

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.Palette;
import diamond.controller.paint.context.PointedElement;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.Diagram;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;
import diamond.model.geom.element.diagram.arrow.RepeatArrow;
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
    private OriLine oriLine;
    private Context context;

    public DiagramsSettingDialog(Context context) {
        i0 = new JComboBox<Integer>();
        i1 = new JComboBox<Integer>();
        Palette palette = context.getPalette();
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

        modelScreen0 = new ModelScreen(new Context(palette));
        modelScreen1 = new ModelScreen(new Context(palette));
        panel0.add(modelScreen0, BorderLayout.CENTER);
        panel1.add(modelScreen1, BorderLayout.CENTER);

        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        oriLine = pointedElements.getOriLine();
        this.context = context;
    }

    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int s0 = (int) i0.getSelectedItem();
            int s1 = (int) i1.getSelectedItem();

            Context c0 = modelScreen0.getContext();
            Context c1 = modelScreen1.getContext();
            c0.getPalette().setStepNo(s0, c0);
            c1.getPalette().setStepNo(s1, c1);
        }

    }

    public void showDialog() {
        JOptionPane option = new JOptionPane(this, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null);
        AbstractArrow newArrow = new RepeatArrow();
        JDialog dialog = option.createDialog(null, "Select Repeat Range");
        dialog.setSize(new Dimension(800, 800));
        dialog.setVisible(true);
        oriLine.setArrow(newArrow);
        oriLine.getHe().setArrow(newArrow);
    }

}
