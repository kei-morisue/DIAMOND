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
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.MainFrame;
import diamond.view.paint.CircleCopyDialog;

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
        super(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.Main.CIRCLE_COPY_ID));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Painter painter = new Painter();
        CircleCopyDialog circleCopyDialog = new CircleCopyDialog(
                MainFrame.getInstance());
        if (painter.countSelectedLines(
                DocHolder.getDoc().getCreasePattern()) == 0) {
            JOptionPane.showMessageDialog(this, "Select target lines",
                    "ArrayCopy", JOptionPane.WARNING_MESSAGE);

        } else {
            circleCopyDialog.setVisible(true);
        }

    }

}
