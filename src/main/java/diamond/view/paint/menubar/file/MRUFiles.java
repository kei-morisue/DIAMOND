/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import diamond.Config;
import diamond.doc.DocHolder;
import diamond.file.FileHistory;
import diamond.file.FileIOUtil;
import diamond.paint.core.PaintContext;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;
import diamond.view.MainFrame;
import diamond.viewsetting.paint.MainFrameSettingDB;

/**
 * @author long_
 *
 */
public class MRUFiles {

    public static JMenuItem[] menuItems;

    public MRUFiles(JMenu menu) {
        menuItems = new JMenuItem[Config.MRUFILE_NUM];
        FileHistory.loadFromFile(Config.INI_FILE_PATH);
        int i = 0;
        for (String path : FileHistory.getHistory()) {
            menuItems[i] = new JMenuItem(path);
            menu.add(MRUFiles.menuItems[i]);
            menuItems[i].addActionListener(new ActionPerformer(path));
            i++;
        }
    }

    private class ActionPerformer implements ActionListener {
        private String filePath;

        public ActionPerformer(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrame mainFrame = MainFrame.getInstance();
            try {
                MainFrameSettingDB.getInstance().notifyObservers();
                FileIOUtil.openFile(mainFrame, filePath);
                mainFrame.setTitle(DocHolder.getDoc().getDataFileName());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame,
                        e.toString(),
                        ResourceHolder
                                .getWarningString(
                                        StringID.Error.LOAD_FAIELD_ID),
                        JOptionPane.ERROR_MESSAGE);
            }
            PaintContext.getPainterScreen().repaint();
        }

    }

}
