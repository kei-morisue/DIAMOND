/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.graphics.Graphics;
import diamond.model.cyborg.style.StyleSegment;
import diamond.view.ui.screen.ScreenMain;
import diamond.view.ui.screen.ScreenStep;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class PickerCyborg<T extends Cyborg> implements Graphics {
    private Stack<T> picked = new Stack<T>();

    public void initialize() {
        picked.clear();
    }

    public void add(T t) {
        if (picked.contains(t)) {
            picked.remove(t);
            return;
        }
        picked.add(t);
    }

    public void pop() {
        if (picked.isEmpty()) {
            return;
        }
        picked.pop();
    }

    public Stack<T> get() {
        return picked;
    }

    @Override
    public void draw(Graphics2D g2d, ScreenMain screen) {
        for (T t : picked) {
            t.draw(g2d, screen);
        }
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenMain screen) {
        Diagram diagram = screen.diagram();
        g2d.setColor(Color.GREEN);
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setStroke(styleSegment
                .strokePointed((float) G2DUtil.getScale(g2d)));
    }

    @Override
    public void draw(Graphics2D g2d, ScreenStep screen) {
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenStep screen) {
    }

}
