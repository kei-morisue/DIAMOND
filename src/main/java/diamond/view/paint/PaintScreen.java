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

package diamond.view.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.vecmath.Vector2d;

import diamond.Config;
import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.EditMode;
import diamond.paint.core.LineSetting;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.util.ElementSelector;
import diamond.value.OriLine;
import diamond.view.paint.screen.PaintScreenMouseAction;
import diamond.view.paint.screen.ScreenAxisTransform;
import diamond.viewsetting.ViewScreenUpdater;
import diamond.viewsetting.paint.PaintScreenSettingDB;
import diamond.viewsetting.paint.ScreenUpdater;

public class PaintScreen extends JPanel
        implements ActionListener, ComponentListener, Observer {

    private Graphics2D bufferg;
    private Image bufferImage;

    private ArrayList<Vector2d> crossPoints = new ArrayList<>();
    private static final int initialSize = (int) (Config.DEFAULT_PAPER_SIZE
            * 1.1);

    private ScreenAxisTransform screenAxsisTransform = new ScreenAxisTransform(
            initialSize, initialSize);

    public PaintScreen() {
        PaintScreenMouseAction mouseAction = new PaintScreenMouseAction(
                screenAxsisTransform);
        addMouseListener(mouseAction);
        addMouseMotionListener(mouseAction);
        addMouseWheelListener(mouseAction);
        addComponentListener(this);
        ScreenUpdater.getInstance().addObserver(this);
        PaintScreenSettingDB.getInstance().addObserver(this);
        PaintContext.setPainterScreen(this);//TODO remove this
    }

    private void drawCandidatePosition(Graphics g) {
        Vector2d candidate = PaintContext.getInstance().pickCandidateV;
        if (candidate != null) {
            g.setColor(Color.BLACK);
            g.drawString("(" + candidate.x +
                    "," + candidate.y + ")", 0, 10);
        }

    }

    private void drawGridLine(Graphics2D g2d) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(LineSetting.STROKE_GRID);

        double paperSize = DocHolder.getDoc().getPaperSize();

        int lineNum = PaintConfig.gridDivNum;
        double step = paperSize / lineNum;

        for (int i = 1; i < lineNum; i++) {
            g2d.draw(new Line2D.Double(
                    step * i - paperSize / 2.0, -paperSize / 2.0,
                    step * i - paperSize / 2.0, paperSize / 2.0));

            g2d.draw(new Line2D.Double(
                    -paperSize / 2.0, step * i - paperSize / 2.0,
                    paperSize / 2.0, step * i - paperSize / 2.0));
        }
    }

    private void drawLines(Graphics2D g2d, Collection<OriLine> lines) {

        ElementSelector selector = new ElementSelector();
        for (OriLine line : lines) {
            if (line.typeVal == OriLine.TYPE_NONE
                    && !PaintConfig.dispAuxLines) {
                continue;
            }

            if ((line.typeVal == OriLine.TYPE_RIDGE
                    || line.typeVal == OriLine.TYPE_VALLEY)
                    && !PaintConfig.dispMVLines) {
                continue;
            }

            g2d.setColor(selector.selectColorByLineType(line.typeVal));
            g2d.setStroke(selector.selectStroke(line.typeVal));

            if (PaintConfig.mouseAction != null) {
                if (PaintContext.getInstance().getLines()
                        .contains(line) == false) {
                    g2d.draw(new Line2D.Double(line.p0.x, line.p0.y, line.p1.x,
                            line.p1.y));
                }
            }
        }
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

        bufferImage = createImage(getWidth(), getHeight());
        bufferg = (Graphics2D) bufferImage.getGraphics();
        repaint();
    }

    @Override
    public void componentShown(ComponentEvent arg0) {
    }

    public Image getCreasePatternImage() {

        return bufferImage;
    }

    // Scaling relative to the center of the screen
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bufferImage == null) {
            bufferImage = createImage(getWidth(), getHeight());
            bufferg = (Graphics2D) bufferImage.getGraphics();
        } //TODO Why here?

        // initialize the AffineTransform of bufferg
        bufferg.setTransform(new AffineTransform());

        // Clears the image buffer
        bufferg.setColor(Color.WHITE);
        bufferg.fillRect(0, 0, getWidth(), getHeight());

        // set the AffineTransform of buffer
        bufferg.setTransform(screenAxsisTransform.getTransform());

        Graphics2D g2d = bufferg;

        Doc doc = DocHolder.getDoc();
        CreasePattern creasePattern = doc.getCreasePattern();
        if (PaintScreenSettingDB.getInstance().isGridVisible()) {

            drawGridLine(g2d);
        }

        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setStroke(LineSetting.STROKE_VALLEY);
        g2d.setColor(Color.black);

        drawLines(g2d, creasePattern);

        // Drawing of the vertices
        if (PaintConfig.getMouseAction().getEditMode() == EditMode.VERTEX
                || PaintConfig.dispVertex) {
            drawVertexRectangles(g2d);
        }

        {
            double scale = screenAxsisTransform.getScale();
            for (Vector2d v : crossPoints) {
                g2d.setColor(Color.RED);
                g2d.fill(
                        new Rectangle2D.Double(v.x - 5.0 / scale,
                                v.y - 5.0 / scale,
                                10.0 / scale, 10.0 / scale));
            }
        }

        if (PaintConfig.bDispCrossLine) {
            List<OriLine> crossLines = doc.getCrossLines();
            if (!crossLines.isEmpty()) {
                g2d.setStroke(LineSetting.STROKE_TMP_OUTLINE);
                g2d.setColor(Color.MAGENTA);

                for (OriLine line : crossLines) {
                    Vector2d v0 = line.p0;
                    Vector2d v1 = line.p1;

                    g2d.draw(new Line2D.Double(v0.x, v0.y, v1.x, v1.y));

                }
            }
        }

        if (PaintConfig.mouseAction != null) {
            PaintConfig.mouseAction.onDraw(g2d, PaintContext.getInstance());

            g.drawImage(bufferImage, 0, 0, this);

            drawCandidatePosition(g);
        } else {
            g.drawImage(bufferImage, 0, 0, this);

        }
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

    private void drawVertexRectangles(Graphics2D g2d) {
        CreasePattern creasePattern = DocHolder.getDoc().getCreasePattern();

        g2d.setColor(Color.BLACK);
        final double vertexDrawSize = 2.0;
        for (OriLine line : creasePattern) {
            if (!PaintConfig.dispAuxLines
                    && line.typeVal == OriLine.TYPE_NONE) {
                continue;
            }
            if (!PaintConfig.dispMVLines && (line.typeVal == OriLine.TYPE_RIDGE
                    || line.typeVal == OriLine.TYPE_VALLEY)) {
                continue;
            }
            Vector2d v0 = line.p0;
            Vector2d v1 = line.p1;
            {
                double scale = screenAxsisTransform.getScale();
                g2d.fill(new Rectangle2D.Double(v0.x - vertexDrawSize / scale,
                        v0.y - vertexDrawSize / scale,
                        vertexDrawSize * 2 / scale,
                        vertexDrawSize * 2 / scale));
                g2d.fill(new Rectangle2D.Double(v1.x - vertexDrawSize / scale,
                        v1.y - vertexDrawSize / scale,
                        vertexDrawSize * 2 / scale,
                        vertexDrawSize * 2 / scale));
            }
        }

    }

}
