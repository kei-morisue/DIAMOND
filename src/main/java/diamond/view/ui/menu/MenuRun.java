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
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class MenuRun extends JMenu {
    public MenuRun(PaintContext paintContext) {
        super(ResourceHolder.getLabelString(LABEL.RUN));
        add(buildPreview(paintContext));
    }

    public JMenuItem buildPreview(PaintContext paintContext) {
        JMenuItem item = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.PREVIEW));
        item.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "WIP");//TODO Do it

            }
        });
        return item;
    }
}
