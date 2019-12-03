/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

/**
 * @author Kei Morisue
 *
 */
public enum EdgeType {
    CREASE, UNSETTLED_VALLEY, UNSETTLED_MOUNTAIN, CUT, VALLEY, MOUNTAIN;

    public static EdgeType getPairType(EdgeType type) {
        switch (type) {
        case MOUNTAIN:
            return VALLEY;
        case VALLEY:
            return MOUNTAIN;
        case UNSETTLED_MOUNTAIN:
            return UNSETTLED_VALLEY;
        case UNSETTLED_VALLEY:
            return UNSETTLED_MOUNTAIN;
        default:
            return type;
        }
    }

    public static boolean isSettled(EdgeType type) {
        switch (type) {
        case CREASE:
        case UNSETTLED_MOUNTAIN:
        case UNSETTLED_VALLEY:
            return false;
        default:
            return true;
        }
    }

    public static EdgeType getAnotherType(EdgeType type) {
        switch (type) {
        case MOUNTAIN:
            return UNSETTLED_MOUNTAIN;
        case VALLEY:
            return UNSETTLED_VALLEY;
        case UNSETTLED_MOUNTAIN:
            return MOUNTAIN;
        case UNSETTLED_VALLEY:
            return VALLEY;
        default:
            return type;
        }
    }
}
