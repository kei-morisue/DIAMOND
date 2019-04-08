/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.ImageIconLoader;

/**
 * @author long_
 *
 */
public class DiagramInsertButton extends JButton {
    PaintContext paintContext;

    public DiagramInsertButton(PaintContext paintContext) {
        this.paintContext = paintContext;
        setBackground(Color.white);
        ImageIconLoader imgLoader = new ImageIconLoader();
        setIcon(imgLoader.loadAsIcon("icon/insert.gif"));
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                paintContext.palette.insertCp();
            }
        });
    }
}
