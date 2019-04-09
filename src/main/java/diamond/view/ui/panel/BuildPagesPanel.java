/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.ui.button.buildPagesButton;

/**
 * @author long_
 *
 */
public class BuildPagesPanel extends JPanel {
    public BuildPagesPanel(PaintContext paintContext) {
        setLayout(new GridLayout(1, 8));
        add(new buildPagesButton(paintContext));
        UiPanelUtil.setBorder(
                this,
                ResourceHolder.getLabelString(LABEL.BUILD_PAGES));
    }
}
