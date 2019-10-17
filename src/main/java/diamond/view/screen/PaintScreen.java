/**
 * DIAMOND - Origami Diagram Editor
*/

package diamond.view.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import diamond.controller.paint.action.PaintScreenAction;
import diamond.controller.paint.context.AbstractScreenContext;
import diamond.controller.paint.context.PaintContext;
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
    private PaintContext context;
    private ModelScreen modelScreen;//TODO Screen cant have a screen

    public PaintScreen(PaintContext paintContext, ModelScreen modelScreen) {
        super(paintContext);
        this.context = paintContext;
        this.modelScreen = modelScreen;
        PaintScreenAction paintActionListnener = new PaintScreenAction(
                paintContext);
        addMouseListener(paintActionListnener);
        addMouseMotionListener(paintActionListnener);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, Ui.PAINT_SCREEN_BG);
        context.getTransform().ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(context.getTransform().getTransform());
        paintCreasePattern(g2d);
        if (context.getPaintAction() != null) {
            context.getPaintAction().onDraw(g2d, context);
        }
        modelScreen.repaint();
        Debugger.debugPaintContext(g2d, context);

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
        double scale = context.getTransform().getScale();
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
    public AbstractScreenContext getContext() {
        return context;
    }

}
