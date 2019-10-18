/**
 * DIAMOND - Origami Diagram Editor
*/

package diamond.view.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import diamond.controller.paint.action.PaintActionInterface;
import diamond.controller.paint.action.PaintScreenAction;
import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;
import diamond.view.screen.debug.Debugger;
import diamond.view.screen.draw.OriFaceDrawer;
import diamond.view.screen.draw.OriLineDrawer;
import diamond.view.screen.draw.OriPointDrawer;
import diamond.view.screen.draw.OriVertexDrawer;
import diamond.view.screen.draw.StringDrawer;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.VertexStyle;
import diamond.view.screen.draw.style.color.OriFaceColor;
import diamond.view.screen.draw.style.color.OriLineColor;
import diamond.view.screen.draw.style.color.OriVertexColor;
import diamond.view.screen.draw.style.color.Ui;

public class PaintScreen extends AbstractScreen {
    private Context context;
    private ModelScreen modelScreen;//TODO Screen cant have a screen

    public PaintScreen(Context context, ModelScreen modelScreen) {
        super(context.getPaintScreenContext());
        context.getPaintScreenContext().getTransform().zoom(1.8);
        this.context = context;
        this.modelScreen = modelScreen;
        PaintScreenAction paintActionListnener = new PaintScreenAction(
                context);
        addMouseListener(paintActionListnener);
        addMouseMotionListener(paintActionListnener);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, Ui.PAINT_SCREEN_BG);
        PaintScreenContext paintContext = context.getPaintScreenContext();
        paintContext.getTransform().ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(paintContext.getTransform().getTransform());
        paintCreasePattern(g2d);
        PaintActionInterface paintAction = paintContext.getPaintAction();
        if (paintAction != null) {
            paintAction.onDraw(g2d, context);
        }
        modelScreen.repaint();
        Debugger.debugPaintContext(g2d, paintContext);
    }

    private void paintCreasePattern(Graphics2D g2d) {
        Cp cp = context.getPalette().getCP();
        OriModel model = cp.getOriModel();
        paintFaces(g2d, model);
        paintVertices(g2d, model);
        paintLines(g2d, cp);

    }

    private void paintLines(Graphics2D g2d, Cp cp) {
        for (OriLine l : cp.getLines()) {
            OriLineDrawer.drawLine(
                    g2d,
                    l,
                    OriLineColor
                            .getCpColor(l.getType()),
                    LineStyle.getCpStroke(l.getType()));
            if (l.getArrow() != null) {
                l.getArrow().draw(g2d, l);
            }
            if (l.p0.isLandmark()) {
                OriVertexDrawer.drawLandMark(g2d, l.p0);
            }
            if (l.p1.isLandmark()) {
                OriVertexDrawer.drawLandMark(g2d, l.p1);
            }

        }
    }

    private void paintVertices(Graphics2D g2d, OriModel model) {
        for (OriVertex vertex : model.getVertices()) {
            OriPointDrawer.drawPoint(
                    g2d,
                    vertex,
                    getVertexSize(vertex),
                    getColor(vertex));
        }
    }

    private double getVertexSize(OriVertex vertex) {
        PaintScreenContext paintContext = context.getPaintScreenContext();
        double scale = paintContext.getTransform().getScale();
        if (vertex.isPickked()) {
            return VertexStyle.SIZE_PICKED / scale;
        }
        if (!vertex.isFoldable()) {
            return VertexStyle.SIZE_WRONG / scale;
        }
        return VertexStyle.SIZE / scale;
    }

    private Color getColor(OriVertex vertex) {
        if (!vertex.isFoldable()) {
            return OriVertexColor.WRONG_ORI_VERTEX;
        }
        if (vertex.isPickked()) {
            return VertexStyle.COLOR_SELECTED;
        }
        return OriVertexColor.CP_ORI_VERTEX;
    }

    private void paintFaces(Graphics2D g2d, OriModel model) {
        LinkedList<OriFace> faces = model.getFaces();
        for (OriFace face : faces) {
            OriFaceDrawer.drawFace(g2d, face, OriFaceColor.ORI_FACE_BACK);
        }
        OriFaceDrawer.drawBaseFace(g2d, model);
        for (OriFace face : faces) {
            StringDrawer.drawFaceNo(g2d, face, faces);
        }

    }

    @Override
    public Context getContext() {
        return context;
    }

}
