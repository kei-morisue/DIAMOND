/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.controller.Context;
import diamond.controller.action.screen.state.StateIdle;
import diamond.model.math.field.Real;
import diamond.view.resource.IconBuilder;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class ButtonStepAdd extends JButton {
    public static final int ADD = 1;
    public static final int DES = -1;

    public ButtonStepAdd(Context context, int op) {
        setBackground(Color.white);
        switch (op) {
        case ADD:
            IconBuilder.set(this, "insert.png");
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //                context.insertCp();
                    //TODO
                }
            });
            break;
        case DES:
            IconBuilder.set(this, "destroy.png");
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //                    Palette palette = context.getPalette();
                    //                    Vector<Cp> cps = palette.getCps();
                    //                    if (JOptionPane.showConfirmDialog(null,
                    //                            Labels.get("destroy_cp")) == 0
                    //                            && cps.size() != 1) {
                    //                        context.removeCp(context.getCp());
                    //                    }
                    //TODO
                }
            });
            break;
        default:
            setText(Labels.get("no_image"));
            break;
        }
        StateIdle<Real> l = new StateIdle<Real>(context);
        addKeyListener(l);
    }
}
