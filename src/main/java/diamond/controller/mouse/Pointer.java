/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.mouse;

import java.util.Observable;
import java.util.Observer;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d2.Face;

/**
 * @author Kei Morisue
 *
 */
public class Pointer extends Observable implements Observer {
    private PointerCyborg<Face> face = new PointerCyborg<>();
    private PointerCyborg<AbstractSegment> segment = new PointerCyborg<>();
    private PointerCyborg<Vertex> vertex = new PointerCyborg<>();

    public Pointer() {
        face.addObserver(this);
        segment.addObserver(this);
        vertex.addObserver(this);
    }

    @SuppressWarnings("unchecked")
    public <T extends Cyborg> PointerCyborg<T> get(Class<T> type) {
        if (type.equals(Vertex.class)) {
            return (PointerCyborg<T>) vertex;
        }
        if (type.equals(AbstractSegment.class)) {
            return (PointerCyborg<T>) segment;
        }
        if (type.equals(Face.class)) {
            return (PointerCyborg<T>) face;
        }
        return null;
    }

    public void initialize() {
        face = null;
        segment = null;
        vertex = null;
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
