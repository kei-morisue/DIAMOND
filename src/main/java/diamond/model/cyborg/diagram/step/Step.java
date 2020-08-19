/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.HashSet;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentBase;
import diamond.model.cyborg.geom.d1.SegmentEdge;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.Graphics;
import diamond.model.cyborg.style.StyleStep;
import diamond.view.ui.screen.ScreenMain;
import diamond.view.ui.screen.ScreenStep;

/**
 * @author Kei Morisue
 *
 */
public final class Step extends StepBase implements Graphics {
    private HashSet<SegmentBase> segments = new HashSet<>();
    private HashSet<Vertex> vertices = new HashSet<>();

    @Deprecated
    public Step() {
    }

    @Override
    public void draw(Graphics2D g2d, ScreenMain screen) {
        for (Face face : faces) {
            face.draw(g2d, screen);
        }
        for (SegmentEdge edge : edges) {
            edge.setG2d(g2d, screen);
            edge.draw(g2d, screen);
        }
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenMain screen) {
    }

    @Override
    public void draw(Graphics2D g2d, ScreenStep screen) {
        setG2d(g2d, screen);
        for (Face face : faces) {
            face.draw(g2d, screen);
        }
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenStep screen) {
        g2d.setColor(StyleStep.COLOR);
        Diagram diagram = screen.diagram();
        int i = diagram.getSteps().indexOf(this);
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        g2d.setFont(StyleStep.FONT_STEP);
        g2d.drawString(String.valueOf(i + 1), 10,
                g2d.getFont().getSize());
        g2d.setTransform(tmpTransform);
    }

    public void update() {
        new Folder(this);
        setCyborg();
    }

    private void setCyborg() {
        setSegments();
        setVertices();
    }

    private void setSegments() {
        this.segments.clear();
        for (Face face : faces) {
            this.segments.addAll(face.getCreases());
            this.segments.addAll(edges);
        }
    }

    private void setVertices() {
        this.vertices.clear();
        for (SegmentBase segment : segments) {
            vertices.add(segment.getV0());
            vertices.add(segment.getV1());
        }
        for (Face face : faces) {
            for (Vertex v : face.getVertices()) {
                vertices.add(v);
            }
        }
    }

    public HashSet<Vertex> getVertices() {
        return vertices;
    }

    public HashSet<SegmentBase> getSegments() {
        return segments;
    }

    public void remove(SegmentEdge edge) {
        edges.remove(edge);
    }

    public void add(SegmentEdge edge) {
        edges.add(edge);
    }

    public void link(Face f0, Face f1, Vertex v0, Vertex v1) {
        SegmentEdge edge = new SegmentEdge(f1, f0, v0, v1);
        add(edge);
    }
}
