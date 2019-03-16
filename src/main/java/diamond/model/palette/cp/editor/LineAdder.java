package diamond.model.palette.cp.editor;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.CrossPointUtil;
import diamond.model.geom.util.DistanceUtil;
import diamond.model.geom.util.LineUtil;

public class LineAdder {
    private class PointComparatorX implements Comparator<OriPoint> {

        @Override
        public int compare(OriPoint v1, OriPoint v2) {
            if (v1.x == v2.x) {
                return 0;
            }
            return v1.x > v2.x ? 1 : -1;
        }
    }

    private class PointComparatorY implements Comparator<OriPoint> {

        @Override
        public int compare(OriPoint v1, OriPoint v2) {
            if (v1.y == v2.y) {
                return 0;
            }
            return ((OriPoint) v1).y > ((OriPoint) v2).y ? 1 : -1;
        }
    }

    /**
     *
     * @param inputLine
     * @param currentLines
     * @return true.
     */
    private boolean divideCurrentLines(OriLine inputLine,
            Collection<OriLine> currentLines) {

        LinkedList<OriLine> toBeAdded = new LinkedList<>();

        for (Iterator<OriLine> iterator = currentLines.iterator(); iterator
                .hasNext();) {
            OriLine line = iterator.next();
            Point2D.Double crossPoint = CrossPointUtil.getCrossPoint(inputLine,
                    line);
            if (crossPoint == null) {
                continue;
            }

            iterator.remove();

            if (DistanceUtil.Distance(line.p0,
                    crossPoint) > Constants.EPS) {
                toBeAdded.add(new OriLine(line.p0, crossPoint, line.getType()));
            }

            if (DistanceUtil.Distance(line.p1,
                    crossPoint) > Constants.EPS) {
                toBeAdded.add(new OriLine(line.p1, crossPoint, line.getType()));
            }

        }

        for (OriLine line : toBeAdded) {
            currentLines.add(line);
        }

        return true;
    }

    private List<OriPoint> createInputLinePoints(OriLine inputLine,
            Collection<OriLine> currentLines) {
        ArrayList<OriPoint> points = new ArrayList<OriPoint>();
        points.add(inputLine.p0);
        points.add(inputLine.p1);

        for (OriLine line : currentLines) {

            if (DistanceUtil.Distance(inputLine.p0,
                    line.p0) < Constants.EPS ||
                    DistanceUtil.Distance(inputLine.p0,
                            line.p1) < Constants.EPS
                    ||
                    DistanceUtil.Distance(inputLine.p1,
                            line.p0) < Constants.EPS
                    ||
                    DistanceUtil.Distance(inputLine.p1,
                            line.p1) < Constants.EPS) {
                continue;
            }

            if (DistanceUtil.DistancePointToSegment(line.p0, inputLine.p0,
                    inputLine.p1) < Constants.EPS) {
                points.add(line.p0);
            }
            if (DistanceUtil.DistancePointToSegment(line.p1, inputLine.p0,
                    inputLine.p1) < Constants.EPS) {
                points.add(line.p1);
            }

            // Calculates the intersection
            OriPoint crossPoint = CrossPointUtil.getCrossPoint(inputLine, line);
            if (crossPoint != null) {
                points.add(crossPoint);
            }

        }

        return points;
    }

    /**
     * Adds a new OriLine, also searching for intersections with others
     * that would cause their mutual division
     *
     * @param inputLine
     * @param currentLines	current line list. it will be affected as
     * 						new lines are added and unnecessary lines are removed.
     */

    public void addLine(OriLine inputLine, Collection<OriLine> currentLines) {
        for (OriLine line : currentLines) {
            if (LineUtil.isSameLineSegment(line, inputLine)) {
                return;
            }
        }

        divideCurrentLines(inputLine, currentLines);

        List<OriPoint> points = createInputLinePoints(inputLine, currentLines);

        // sort in order to make points sequential
        boolean sortByX = Math.abs(inputLine.p0.x - inputLine.p1.x) > Math
                .abs(inputLine.p0.y - inputLine.p1.y);
        if (sortByX) {
            Collections.sort(points, new PointComparatorX());
        } else {
            Collections.sort(points, new PointComparatorY());
        }

        OriPoint prePoint = points.get(0);

        // add new lines sequentially
        for (int i = 1; i < points.size(); i++) {
            OriPoint p = points.get(i);
            // remove very short line
            if (DistanceUtil.Distance(prePoint,
                    p) < Constants.EPS) {
                continue;
            }

            currentLines.add(new OriLine(prePoint, p, inputLine.getType()));
            prePoint = p;
        }

    }

    /**
     *
     * @param lines        lines to be added
     * @param destination  collection as a destination
     */
    public void addAll(Collection<OriLine> lines,
            Collection<OriLine> destination) {
        for (OriLine line : lines) {
            addLine(line, destination);
        }
    }
}
