/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.resource.StringID.Main;

/**
 * @author long_
 *
 */
public class MenuHelp extends JMenu implements ActionListener {
    public static final String itemAboutContent = "DIAMOND @Kei Morisue";
    public static JMenuItem menuItemAbout = new JMenuItem(
            ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                    Main.ABOUT_ID));

    public MenuHelp() {
        super(ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                StringID.Main.HELP_ID));
        menuItemAbout.addActionListener(this);
        add(menuItemAbout);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, itemAboutContent,
                ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                        StringID.Main.TITLE_ID),
                JOptionPane.INFORMATION_MESSAGE);
    }

}
