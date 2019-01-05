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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import diamond.doc.DocHolder;
import diamond.paint.EditMode;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.CreasePattern;
import diamond.viewsetting.ViewScreenUpdater;
import diamond.viewsetting.paint.ScreenUpdater;

public class PaintScreen extends JPanel
        implements ActionListener, ComponentListener, Observer {
    private Image bufferImage;

    private ScreenAxisTransform screenAxsisTransform = new ScreenAxisTransform(
            getWidth(), getHeight());

    public PaintScreen() {
        ScreenMouseAction mouseAction = new ScreenMouseAction(
                screenAxsisTransform);
        addMouseListener(mouseAction);
        addMouseMotionListener(mouseAction);
        addMouseWheelListener(mouseAction);
        addComponentListener(this);
        ScreenUpdater.getInstance().addObserver(this);
        PaintContext.getInstance().addObserver(this);
        PaintContext.setPainterScreen(this);//TODO remove this
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = initializeBufferG2D();
        CreasePattern creasePattern = DocHolder.getDoc().getCreasePattern();
        Graphics2DDrawer.drawLines(g2d, creasePattern);
        if (PaintConfig.getMouseAction().getEditMode() == EditMode.VERTEX
                || PaintConfig.dispVertex) {
            Graphics2DDrawer.drawVertexRectangles(g2d,
                    DocHolder.getDoc().getCreasePattern());
        }
        if (PaintContext.getInstance().dispGrid) {
            Graphics2DDrawer.drawGridLine(g2d,
                    DocHolder.getDoc().getPaperSize(), PaintConfig.gridDivNum);
        }

        if (PaintConfig.bDispCrossLine) {
            Graphics2DDrawer.drawCrossLines(g2d,
                    DocHolder.getDoc().getCrossLines());
        }
        if (PaintConfig.mouseAction != null) {
            PaintConfig.mouseAction.onDraw(g2d, PaintContext.getInstance());
            Graphics2DDrawer.showXnY(g2d,
                    PaintContext.getInstance().pickCandidateV);
        }
        g.drawImage(bufferImage, 0, 0, this);
    }

    private Graphics2D initializeBufferG2D() {
        bufferImage = createImage(getWidth(), getHeight());
        Graphics2D bufferg = (Graphics2D) bufferImage.getGraphics();
        bufferg.setTransform(new AffineTransform());
        bufferg.setColor(Color.WHITE);
        bufferg.fillRect(0, 0, getWidth(), getHeight());
        bufferg.setTransform(screenAxsisTransform.getTransform());
        return bufferg;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    @Override
    public void componentHidden(ComponentEvent arg0) {
    }

    @Override
    public void componentMoved(ComponentEvent arg0) {
    }

    @Override
    public void componentResized(ComponentEvent arg0) {
        if (getWidth() <= 0 || getHeight() <= 0) {
            return;
        }
        screenAxsisTransform.Resize(getWidth(), getHeight());
        repaint();
    }

    @Override
    public void componentShown(ComponentEvent arg0) {
    }

    public Image getCreasePatternImage() {
        return bufferImage;
    }

    @Override
    public void update(Observable o, Object arg) {
        String name = o.toString();
        if (name.equals(ScreenUpdater.getInstance().getName())) {
            if (arg != null) {
                if (arg.equals(ViewScreenUpdater.REDRAW_REQUESTED)) {
                    repaint();
                }
            }
        }
    }

}
