/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Stack;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.topface.OriFace0PickkingState;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriFaceComparator;
import diamond.view.screen.draw.OriFaceDrawer;

/**
 * @author long_
 *
 */
public class FaceOrderingAction extends AbstractPaintAction {
    public FaceOrderingAction() {
        setActionState(new OriFace0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        FaceOrderingAction.drawOrderedFaces(g2d, context);
    }

    @Override
    public Point2D.Double onMove(PaintContext context) {
        setCandidateFaceOnMove(context);
        return context.pointedOriPoint;
    }

    public static void drawOrderedFaces(Graphics2D g2d,
            PaintContext context) {
        Stack<OriFace> pickedOriFaces = context.getPickedOriFaces();
        if (pickedOriFaces.size() != 1) {
            return;
        }
        OriFace picked = pickedOriFaces.get(0);
        if (picked != null) {
            LinkedList<OriFace> faces = context.palette.getOriModel()
                    .getFaces();
            OriFaceComparator oriFaceComparator = new OriFaceComparator(faces);
            for (OriFace face : faces) {
                Color color;
                switch (oriFaceComparator.compare(picked, face)) {
                case -1:
                    color = Color.blue;
                    break;
                case 1:
                    color = Color.red;
                    break;
                default:
                    color = Color.green;
                    break;
                }
                OriFaceDrawer.drawFace(g2d, face.getOutline(),
                        color);
            }
        }
    }
}
