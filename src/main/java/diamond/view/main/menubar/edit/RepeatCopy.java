/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import diamond.doc.DocHolder;
import diamond.paint.creasepattern.Painter;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;
import diamond.view.main.RepeatCopyDialog;

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
        super(ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                StringID.Main.REPEAT_COPY_ID));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Painter painter = new Painter();
        RepeatCopyDialog arrayCopyDialog = new RepeatCopyDialog(
                MainFrame.getInstance());
        if (painter.countSelectedLines(
                DocHolder.getInstance().getDoc().getCreasePattern()) == 0) {
            JOptionPane.showMessageDialog(this, "Select target lines",
                    ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                            StringID.Main.REPEAT_COPY_ID),
                    JOptionPane.WARNING_MESSAGE);

        } else {
            arrayCopyDialog.setVisible(true);
        }

    }

}
