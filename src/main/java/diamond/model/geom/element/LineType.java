package diamond.model.geom.element;

public enum LineType {
    AUX,
    AUX_VALLEY,
    AUX_MOUNTAIN,
    CUT,
    VALLEY,
    MOUNTAIN;

    public static boolean isAux(LineType lineType) {
        if (lineType == LineType.AUX) {
            return true;
        }
        if (lineType == LineType.AUX_MOUNTAIN) {
            return true;
        }
        if (lineType == LineType.AUX_VALLEY) {
            return true;
        }
        return false;
    }

    public static LineType getPairType(LineType lineType) {
        switch (lineType) {
        case MOUNTAIN:
            return LineType.VALLEY;
        case VALLEY:
            return LineType.MOUNTAIN;
        case AUX_MOUNTAIN:
            return LineType.AUX_VALLEY;
        case AUX_VALLEY:
            return LineType.AUX_MOUNTAIN;
        default:
            return lineType;
        }
    }
}
