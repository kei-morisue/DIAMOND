/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import diamond.model.palette.diagram.Clipper;
import diamond.view.paint.screen.ScreenTransform;

/**
 * @author long_
 *
 */
public class Diagram {
    private ScreenTransform transform = new ScreenTransform(0, 0);
    private Clipper clipper;

    public Diagram() {

    }

    public Clipper getClipper() {
        return this.clipper;
    }

    public void setClipper(Clipper clipper) {
        this.clipper = clipper;
    }

    public ScreenTransform getTransform() {
        return transform;
    }

    public void setTransform(ScreenTransform transform) {
        this.transform = transform;
    }
}
