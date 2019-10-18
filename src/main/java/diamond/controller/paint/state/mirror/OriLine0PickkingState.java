/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.mirror;

import java.util.ArrayList;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.OriLinePickkingState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.util.OriLineUtil;
import diamond.model.palette.cp.editor.LineAdder;

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
    protected void undoAction(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        OriLine pointedOriLine = paintScreenContext.getPointedOriLine();
        ArrayList<OriLine> mirroredLines = new ArrayList<OriLine>();
        if (pointedOriLine != null) {
            for (OriLine line : paintScreenContext.getPickedLines()) {
                OriLine mirroredLine = OriLineUtil.mirroredLine(line,
                        pointedOriLine);
                mirroredLines.add(mirroredLine);
            }
        }
        LineAdder.addAll(mirroredLines,
                context.getPalette().getCP().getLines());
        paintScreenContext.getPickedLines().clear();
    }

    @Override
    protected void onResult(Context context) {
    }

}
