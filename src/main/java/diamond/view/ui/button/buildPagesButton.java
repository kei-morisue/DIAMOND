/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;

import javax.swing.JButton;

import diamond.controller.paint.ModelContext;
import diamond.controller.paint.PaintContext;
import diamond.view.resource.IconSetter;

/**
 * @author long_
 *
 */
public class buildPagesButton extends JButton {
    PaintContext paintContext;
    ModelContext modelContext;

    public buildPagesButton(PaintContext paintContext,
            ModelContext modelContext) {
        super("Build Pages");
        setBackground(Color.white);
        IconSetter.set(this, "build.gif");
        setSize(100, 100);
        this.paintContext = paintContext;
        this.paintContext = paintContext;
    }
}
