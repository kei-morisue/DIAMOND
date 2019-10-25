/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.paint.context.PaintScreenContext;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class SignsUiPanel extends JPanel {

    public SignsUiPanel(PaintScreenContext context, ButtonGroup buttonGroup) {
        setLayout(new GridLayout(3, 4));
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
        UiPanelUtil.addArrowPaintButton(this, buttonGroup,
                LABEL.REPEAT_ARROW, context);
        UiPanelUtil.addArrowPaintButton(this, buttonGroup,
                LABEL.SCALE_ARROW, context);
        UiPanelUtil.addArrowPaintButton(this, buttonGroup,
                LABEL.OFFSET_ARROW, context);
        UiPanelUtil.addLandmarkPaintButton(this, buttonGroup,
                LABEL.LANDMARK, context);

    }
}
