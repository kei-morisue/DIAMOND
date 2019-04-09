/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;

import javax.swing.JButton;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.IconSetter;

/**
 * @author long_
 *
 */
public class buildPagesButton extends JButton {
    PaintContext paintContext;

    public buildPagesButton(PaintContext paintContext) {
        setBackground(Color.white);
        IconSetter.set(this, "build.gif");
        this.paintContext = paintContext;
        this.paintContext = paintContext;
    }
}
