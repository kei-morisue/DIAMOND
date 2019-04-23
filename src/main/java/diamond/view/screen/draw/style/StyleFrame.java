/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw.style;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import diamond.Initials;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.ui.panel.FaceOption;
import diamond.view.ui.panel.LineOption;

/**
 * @author long_
 *
 */
public class StyleFrame extends JFrame {
    public StyleFrame() {
        setTitle(ResourceHolder.getLabelString(LABEL.STYLE));
        setSize(Initials.STYLE_FRAME_WIDTH, Initials.STYLE_FRAME_HEIGHT);
        JTabbedPane pane = new JTabbedPane();
        pane.addTab(
                ResourceHolder.getLabelString(LABEL.LINE_TAB),
                new LineOption());
        pane.addTab(
                ResourceHolder.getLabelString(LABEL.FACE_TAB),
                new FaceOption());

        add(pane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
