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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import diamond.model.palette.cp.CreasePattern;
import diamond.view.paint.PaintContext;

public class PaintScreen extends AbstractScreen {
    private CoodinateTransform coordinateTransform = new CoodinateTransform(
            getWidth(), getHeight());

    private PaintContext paintContext;

    public PaintScreen(PaintContext paintContext) {
        super();
        ScreenMouseAction mouseAction = new ScreenMouseAction(
                coordinateTransform, paintContext);
        addMouseListener(mouseAction);
        addMouseMotionListener(mouseAction);
        addMouseWheelListener(mouseAction);
        this.paintContext = paintContext;
        paintContext.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        setBackground(Color.gray);
        int width = getWidth();
        int height = getHeight();
        g2d.fillRect(0, 0, width, height);
        coordinateTransform.ResizeWindow(width, height);
        g2d.setTransform(coordinateTransform.getTransform());
        CreasePattern creasePattern = new CreasePattern();//TODO
        Graphics2dDrawer.drawLines(g2d, creasePattern.getLines());
        Graphics2dDrawer.drawPoints(g2d, creasePattern.getPoints());
        Graphics2dDrawer.describe(g2d, paintContext.currentlogicalMousePoint, 0,
                10);
        Graphics2dDrawer.describe(g2d, paintContext.clickedLatestPoint,
                0, 30);
        Graphics2dDrawer.describe(g2d, coordinateTransform.getTransform(),
                0, 80);
    }

}
