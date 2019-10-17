/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import javax.swing.JComboBox;

import diamond.controller.paint.context.Palette;
import diamond.model.geom.element.diagram.Diagram;

/**
 * @author long_
 *
 */
public class StepNoComboBox extends JComboBox<Diagram> {

    public StepNoComboBox(Palette palette) {
        super(palette.getDiagrams());
    }

}
