/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.IconSetter;

/**
 * @author long_
 *
 */
public class buildPagesButton extends JButton {
    PaintContext paintContext;

    public buildPagesButton(PaintContext paintContext) {
        setBackground(Color.white);
        IconSetter.set(this, "build.gif");
        this.paintContext = paintContext;
        this.paintContext = paintContext;
        setMnemonic(KeyEvent.VK_B);
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "WIP");//TODO Do it

            }
        });
    }
}
