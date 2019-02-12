/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.HashSet;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class OriFace {
    private ArrayList<OriHalfEdge> halfEdges = new ArrayList<>();
    private HashSet<OriHalfEdge> auxLines = new HashSet<OriHalfEdge>();

    private boolean faceFront = true;

    private GeneralPath outline = null;

    private AffineTransform transform = null;

    public void addHalfEdge(OriHalfEdge he) {
        halfEdges.add(he);
    }

    public void makeHalfedgeLoop() {
        int heNum = halfEdges.size();
        for (int i = 0; i < heNum; i++) {
            OriHalfEdge pre_he = halfEdges.get((i - 1 + heNum) % heNum);
            OriHalfEdge he = halfEdges.get(i);
            OriHalfEdge nxt_he = halfEdges.get((i + 1) % heNum);
            he.setNext(nxt_he);
            he.setPrev(pre_he);
        }
    }

    public void addAuxLine(OriHalfEdge he) {
        auxLines.add(he);
    }

    public void setOutline(double scale) {
        outline = new GeneralPath();
        Vector2d centerP = new Vector2d();
        for (OriHalfEdge he : halfEdges) {
            centerP.add(he.getSv());
        }
        centerP.scale(1.0 / halfEdges.size());

        outline.moveTo(
                (float) (halfEdges.get(0).getSv().x * scale
                        + centerP.x
                                * (1.0 - scale)),
                (float) (halfEdges.get(0).getSv().y * scale
                        + centerP.y
                                * (1.0 - scale)));
        for (int i = 1; i < halfEdges.size(); i++) {
            float x = (float) (halfEdges.get(i).getSv().x * scale
                    + centerP.x
                            * (1.0 - scale));
            float y = (float) (halfEdges.get(i).getSv().y * scale
                    + centerP.y
                            * (1.0 - scale));
            outline.lineTo(
                    x,
                    y);
        }
        outline.closePath();
    }

    public boolean isFaceFront() {
        return this.faceFront;
    }

    public void setFaceFront(boolean faceFront) {
        this.faceFront = faceFront;
    }

    public ArrayList<OriHalfEdge> getHalfEdges() {
        return this.halfEdges;
    }

    public void setHalfEdges(ArrayList<OriHalfEdge> halfEdges) {
        this.halfEdges = halfEdges;
    }

    public HashSet<OriHalfEdge> getAuxLines() {
        return this.auxLines;
    }

    public void setAuxLines(HashSet<OriHalfEdge> auxLines) {
        this.auxLines = auxLines;
    }

    public GeneralPath getOutline() {
        return this.outline;
    }

    public AffineTransform getTransform() {
        return this.transform;
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

}
