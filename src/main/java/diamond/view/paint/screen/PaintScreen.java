/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2005-2009 Jun Mitani http://mitani.cs.tsukuba.ac.jp/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package diamond.view.paint.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.listener.PaintActionListnener;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriModel;
import diamond.model.geom.element.orimodel.OriVertex;
import diamond.model.palette.cp.CreasePattern;
import diamond.view.paint.screen.debug.Debugger;
import diamond.view.paint.screen.draw.OriDrawer;
import diamond.view.resource.color.ColorStyle;
import diamond.view.resource.graphic.LineStrokeSetting;
import diamond.view.resource.graphic.VertexSetting;

public class PaintScreen extends AbstractScreen {
    private PaintContext paintContext;

    public PaintScreen(PaintContext paintContext) {
        super(paintContext);
        this.paintContext = paintContext;

        PaintActionListnener paintActionListnener = new PaintActionListnener(
                paintContext);
        addMouseListener(paintActionListnener);
        addMouseMotionListener(paintActionListnener);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBG(g2d, ColorStyle.PAINT_SCREEN_BG);
        paintContext.coordinateTransform.ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(paintContext.coordinateTransform.getTransform());
        paintCreasePattern(g2d);
        if (paintContext.paintAction != null) {
            paintContext.paintAction.onDraw(g2d, paintContext);
        }

        Debugger.debugPaintContext(g2d, paintContext);

    }

    private void paintCreasePattern(Graphics2D g2d) {
        CreasePattern creasePattern = paintContext.getCP();
        OriModel model = new OriModel(creasePattern);
        for (OriFace face : model.getFaces()) {
            OriDrawer.drawFace(g2d, face,
                    ColorStyle.ORIFACE);
        }
        for (OriVertex vertex : model.getVertices()) {
            OriDrawer.drawVertex(
                    g2d,
                    vertex,
                    VertexSetting.VERTEX_SIZE,
                    (vertex.isFoldable()) ? ColorStyle.ORIVERTEX
                            : ColorStyle.WRONG_ORIVERTEX);
        }
        for (OriLine l : creasePattern.getLines()) {
            OriDrawer.drawLine(
                    g2d,
                    l,
                    ColorStyle.getColor(l),
                    LineStrokeSetting.STROKE_VALLEY);
        }
    }
}
