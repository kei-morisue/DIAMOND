/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import diamond.Config;
import diamond.file.FileIOUtil;
import diamond.paint.core.PaintContext;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.view.estimation.EstimationResultFrame;
import diamond.view.main.MainFrame;
import diamond.view.model.ModelViewFrame;
import diamond.viewsetting.main.MainFrameSettingDB;

/**
 * @author long_
 *
 */
public class MRUFiles implements ActionListener {

    public static JMenuItem[] menuItems = new JMenuItem[Config.MRUFILE_NUM];

    public MRUFiles() {
        for (int i = 0; i < Config.MRUFILE_NUM; i++) {
            menuItems[i] = new JMenuItem();
            menuItems[i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < Config.MRUFILE_NUM; i++) {
            if (e.getSource() == menuItems[i]) {
                MainFrame mainFrame = MainFrame.getInstance();
                try {
                    String filePath = menuItems[i]
                            .getText();
                    MainFrameSettingDB.getInstance().notifyObservers();
                    ModelViewFrame.getInstance().setVisible(false);
                    EstimationResultFrame.getInstance().setVisible(false);
                    FileIOUtil.openFile(mainFrame, filePath);
                    mainFrame.updateTitleText();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainFrame,
                            e.toString(),
                            ResourceHolder.getString(
                                    ResourceKey.WARNING,
                                    "Error_FileLoadFailed"),
                            JOptionPane.ERROR_MESSAGE);
                }
                PaintContext.getPainterScreen().repaint();
                return;
            }
        }
    }
}
