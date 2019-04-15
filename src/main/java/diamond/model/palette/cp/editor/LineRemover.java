package diamond.model.palette.cp.editor;

import java.util.ArrayList;
import java.util.Collection;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.DistanceUtil;
import diamond.model.geom.util.OriLineUtil;

public class LineRemover {
    public static void removeLine(
            OriLine l, Collection<OriLine> creasePattern) {
        creasePattern.remove(l);
        // merge the lines if possible, to prevent unnecessary vertexes
        merge2LinesAt(l.p0, creasePattern);
        merge2LinesAt(l.p1, creasePattern);
    }

    /**
     * remove vertex from crease pattern
     * @param v
     * @param creasePattern
     */
    public static void removeVertex(
            OriPoint v, Collection<OriLine> creasePattern) {

        merge2LinesAt(v, creasePattern);
    }

    private static void merge2LinesAt(
            OriPoint p, Collection<OriLine> creasePattern) {

        ArrayList<OriLine> sharedLines = new ArrayList<OriLine>();
        for (OriLine line : creasePattern) {
            if (DistanceUtil.Distance(line.p0, p) < 0.001
                    || DistanceUtil.Distance(line.p1, p) < 0.001) {
                sharedLines.add(line);
            }
        }

        if (sharedLines.size() != 2) {
            return;
        }

        OriLine l0 = sharedLines.get(0);
        OriLine l1 = sharedLines.get(1);

        if (l0.getType() != l1.getType()) {
            return;
        }

        // Check if the lines have the same angle
        OriPoint dir0 = l0.p1.sub(l0.p0);
        OriPoint dir1 = l1.p1.sub(l1.p0);

        dir0.normalize();
        dir1.normalize();

        if (!OriLineUtil.isParallel(dir0, dir1)) {
            return;
        }

        // Merge possibility found
        OriPoint p0 = new OriPoint();
        OriPoint p1 = new OriPoint();

        if (DistanceUtil.Distance(l0.p0, p) < 0.001) {
            p0.x = l0.p1.x;
            p0.y = l0.p1.y;
        } else {
            p0.x = l0.p0.x;
            p0.y = l0.p0.y;
        }
        if (DistanceUtil.Distance(l1.p0, p) < 0.001) {
            p1.x = l1.p1.x;
            p1.y = l1.p1.y;
        } else {
            p1.x = l1.p0.x;
            p1.y = l1.p0.y;
        }

        creasePattern.remove(l0);
        creasePattern.remove(l1);
        OriLine li = new OriLine(p0, p1, l0.getType());
        creasePattern.add(li);
    }

}
