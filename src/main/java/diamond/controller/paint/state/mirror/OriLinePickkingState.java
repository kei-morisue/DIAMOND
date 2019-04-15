/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.mirror;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Stack;

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
        //TODO strange implementation
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

    protected boolean onAction(PaintContext context, Double currentPoint) {
        OriLine pointedOriLine = context.pointedOriLine;
        if (pointedOriLine != null) {
            Stack<OriLine> pickedLines = context.getPickedLines();
            if (pickedLines.contains(pointedOriLine)) {
                pickedLines.remove(pointedOriLine);
            } else {
                pickedLines.push(pointedOriLine);
            }
        }
        return true;
    }

    @Override
    protected void onResult(PaintContext context) {
    }

}
