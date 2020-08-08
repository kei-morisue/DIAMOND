/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.mouse;

import java.util.Collection;
import java.util.Observable;

import diamond.Config;
import diamond.controller.Context;
import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.step.Step;
import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public class PointerCyborg<T extends Cyborg> extends Observable {
    private T pointed;
    private Class<T> type;

    public PointerCyborg(Class<T> type) {
        this.type = type;
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
