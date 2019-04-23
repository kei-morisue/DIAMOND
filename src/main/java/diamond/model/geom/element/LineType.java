package diamond.model.geom.element;

public enum LineType {
    CREASE,
    UNSETTLED_VALLEY,
    UNSETTLED_MOUNTAIN,
    CUT,
    VALLEY,
    MOUNTAIN;

    public static LineType getPairType(LineType lineType) {
        switch (lineType) {
        case MOUNTAIN:
            return LineType.VALLEY;
        case VALLEY:
            return LineType.MOUNTAIN;
        case UNSETTLED_MOUNTAIN:
            return LineType.UNSETTLED_VALLEY;
        case UNSETTLED_VALLEY:
            return LineType.UNSETTLED_MOUNTAIN;
        default:
            return lineType;
        }
    }

    public static LineType getAuxType(LineType lineType) {
        switch (lineType) {
        case MOUNTAIN:
            return LineType.UNSETTLED_MOUNTAIN;
        case VALLEY:
            return LineType.UNSETTLED_VALLEY;
        case UNSETTLED_MOUNTAIN:
            return LineType.MOUNTAIN;
        case UNSETTLED_VALLEY:
            return LineType.VALLEY;
        default:
            return lineType;
        }
    }
}
