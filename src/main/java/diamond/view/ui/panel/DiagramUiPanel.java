/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class DiagramUiPanel extends JPanel {

    public DiagramUiPanel(PaintContext context, ButtonGroup buttonGroup) {
        setLayout(new GridLayout(3, 3));
        UiPanelUtil.addArrowPaintButton(this, buttonGroup,
                LABEL.VALLEY_ARROW, context);
        UiPanelUtil.addArrowPaintButton(this, buttonGroup,
                LABEL.MOUNTAIN_ARROW, context);
        UiPanelUtil.addArrowPaintButton(this, buttonGroup,
                LABEL.FOLD_UNFOLD_ARROW, context);

        UiPanelUtil.addArrowPaintButton(this, buttonGroup,
                LABEL.SINK_ARROW, context);
        UiPanelUtil.addArrowPaintButton(this, buttonGroup,
                LABEL.FLIP_ARROW, context);
        UiPanelUtil.addArrowPaintButton(this, buttonGroup,
                LABEL.ROTATE_ARROW, context);

        UiPanelUtil.addLandmarkPaintButton(this, buttonGroup,
                LABEL.LANDMARK, context);

        UiPanelUtil.setBorder(
                this,
                ResourceHolder.getLabelString(LABEL.DIAGRAM_TAB));
    }
}
