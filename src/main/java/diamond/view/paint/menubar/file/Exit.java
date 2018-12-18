/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import diamond.Config;
import diamond.doc.DocHolder;
import diamond.file.FileHistory;
import diamond.file.FileIOUtil;
import diamond.file.FilterDB;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;

/**
 * @author long_
 *
 */
public class Exit extends JMenuItem implements ActionListener {
    private static Exit instance = null;

    public static Exit getInstance() {
        if (instance == null) {
            instance = new Exit();
        }
        return instance;
    }

    private Exit() {
        super(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.Main.EXIT_ID));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (DocHolder.getDoc().isChanged()) {
            // TODO: confirm saving edited opx
            int selected = JOptionPane
                    .showConfirmDialog(
                            this,
                            "The crease pattern has been modified. Would you like to save?",
                            "Comfirm to save", JOptionPane.YES_NO_OPTION);
            if (selected == JOptionPane.YES_OPTION) {
                String path = FileIOUtil.exportFile(
                        FileHistory.getLastDirectory(),
                        DocHolder.getDoc().getDataFileName(),
                        FilterDB.getInstance().toArray());
                if (path == null) {

                }
            }
        }
        FileHistory.saveToFile(Config.INI_FILE_PATH);
        System.exit(0);
    }
}
