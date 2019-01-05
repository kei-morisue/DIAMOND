/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import diamond.doc.DocHolder;
import diamond.paint.creasepattern.Painter;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey.LABEL;
import diamond.view.paint.PaintFrame;

/**
 * @author long_
 *
 */
public class CircleCopy
        extends JMenuItem implements ActionListener {
    private static CircleCopy instance = null;

    public static CircleCopy getInstance() {
        if (instance == null) {
            instance = new CircleCopy();
        }
        return instance;
    }

    public CircleCopy() {
        super(ResourceHolder.getLabelString(LABEL.CIRCLE_COPY));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Painter painter = new Painter();
        CircleCopyDialog circleCopyDialog = new CircleCopyDialog(
                PaintFrame.getInstance());
        if (painter.countSelectedLines(
                DocHolder.getDoc().getCreasePattern()) == 0) {
            JOptionPane.showMessageDialog(this, "Select target lines",
                    "ArrayCopy", JOptionPane.WARNING_MESSAGE);

        } else {
            circleCopyDialog.setVisible(true);
        }

    }

}
