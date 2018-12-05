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
import diamond.file.FileFilterEx;
import diamond.file.FileHistory;
import diamond.file.FileIOUtil;
import diamond.file.FilterDB;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;
import diamond.view.main.menubar.MenuFile;

/**
 * @author long_
 *
 */
public class SaveAs extends JMenuItem implements ActionListener {
    private static SaveAs instance = null;
    private static FilterDB filterDB = FilterDB.getInstance();

    private static FileFilterEx[] fileFilters = new FileFilterEx[] {

            filterDB.getFilter("opx"), filterDB.getFilter("pict") };

    public static SaveAs getInstance() {
        if (instance == null) {
            instance = new SaveAs();
        }
        return instance;
    }

    public SaveAs() {
        super(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.Main.SAVE_AS_ID));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = FileIOUtil.exportFile(FileHistory.getLastDirectory(),
                DocHolder.getDoc().getDataFileName(),
                fileFilters);
        MenuFile.getInstance().updateMRUItems(path);
        MainFrame.getInstance().updateTitleText();
    }

}
