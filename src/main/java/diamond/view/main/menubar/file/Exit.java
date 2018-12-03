/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;

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

    public Exit() {
        super(ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                StringID.Main.EXIT_ID));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().saveIniFile();
        System.exit(0);
    }
}
