/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.controller.paint.context.Context;
import diamond.view.resource.IconSetter;

/**
 * @author long_
 *
 */
public class InsertCpButton extends JButton {
    public InsertCpButton(Context context) {
        setBackground(Color.white);
        IconSetter.set(this, "insert.png");
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                context.getPalette().insertCp();
            }
        });
    }
}
