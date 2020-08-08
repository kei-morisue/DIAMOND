/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.mouse;

import java.util.Stack;

import diamond.model.cyborg.geom.Cyborg;

/**
 * @author Kei Morisue
 *
 */
public class PickerCyborg<T extends Cyborg> {
    private Stack<T> picked = new Stack<T>();

    public void initialize() {
        picked.clear();
    }

    public void add(T t) {
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
}
