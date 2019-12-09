/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
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

import diamond.Config;
import diamond.controller.Context;
import diamond.controller.Palette;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.HalfEdge;
import diamond.model.symbol.arrow.ArrowRepeat;
import diamond.view.resource.string.Labels;
import diamond.view.ui.screen.FoldedScreen;

/**
 * @author Kei Morisue
 *
 */
public class SymbolRepeatDialog extends JPanel {

    private JComboBox<Integer> i0;
    private FoldedScreen screen0;
    private JComboBox<Integer> i1;
    private FoldedScreen screen1;
    private Context context;
    private Context context0;
    private Context context1;

    public SymbolRepeatDialog(Context context) {
        this.context = context;
        i0 = new JComboBox<Integer>();
        i1 = new JComboBox<Integer>();
        Palette palette = context.getPalette();
        Palette palette0 = new Palette(palette);
        Palette palette1 = new Palette(palette);

        Vector<Cp> diagrams = palette.getCps();
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

        context0 = new Context();
        context1 = new Context();
        context0.setPalette(palette0);
        context1.setPalette(palette1);

        screen0 = new FoldedScreen(context0);
        screen1 = new FoldedScreen(context1);
        panel0.add(screen0, BorderLayout.CENTER);
        panel1.add(screen1, BorderLayout.CENTER);

    }

    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int s0 = (int) i0.getSelectedItem() - 1;
            int s1 = (int) i1.getSelectedItem() - 1;
            context0.setCurrentStep(s0);
            context1.setCurrentStep(s1);
            screen0.repaint();
            screen1.repaint();
        }
    }

    public void showDialog() {
        JOptionPane option = new JOptionPane(this, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null);
        JDialog dialog = option.createDialog(null, Labels.get("start_to_end"));
        dialog.setSize(
                new Dimension(Config.DIALOG_WIDTH, Config.DIALOG_HEIGHT));
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);

        int s0 = (int) i0.getSelectedItem() - 1;
        int s1 = (int) i1.getSelectedItem() - 1;

        Vector<Cp> cps = context.getPalette().getCps();
        Cp cp0 = cps.get(s0);
        Cp cp1 = cps.get(s1);

        ArrowRepeat arrow = new ArrowRepeat(cp0, cp1, cps);
        HalfEdge he = context.getPicker().getHalfEdges().get(0);
        arrow.set(he);
        context.getCp().getSymbolsHalfEdge().put(he, arrow);
    }

}