/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.view.paint.PaintContext;
import diamond.view.paint.screen.PaintScreen;
import diamond.view.resource.ImageIconLoader;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class panelUI extends JPanel {
    private JRadioButton axiom1Button = new JRadioButton(
            ResourceHolder.getLabelString(LABEL.INPUT_LINE));

    public panelUI(PaintScreen screen, PaintContext context) {
        ImageIconLoader imageIconLoader = new ImageIconLoader();
        axiom1Button.setIcon(
                imageIconLoader.loadAsIcon("icon/segment.gif"));
        add(axiom1Button);
        axiom1Button.setPressedIcon(
                imageIconLoader.loadAsIcon("icon/segment_P.gif"));
    }
}
