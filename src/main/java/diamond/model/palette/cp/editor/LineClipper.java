/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp.editor;

import java.util.Set;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.CrossPointUtil;

/**
 * @author long_
 *
 */
public class LineClipper {
    public static OriLine clip(OriLine line, Set<OriLine> cutLines) {
        OriPoint p0, p1;
        p0 = null;
        p1 = null;
        for (OriLine cutLine : cutLines) {
            OriPoint cp = CrossPointUtil.getCrossPoint(line, cutLine);
            if (cp != null) {
                if (p0 == null) {
                    p0 = cp;
                } else {
                    p1 = cp;
                }
            }
        }
        if (p0 != null && p1 != null) {
            return new OriLine(p0, p1, line.getType());
        }
        return line;
    }
}
