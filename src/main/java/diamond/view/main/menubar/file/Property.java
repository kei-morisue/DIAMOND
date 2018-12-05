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
import diamond.view.main.PropertyDialog;

/**
 * @author long_
 *
 */
public class Property extends JMenuItem implements ActionListener {
    private static Property instance = null;

    public static Property getInstance() {
        if (instance == null) {
            instance = new Property();
        }
        return instance;
    }

    public Property() {
        super(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.Main.PROPERTY_ID));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PropertyDialog dialog = new PropertyDialog(MainFrame.getInstance());
        dialog.setValue();
        dialog.setLocation(
                (int) (MainFrame.getInstance().getX() + dialog.getWidth() / 2),
                (int) (MainFrame.getInstance().getY()
                        + dialog.getHeight() / 2));
        dialog.setModal(true);
        dialog.setVisible(true);

    }
}
