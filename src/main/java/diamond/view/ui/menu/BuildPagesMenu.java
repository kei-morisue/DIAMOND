/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import diamond.controller.paint.PaintContext;

/**
 * @author long_
 *
 */
public class BuildPagesMenu extends JMenu {
    public BuildPagesMenu(PaintContext paintContext) {
        super("Run");
        add(buildRun(paintContext));
    }

    public JMenuItem buildRun(PaintContext paintContext) {
        JMenuItem item = new JMenuItem("Run");
        item.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "WIP");//TODO Do it

            }
        });
        return item;
    }
}
