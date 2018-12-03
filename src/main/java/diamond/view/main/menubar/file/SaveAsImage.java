/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import diamond.doc.DocHolder;
import diamond.file.FileFilterEx;
import diamond.file.FileHistory;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;

/**
 * @author long_
 *
 */
public class SaveAsImage extends JMenuItem implements ActionListener {
    private static SaveAsImage instance = null;

    public static SaveAsImage getInstance() {
        if (instance == null) {
            instance = new SaveAsImage();
        }
        return instance;
    }

    public SaveAsImage() {
        super(ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                StringID.Main.SAVE_AS_IMAGE_ID));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().saveFile(FileHistory.getLastDirectory(),
                DocHolder.getInstance().getDoc().getDataFileName(),
                new FileFilterEx[] { MainFrame.filterDB.getFilter("pict") });
    }
}
