/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.modifycontour;

import java.awt.geom.Point2D.Double;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.context.PointedElement;
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
    protected boolean onAction(Context context, Double currentPoint) {
        OriLine picked = NearestLineFinder.findAround(context);
        if (picked == null) {
            return false;
        }
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        Stack<OriLine> pickedLines = pickedElements.getOriLines();
        if (pickedLines.size() > 0
                && !OriLineUtil.isConnected(picked, pickedLines.peek())) {
            return false;
        }
        pickedLines.push(picked);
        paintScreenContext.getPointedElements().setOriLine(picked);
        return true;
    }

    @Override
    protected void onResult(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        OriLine pointedOriLine = pointedElements.getOriLine();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        Stack<OriLine> pickedLines = pickedElements.getOriLines();
        if (pointedOriLine != null && pickedLines.size() > 2) {
            if (OriLineUtil.isConnected(pointedOriLine, pickedLines.get(0))) {
                for (OriLine line : pickedLines) {
                    line.setType(LineType.CUT);
                }
                Set<OriLine> lines = context.getPalette().getCP().getLines();
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
    protected void rebuild(Context context) {
        context.getPalette().getCP().rebuildModel();
    }

}
