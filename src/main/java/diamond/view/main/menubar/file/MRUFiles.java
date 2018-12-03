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
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.view.main.MainFrame;

/**
 * @author long_
 *
 */
public class MRUFiles implements ActionListener {

    public static JMenuItem[] menuItems = new JMenuItem[Config.MRUFILE_NUM];

    public MRUFiles() {
        MainFrame mainFrame = MainFrame.getInstance();
        for (int i = 0; i < Config.MRUFILE_NUM; i++) {
            menuItems[i] = new JMenuItem();
            menuItems[i].addActionListener(mainFrame);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < Config.MRUFILE_NUM; i++) {
            if (e.getSource() == menuItems[i]) {
                MainFrame mainScreen = MainFrame.getInstance();
                try {
                    String filePath = menuItems[i]
                            .getText();
                    mainScreen.openFile(filePath);
                    mainScreen.updateTitleText();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainScreen,
                            e.toString(),
                            ResourceHolder.getInstance().getString(
                                    ResourceKey.WARNING,
                                    "Error_FileLoadFailed"),
                            JOptionPane.ERROR_MESSAGE);
                }
                mainScreen.getMainScreen().repaint();
                return;
            }
        }
    }
}
