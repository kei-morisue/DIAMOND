/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

/**
 * @author Kei Morisue
 *
 */
public enum SegmentType {
    CREASE, CREASE_VALLEY, CREASE_MOUNTAIN, CUT, VALLEY, MOUNTAIN;

    public static SegmentType flip(SegmentType type) {
        switch (type) {
        case MOUNTAIN:
            return VALLEY;
        case VALLEY:
            return MOUNTAIN;
        case CREASE_MOUNTAIN:
            return CREASE_VALLEY;
        case CREASE_VALLEY:
            return CREASE_MOUNTAIN;
        default:
            return type;
        }
    }

    public static boolean isCrease(SegmentType type) {
        switch (type) {
        case CREASE:
        case CREASE_MOUNTAIN:
        case CREASE_VALLEY:
            return false;
        default:
            return true;
        }
    }

    public static SegmentType foldUnfold(SegmentType type) {
        switch (type) {
        case MOUNTAIN:
            return CREASE_MOUNTAIN;
        case VALLEY:
            return CREASE_VALLEY;
        case CREASE_MOUNTAIN:
            return MOUNTAIN;
        case CREASE_VALLEY:
            return VALLEY;
        case CREASE:
            return VALLEY;
        default:
            return type;
        }
    }

}