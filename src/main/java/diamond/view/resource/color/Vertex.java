/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.resource.color;

import java.awt.Color;

/**
 * @author Kei Morisue
 *
 */
public class Vertex {
    public static final Color NEUTRAL = Color.BLACK;
    public static final Color SELECTED = Color.GREEN;
    public static final Color WRONG = Color.RED;

    public static Color getColor(diamond.model.cyborg.Vertex v) {
        if (v.property.isPointed) {
            return SELECTED;
        }
        if (!v.property.isFoldable) {
            return WRONG;
        }
        return NEUTRAL;
    }
}
