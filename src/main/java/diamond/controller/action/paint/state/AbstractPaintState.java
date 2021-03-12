/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.paint.state;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractPaintState extends AbstractState {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
