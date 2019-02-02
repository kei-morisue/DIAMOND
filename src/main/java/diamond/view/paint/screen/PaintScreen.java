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
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import diamond.Initials;
import diamond.model.palette.cp.CreasePattern;
import diamond.view.paint.PaintContext;

public class PaintScreen extends JPanel implements ComponentListener, Observer {
    private ScreenAxisTransform screenAxsisTransform = new ScreenAxisTransform(
            getWidth(), getHeight());

    private PaintContext paintContext;

    public PaintScreen(PaintContext paintContext) {
        ScreenMouseAction mouseAction = new ScreenMouseAction(
                screenAxsisTransform);
        addMouseListener(mouseAction);
        addMouseMotionListener(mouseAction);
        addMouseWheelListener(mouseAction);
        this.paintContext = paintContext;
        setBackground(Color.gray);
        setSize(Initials.MAIN_FRAME_WIDTH, Initials.MAIN_FRAME_HEIGHT);
    }

    private Graphics2D createFreshG2D() {
        Image g2dBuff = createImage();
        Graphics2D g2d = (Graphics2D) g2dBuff.getGraphics();
        g2d.setTransform(new AffineTransform());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setTransform(screenAxsisTransform.getTransform());
        return g2d;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        CreasePattern creasePattern = new CreasePattern();
        Graphics2DDrawer.drawLines(g2d, creasePattern.getLines());
        Graphics2DDrawer.showXnY(g2d, paintContext.pickCandidateVertex());
    }

    public Image createImage() {
        int w = getWidth();
        int h = getHeight();
        return createImage(w, h);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
