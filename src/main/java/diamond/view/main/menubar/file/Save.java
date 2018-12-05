/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.doc.DocHolder;
import diamond.file.FilterDB;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.menubar.MenuFile;

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
        super(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.Main.SAVE_ID));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = DocHolder.getDoc().getDataFilePath();
        if (!filePath.equals("")) {
            FilterDB.getInstance().getFilter("opx").getSavingAction()
                    .save(filePath);
        } else {
            SaveAs.getInstance().doClick();
        }
        MenuFile.getInstance().updateMRUItems(filePath);
    }
}
