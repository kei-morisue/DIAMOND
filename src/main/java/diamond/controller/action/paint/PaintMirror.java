/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.action.state.AddSegment;
import diamond.controller.action.state.PickCyborg;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.PickerCyborg;
import diamond.model.cyborg.geom.PointerCyborg;
import diamond.model.cyborg.geom.d1.AbstractSegment;

/**
 * @author Kei Morisue
 *
 */
public class PaintMirror extends AbstractPaintActionMouse {
    private Context context;

    public PaintMirror(Context context) {
        this.context = context;
        initialize(
                new PickCyborg<AbstractSegment>(context, AbstractSegment.class),
                new AddSegment(context));
    }

    @Override
    protected void onLeftCtrl() {
        state = state.doAction();
        setChanged();
        notifyObservers();
    }

    @Override
    public void onLeftPress(boolean isCtrl) {
        state.doAction();
        setChanged();
        notifyObservers();
    }

    @Override
    protected void onRightCtrl() {
    }

    @Override
    public void onDraw(Graphics2D g2d) {
        Diagram diagram = context.getDiagram();
        PointerCyborg<AbstractSegment> pointer = context
                .getPointer(AbstractSegment.class);
        pointer.setG2d(g2d, diagram);
        pointer.draw(g2d, diagram);
        PickerCyborg<AbstractSegment> picker = context
                .getPicker(AbstractSegment.class);
        picker.setG2d(g2d, diagram);
        picker.draw(g2d, diagram);
    }

}
