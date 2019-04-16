/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.modifycontour;

import java.awt.geom.Point2D.Double;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriLinePickkingState;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.util.NearestLineFinder;
import diamond.model.geom.util.OriLineUtil;
import diamond.model.palette.cp.editor.LineRemover;

/**
 * @author long_
 *
 */
public class OriLine0PickkingState extends OriLinePickkingState {
    @Override
    protected void initialize() {
        setPrevClass(this.getClass());
        setNextClass(this.getClass());
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        OriLine picked = NearestLineFinder.findAround(context);
        if (picked == null) {
            return false;
        }
        Stack<OriLine> pickedLines = context.getPickedLines();
        if (pickedLines.size() > 0
                && !OriLineUtil.isConnected(picked, pickedLines.peek())) {
            return false;
        }
        pickedLines.push(picked);
        context.pointedOriLine = picked;
        return true;
    }

    @Override
    protected void onResult(PaintContext context) {
        OriLine pointedOriLine = context.pointedOriLine;
        Stack<OriLine> pickedLines = context.getPickedLines();
        if (pointedOriLine != null && pickedLines.size() > 2) {
            if (OriLineUtil.isConnected(pointedOriLine, pickedLines.get(0))) {
                for (OriLine line : pickedLines) {
                    line.setType(LineType.CUT);
                }
                Set<OriLine> lines = context.palette.getCP().getLines();
                HashSet<OriLine> remove = new HashSet<OriLine>();
                for (OriLine line : lines) {
                    if (!pickedLines.contains(line)
                            && line.getType() == LineType.CUT) {
                        remove.add(line);
                    }
                }
                LineRemover.remove(remove, lines);
                pickedLines.clear();
            }
        }
    }

    @Override
    protected void rebuild(PaintContext context) {
        context.palette.getCP().rebuildModel();
    }

}
