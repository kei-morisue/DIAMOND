/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import diamond.controller.paint.context.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.screen.draw.style.StyleFrame;

/**
 * @author long_
 *
 */
public class MenuOprtion extends JMenu {
    public MenuOprtion(PaintContext paintContext) {
        super(ResourceHolder.getLabelString(LABEL.OPTION));
        JMenuItem style = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.STYLE));
        add(style);
        style.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StyleFrame();
            }
        });
    }
}
