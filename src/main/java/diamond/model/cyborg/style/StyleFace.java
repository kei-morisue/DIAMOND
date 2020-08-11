/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.style;

import java.awt.Color;

import diamond.model.Setter;

/**
 * @author Kei Morisue
 *
 */
public class StyleFace {
    private Color front = Color.GRAY;
    private Color back = Color.WHITE;
    public static final Color POINTED = Color.GREEN;

    public StyleFace() {
    }

    public Color getFront() {
        return front;
    }

    @Deprecated
    public void setFront(Color front) {
        this.front = front;
    }

    public class FrontSetter implements Setter<Color> {
        @Override
        public void set(Color color) {
            front = color;
        }
    }

    public Color getBack() {
        return back;
    }

    @Deprecated
    public void setBack(Color back) {
        this.back = back;
    }

    public class BackSetter implements Setter<Color> {
        @Override
        public void set(Color color) {
            back = color;
        }
    }

    public Color getColor(boolean isFront) {
        return (isFront) ? front : back;
    }

}
