/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.mouse;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.PickerCyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentBase;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.Graphics;

/**
 * @author Kei Morisue
 *
 */
public class Picker {
    private PickerCyborg<Face> faces = new PickerCyborg<>();
    private PickerCyborg<SegmentBase> segments = new PickerCyborg<>();
    private PickerCyborg<Vertex> vs = new PickerCyborg<>();

    public void initialize() {
        faces.initialize();
        segments.initialize();
        vs.initialize();
    }

    @SuppressWarnings("unchecked")
    public <T extends Cyborg & Graphics> PickerCyborg<T> get(Class<T> type) {
        if (type.equals(Vertex.class)) {
            return (PickerCyborg<T>) vs;
        }
        if (type.equals(SegmentBase.class)) {
            return (PickerCyborg<T>) segments;
        }
        if (type.equals(Face.class)) {
            return (PickerCyborg<T>) faces;
        }
        return null;
    }
}
