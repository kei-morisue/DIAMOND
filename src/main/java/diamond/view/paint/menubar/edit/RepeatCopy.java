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
public class RepeatCopy
        extends JMenuItem implements ActionListener {
    private static RepeatCopy instance = null;

    public static RepeatCopy getInstance() {
        if (instance == null) {
            instance = new RepeatCopy();
        }
        return instance;
    }

    public RepeatCopy() {
        super(ResourceHolder.getLabelString(LABEL.REPEAT_COPY));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Painter painter = new Painter();
        RepeatCopyDialog arrayCopyDialog = new RepeatCopyDialog(
                PaintFrame.getInstance());
        if (painter.countSelectedLines(
                DocHolder.getDoc().getCreasePattern()) == 0) {
            JOptionPane.showMessageDialog(this, "Select target lines",
                    ResourceHolder.getLabelString(
                            LABEL.REPEAT_COPY),
                    JOptionPane.WARNING_MESSAGE);

        } else {
            arrayCopyDialog.setVisible(true);
        }

    }

}
