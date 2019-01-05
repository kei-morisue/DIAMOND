/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar.help;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import diamond.Version;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class About extends JMenuItem implements ActionListener {
    private static About instance = null;

    public static About getInstance() {
        if (instance == null) {
            instance = new About();
        }
        return instance;
    }

    private static final String content = "DIAMOND: (c) 2018 Kei Morisue\n"
            + "Being Developed based on:\n"
            + "ORIPA S: (c) 2012 OUCHI Koji\n" +
            "http://github.com/Ooouch1\n" +
            "ORIPA: (c) 2005-2009 Jun Mitani\nhttp://mitani.cs.tsukuba.ac.jp/\n\n"
            + "This program comes with ABSOLUTELY NO WARRANTY.";

    private About() {
        super(ResourceHolder.getLabelString(
                LABEL.ABOUT));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, content,
                String.format(ResourceHolder.getLabelString(
                        LABEL.MAIN_FRAME_TITLE), Version.VERSION),
                JOptionPane.INFORMATION_MESSAGE);

    }
}
