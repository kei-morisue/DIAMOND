/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Observable;

import diamond.Config;
import diamond.controller.Context;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.GraphicsCp;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StyleSegment;
import diamond.model.cyborg.style.StyleVertex;
import diamond.model.math.Fuzzy;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class PointerCyborg<T extends Cyborg & GraphicsCp> extends Observable
        implements GraphicsCp {
    private T pointed;
    private Class<T> type;

    public PointerCyborg(Class<T> type) {
        this.type = type;
    }

    @Override
    public void draw(Graphics2D g2d, Diagram diagram) {
        if (pointed == null) {
            return;
        }
        pointed.draw(g2d, diagram);
    }

    @Override
    public void setG2d(Graphics2D g2d, Diagram diagram) {
        if (type == Face.class) {
            g2d.setColor(StyleFace.POINTED);
            return;
        }
        if (type == Vertex.class) {
            g2d.setColor(StyleVertex.POINTED);
            return;
        }
        if (type == AbstractSegment.class) {
            g2d.setStroke(diagram.getStyleSegment()
                    .strokePointed((float) G2DUtil.getScale(g2d)));
            g2d.setColor(StyleSegment.POINTED);
            return;
        }
    }

    public void initialize() {
        pointed = null;
        setChanged();
        notifyObservers();
    }

    public boolean add(Context context) {
        Collection<T> candidates = getCandidates(context);
        for (T t : candidates) {
            double dist = t.dist(context.getMouseLocation());
            if (Fuzzy.isSmall(dist, eps(t))) {
                pointed = t;
                return true;
            }
        }
        pointed = null;
        return false;
    }

    @SuppressWarnings("unchecked")
    private Collection<T> getCandidates(Context context) {
        Step step = context.getDiagram().getStep();
        if (type == Face.class) {
            return (Collection<T>) step.getFaces();
        }
        if (type == Vertex.class) {
            return (Collection<T>) step.getVertices();
        }
        if (type == AbstractSegment.class) {
            return (Collection<T>) step.getSegments();
        }
        return null;//TODO
    }

    private int eps(T t) {
        if (t.getClass().equals(Face.class)) {
            return Config.EPS_F;
        }
        return Config.EPS_V;
    }

    public T get() {
        return pointed;
    }

}
