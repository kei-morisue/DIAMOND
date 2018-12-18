package diamond.paint.core;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import javax.vecmath.Vector2d;

import diamond.doc.DocHolder;
import diamond.value.OriLine;
import diamond.view.paint.PaintScreen;

public class PaintContext {

    private static PaintContext instance = null;

    public static PaintContext getInstance() {
        if (instance == null) {
            instance = new PaintContext();
        }

        return instance;
    }

    private ArrayList<Vector2d> gridPoints;

    //--------------------------------------------------

    private boolean isPasting = false;

    private static PaintScreen painterScreen = new PaintScreen();

    private boolean missionCompleted = false;
    private Point2D.Double mousePoint;

    private Stack<OriLine> pickedLines = new Stack<>();
    private Stack<Vector2d> pickedVertices = new Stack<>();

    public boolean dispGrid = true;
    public OriLine pickCandidateL = new OriLine();

    public Vector2d pickCandidateV = new Vector2d();

    public double scale;

    private PaintContext() {
    }

    /**
     * remove all lines and all vertices in this context.
     *
     * @param unselect	true if the removed lines should be marked as unselected.
     */
    public void clear(boolean unselect) {

        if (unselect && pickedLines.empty() == false) {
            for (OriLine l : pickedLines) {
                l.selected = false;
            }
        }

        pickedLines.clear();
        pickedVertices.clear();

        pickCandidateL = null;
        pickCandidateV = null;

        missionCompleted = false;
    }

    public void finishPasting() {
        this.isPasting = false;
    }

    public OriLine getLine(int index) {
        return pickedLines.get(index);
    }

    public int getLineCount() {
        return pickedLines.size();
    }

    public Stack<OriLine> getLines() {
        return pickedLines;
    }

    public Point2D.Double getLogicalMousePoint() {
        return mousePoint;
    }

    public Vector2d getVertex(int index) {
        return pickedVertices.get(index);
    }

    public int getVertexCount() {
        return pickedVertices.size();
    }

    public Stack<Vector2d> getVertices() {
        return pickedVertices;
    }

    public boolean isMissionCompleted() {
        return missionCompleted;
    }

    public boolean isPasting() {
        return isPasting;
    }

    public OriLine peekLine() {
        return pickedLines.peek();
    }

    public Vector2d peekVertex() {
        return pickedVertices.peek();
    }

    /**
     * pop the last pushed line and mark it unselected.
     * @return popped line. null if no line is pushed.
     */
    public OriLine popLine() {
        if (pickedLines.empty()) {
            return null;
        }

        OriLine line = pickedLines.pop();
        line.selected = false;
        return line;
    }

    public Vector2d popVertex() {
        if (pickedVertices.empty()) {
            return null;
        }

        return pickedVertices.pop();
    }

    public void pushLine(OriLine picked) {
        //	picked.selected = true;
        pickedLines.push(picked);
    }

    public void pushVertex(Vector2d picked) {
        pickedVertices.push(picked);
    }

    /**
     * performs the same as {@code Vector.remove(Object o)}.
     * @param line
     * @return
     */
    public boolean removeLine(OriLine line) {

        return pickedLines.remove(line);
    }

    public void set(double scale, boolean dispGrid) {
        this.scale = scale;
        this.dispGrid = dispGrid;
    }

    public void setLogicalMousePoint(Point2D.Double logicalPoint) {
        this.mousePoint = logicalPoint;
    }

    public void setMissionCompleted(boolean missionCompleted) {
        this.missionCompleted = missionCompleted;
    }

    public void startPasting() {
        this.isPasting = true;
    }

    public Collection<Vector2d> updateGrids(int gridDivNum) {
        gridPoints = new ArrayList<>();
        double paperSize = DocHolder.getDoc().getPaperSize();

        double step = paperSize / gridDivNum;
        for (int ix = 0; ix < PaintConfig.gridDivNum + 1; ix++) {
            for (int iy = 0; iy < gridDivNum + 1; iy++) {
                double x = -paperSize / 2 + step * ix;
                double y = -paperSize / 2 + step * iy;

                gridPoints.add(new Vector2d(x, y));
            }
        }

        return gridPoints;
    }

    public static PaintScreen getPainterScreen() {
        return getInstance().painterScreen;
    }

    public static void setPainterScreen(PaintScreen __painterScreen) {
        painterScreen = __painterScreen;
    }

}
