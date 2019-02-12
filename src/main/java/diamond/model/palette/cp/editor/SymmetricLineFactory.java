package diamond.model.palette.cp.editor;

import java.util.Collection;
import java.util.LinkedList;

import javax.vecmath.Vector2d;

import diamond.model.geom.Constants;
import diamond.model.geom.element.Line;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.util.CrossPointUtil;
import diamond.model.geom.util.DistanceUtil;
import diamond.model.geom.util.GeomUtil;

public class SymmetricLineFactory {

    private class BestPair {
        private OriLine bestLine = null;
        private Vector2d bestPoint = null;

        /**
         * @return bestLine
         */
        public OriLine getBestLine() {
            return bestLine;
        }

        /**
         * @param bestLine bestLineを登録する
         */
        public void setBestLine(OriLine bestLine) {
            this.bestLine = bestLine;
        }

        /**
         * @return bestPoint
         */
        public Vector2d getBestPoint() {
            return bestPoint;
        }

        /**
         * @param bestPoint bestPointを登録する
         */
        public void setBestPoint(Vector2d bestPoint) {
            this.bestPoint = bestPoint;
        }

    }

    /**
     * v1-v2 is the symmetry line, v0-v1 is the subject to be copied.
     * @param v0
     * @param v1
     * @param v2
     * @param creasePattern
     * @throws PainterCommandFailedException
     */
    public OriLine createSymmetricLine(
            Vector2d v0, Vector2d v1, Vector2d v2,
            Collection<OriLine> creasePattern, LineType type) {

        BestPair pair = findBestPair(v0, v1, v2, creasePattern);

        Vector2d bestPoint = pair.getBestPoint();

        return new OriLine(v1, bestPoint, type);
    }

    /**
     * Core logic for creating symmetric line
     * @param v0
     * @param v1
     * @param v2
     * @param creasePattern
     * @return
     * @throws PainterCommandFailedException
     */
    private BestPair findBestPair(
            Vector2d v0, Vector2d v1, Vector2d v2,
            Collection<OriLine> creasePattern) {
        BestPair bestPair = new BestPair();

        Vector2d v3 = GeomUtil.getSymmetricPoint(v0, v1, v2);
        Line ray = new Line(v1, new Vector2d(v3.x - v1.x, v3.y - v1.y));

        double minDist = Double.MAX_VALUE;
        for (OriLine l : creasePattern) {
            Vector2d crossPoint = CrossPointUtil.getCrossPoint(ray,
                    l.getSegment());
            if (crossPoint == null) {
                continue;
            }
            double distance = DistanceUtil.Distance(crossPoint, v1);
            if (distance < Constants.POINT_EPS) {
                continue;
            }

            if (distance < minDist) {
                minDist = distance;
                bestPair.setBestPoint(crossPoint);
                bestPair.setBestLine(l);
            }
        }

        if (bestPair.getBestPoint() == null) {
            System.out.println("failed to find the terminal of symmetric line");
        }

        return bestPair;
    }

    /**
     * This method generates possible rebouncing of the fold.
     *
     * @param v0 terminal point of the line to be copied
     * @param v1 connecting point of symmetry line and the line to be copied.
     * @param v2 terminal point of symmetry line
     * @param startV
     * @param creasePattern
     *
     * @return a collection of auto walk line
     * @throws PainterCommandFailedException
     */
    public Collection<OriLine> createSymmetricLineAutoWalk(
            Vector2d v0, Vector2d v1, Vector2d v2, Vector2d startV,
            Collection<OriLine> creasePattern, LineType type) {

        LinkedList<OriLine> autoWalkLines = new LinkedList<>();

        addSymmetricLineAutoWalk(v0, v1, v2, 0, startV, creasePattern,
                autoWalkLines, type);

        return autoWalkLines;
    }

    /**
     * add new symmetric line to {@code autoWalkLines} recursively.
     * @param v0
     * @param v1
     * @param v2
     * @param stepCount
     * @param startV
     * @param creasePattern
     * @param autoWalkLines
     * @throws PainterCommandFailedException
     */
    private void addSymmetricLineAutoWalk(
            Vector2d v0, Vector2d v1, Vector2d v2, int stepCount,
            Vector2d startV,
            Collection<OriLine> creasePattern,
            Collection<OriLine> autoWalkLines,
            LineType type) {

        //FIXME this method does not detect loop path. it causes meaningless recursion.

        stepCount++;
        if (stepCount > 36) {
            return;
        }

        BestPair pair = findBestPair(v0, v1, v2, creasePattern);

        Vector2d bestPoint = pair.getBestPoint();
        if (bestPoint == null) {
            return;
        }
        OriLine bestLine = pair.getBestLine();

        OriLine autoWalk = new OriLine(
                v1, bestPoint, type);

        autoWalkLines.add(autoWalk);

        if (DistanceUtil.Distance(bestPoint,
                startV) < Constants.POINT_EPS) {
            return;
        }

        addSymmetricLineAutoWalk(
                v1, bestPoint, bestLine.p0, stepCount, startV,
                creasePattern, autoWalkLines, type);

    }

}
