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
import diamond.controller.paint.action.PaintActionListnener;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;
import diamond.view.paint.screen.debug.Debugger;
import diamond.view.paint.screen.draw.ColorStyle;
import diamond.view.paint.screen.draw.LineStrokeSetting;
import diamond.view.paint.screen.draw.OriDrawer;
import diamond.view.paint.screen.draw.VertexSetting;

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
        for (OriFace face : model.getFaces()) {
            OriDrawer.drawFace(g2d, face.getOutline(),
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
        for (OriLine l : cp.getLines()) {
            OriDrawer.drawLine(
                    g2d,
                    l,
                    ColorStyle.getCpColor(l.getType()),
                    LineStrokeSetting.getCpStroke(l.getType()));
        }
    }

}
