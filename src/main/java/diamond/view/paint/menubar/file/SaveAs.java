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
import diamond.file.FileFilterEx;
import diamond.file.FileHistory;
import diamond.file.FileIOUtil;
import diamond.file.FilterDB;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey.LABEL;
import diamond.view.paint.PaintFrame;
import diamond.view.paint.menubar.MenuFile;

/**
 * @author long_
 *
 */
public class SaveAs extends JMenuItem implements ActionListener {
    private static SaveAs instance = null;

    public static SaveAs getInstance() {
        if (instance == null) {
            instance = new SaveAs();
        }
        return instance;
    }

    private static FilterDB filterDB = FilterDB.getInstance();

    private static FileFilterEx[] fileFilters = new FileFilterEx[] {

            filterDB.getFilter("opx"), filterDB.getFilter("pict") };

    private SaveAs() {
        super(ResourceHolder.getLabelString(LABEL.SAVE_AS));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String dataFileName = DocHolder.getDoc().getDataFileName();
        String path = FileIOUtil.exportFile(FileHistory.getLastDirectory(),
                dataFileName,
                fileFilters);
        DocHolder.getDoc().setDataFilePath(path);
        MenuFile.getInstance().updateMRUItems(path);
        PaintFrame.getInstance().setTitle(DocHolder.getDoc().getDataFileName());
    }

}
