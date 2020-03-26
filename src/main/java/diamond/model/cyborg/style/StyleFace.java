/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.style;

import java.awt.Color;

/**
 * @author Kei Morisue
 *
 */
public class StyleFace {
    private Color front = Color.GRAY;
    private Color back = Color.WHITE;

    public StyleFace() {
    }

    public Color getFront() {
        return front;
    }

    public void setFront(Color front) {
        this.front = front;
    }

    public Color getBack() {
        return back;
    }

    public void setBack(Color back) {
        this.back = back;
    }

    public Color getColor(boolean isFront) {
        return (isFront) ? front : back;
    }

}
