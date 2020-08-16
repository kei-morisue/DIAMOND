/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.mouse;

import java.util.Observable;
import java.util.Observer;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.PointerCyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentBase;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.Graphics;

/**
 * @author Kei Morisue
 *
 */
public class Pointer extends Observable implements Observer {
    private PointerCyborg<Face> face = new PointerCyborg<>(Face.class);
    private PointerCyborg<SegmentBase> segment = new PointerCyborg<>(
            SegmentBase.class);
    private PointerCyborg<Vertex> vertex = new PointerCyborg<>(Vertex.class);

    public Pointer() {
        face.addObserver(this);
        segment.addObserver(this);
        vertex.addObserver(this);
    }

    @SuppressWarnings("unchecked")
    public <T extends Cyborg & Graphics> PointerCyborg<T> get(Class<T> type) {
        if (type == Vertex.class) {
            return (PointerCyborg<T>) vertex;
        }
        if (type == SegmentBase.class) {
            return (PointerCyborg<T>) segment;
        }
        if (type == Face.class) {
            return (PointerCyborg<T>) face;
        }
        return null;//TODO
    }

    public void initialize() {
        face.initialize();
        segment.initialize();
        vertex.initialize();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
