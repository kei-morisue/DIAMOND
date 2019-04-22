/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.mirror;

import java.util.ArrayList;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.util.OriLineUtil;
import diamond.model.palette.cp.editor.LineAdder;

/**
 * @author long_
 *
 */
public class OriLinePickkingState
        extends diamond.controller.paint.state.OriLinePickkingState {
    @Override
    protected void initialize() {
        setPrevClass(this.getClass());
        setNextClass(this.getClass());

    }

    @Override
    protected void undoAction(PaintContext context) {
        OriLine pointedOriLine = context.pointedOriLine;
        ArrayList<OriLine> mirroredLines = new ArrayList<OriLine>();
        if (pointedOriLine != null) {
            for (OriLine line : context.getPickedLines()) {
                OriLine mirroredLine = OriLineUtil.mirroredLine(line,
                        pointedOriLine);
                mirroredLines.add(mirroredLine);
            }
        }
        LineAdder.addAll(mirroredLines, context.palette.getCP().getLines());
        context.getPickedLines().clear();
    }

    @Override
    protected void onResult(PaintContext context) {
    }

}
