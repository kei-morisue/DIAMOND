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
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.CreasePatternHolder;
import diamond.model.palette.cp.CreasePattern;
import diamond.view.resource.color.ColorStyle;
import diamond.view.resource.graphic.LineStrokeSetting;
import diamond.view.resource.graphic.VertexSetting;

public class PaintScreen extends AbstractScreen {
    private PaintContext paintContext;

    public PaintScreen(PaintContext paintContext) {
        super();
        PaintScreenCoordinateListnener coordinateActionListener = new PaintScreenCoordinateListnener(
                paintContext);
        PaintActionListnener paintActionListnener = new PaintActionListnener(
                paintContext);
        addMouseListener(coordinateActionListener);
        addMouseMotionListener(coordinateActionListener);
        addMouseWheelListener(coordinateActionListener);
        addMouseListener(paintActionListnener);
        addMouseMotionListener(paintActionListnener);

        this.paintContext = paintContext;
        //paintContext.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        setBackground(ColorStyle.FRAME_BG);
        int width = getWidth();
        int height = getHeight();
        g2d.fillRect(0, 0, width, height);
        paintContext.coordinateTransform.ResizeWindow(width, height);
        g2d.setTransform(paintContext.coordinateTransform.getTransform());
        paintCreasePattern(g2d);
        this.paintDebuggingComponent(g2d);
        if (paintContext.paintAction != null) {
            paintContext.paintAction.onDraw(g2d, paintContext);
        }

    }

    private void paintCreasePattern(Graphics2D g2d) {
        CreasePattern creasePattern = CreasePatternHolder.getCP();
        for (OriLine l : creasePattern.getLines()) {
            OriDrawer.drawLine(
                    g2d,
                    l,
                    ColorStyle.getColor(l),
                    LineStrokeSetting.STROKE_VALLEY);
        }
        for (OriPoint p : creasePattern.getPoints()) {
            OriDrawer.drawPoint(
                    g2d,
                    p,
                    VertexSetting.VERTEX_SIZE,
                    ColorStyle.ORI_POINT);
        }
    }

    /**
     * @param g
     */
    private void paintDebuggingComponent(Graphics2D g2d) {
        OriDrawer.describe(g2d, paintContext.currentLogicalMousePoint, 0,
                10);
        OriDrawer.describe(g2d, paintContext.pointedOriLine,
                0, 30);
        OriDrawer.describe(g2d, paintContext.coordinateTransform.getTransform(),
                0, 80);
    }
}
