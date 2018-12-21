/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;
import diamond.view.MainFrame;

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
        super(ResourceHolder.getLabelString(StringID.Main.PROPERTY_ID));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PropertyDialog dialog = new PropertyDialog(MainFrame.getInstance());
        dialog.setValue();
        dialog.setLocation(
                MainFrame.getInstance().getX() + dialog.getWidth() / 2,
                MainFrame.getInstance().getY()
                        + dialog.getHeight() / 2);
        dialog.setModal(true);
        dialog.setVisible(true);

    }
}