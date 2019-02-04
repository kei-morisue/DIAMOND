/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette;

import diamond.model.palette.cp.CreasePattern;

/**
 * @author long_
 *
 */
public class CreasePatternHolder {
    private static CreasePattern creasePattern = new CreasePattern();
    private static CreasePattern creasePatternBUffered = new CreasePattern();

    public static CreasePattern getCP() {
        return creasePattern;
    }

    public static CreasePattern getCreasePatternBUffered() {
        return creasePatternBUffered;
    }

}
