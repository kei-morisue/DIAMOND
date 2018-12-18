/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.doc.DocHolder;
import diamond.file.FilterDB;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;
import diamond.view.paint.menubar.MenuFile;

/**
 * @author long_
 *
 */
public class Save extends JMenuItem implements ActionListener {
    private static Save instance = null;

    public static Save getInstance() {
        if (instance == null) {
            instance = new Save();
        }
        return instance;
    }

    private Save() {
        super(ResourceHolder.getLabelString(StringID.Main.SAVE_ID));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = DocHolder.getDoc().getDataFilePath();
        if (filePath != null) {
            FilterDB.getInstance().getFilter("opx").getSavingAction()
                    .save(filePath);
            MenuFile.getInstance().updateMRUItems(filePath);
            diamond.view.MainFrame.getInstance()
                    .setTitle(DocHolder.getDoc().getDataFileName());
        } else {
            SaveAs.getInstance().doClick();
        }
    }
}
