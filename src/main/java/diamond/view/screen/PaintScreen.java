/**
 * DIAMOND - Origami Diagram Editor
*/

package diamond.view.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.action.PaintActionListnener;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;
import diamond.view.screen.debug.Debugger;
import diamond.view.screen.draw.OriDrawer;
import diamond.view.screen.draw.style.ColorStyle;
import diamond.view.screen.draw.style.LineStrokeStyle;
import diamond.view.screen.draw.style.VertexStyle;

public class PaintScreen extends AbstractScreen {
    private PaintContext paintContext;

    public PaintScreen(PaintContext paintContext) {
        super(paintContext);
        this.paintContext = paintContext;

        PaintActionListnener paintActionListnener = new PaintActionListnener(
                paintContext);
        addMouseListener(paintActionListnener);
        addMouseMotionListener(paintActionListnener);
        paintContext.palette.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, ColorStyle.PAINT_SCREEN_BG);
        paintContext.transform.ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(paintContext.transform.getTransform());
        paintCreasePattern(g2d);
        if (paintContext.paintAction != null) {
            paintContext.paintAction.onDraw(g2d, paintContext);
        }

        Debugger.debugPaintContext(g2d, paintContext);

    }

    private void paintCreasePattern(Graphics2D g2d) {
        Cp cp = paintContext.palette.getCP();
        OriModel model = cp.getOriModel();
        paintFaces(g2d, model);
        paintVertices(g2d, model);
        paintLines(g2d, cp);

    }

    private void paintLines(Graphics2D g2d, Cp cp) {
        for (OriLine l : cp.getLines()) {
            OriDrawer.drawLine(
                    g2d,
                    l,
                    ColorStyle.getCpColor(l.getType()),
                    LineStrokeStyle.getCpStroke(l.getType()));
        }
    }

    private void paintVertices(Graphics2D g2d, OriModel model) {
        for (OriVertex vertex : model.getVertices()) {
            OriDrawer.drawPoint(
                    g2d,
                    vertex,
                    VertexStyle.SIZE,
                    (vertex.isFoldable()) ? ColorStyle.CP_ORI_VERTEX
                            : ColorStyle.WRONG_ORI_VERTEX);
        }
    }

    private void paintFaces(Graphics2D g2d, OriModel model) {
        LinkedList<OriFace> faces = model.getFaces();
        for (OriFace face : faces) {
            OriDrawer.drawFace(g2d, face.getOutline(),
                    ColorStyle.ORI_FACE_FRONT);
            OriDrawer.drawFaceNo(g2d, face, faces);
        }
        OriFace baseFace = model.getBaseFace();
        OriDrawer.drawFace(g2d, baseFace.getOutline(),
                ColorStyle.CP_BASE_ORI_FACE);
        OriDrawer.drawFaceNo(g2d, baseFace, faces);

    }

}
