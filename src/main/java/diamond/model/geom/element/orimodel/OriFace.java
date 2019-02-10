/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.HashSet;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class OriFace {
    public ArrayList<OriHalfEdge> halfEdges = new ArrayList<>();
    public HashSet<OriEdge> auxLines = new HashSet<OriEdge>();
    public boolean selected = false;
    public boolean convex = false;

    private boolean faceFront = true;
    private boolean tmpFlg = false;
    private int z_order = 0;
    private int tmpInt = 0;

    public GeneralPath outline = new GeneralPath();
    public GeneralPath preOutline = new GeneralPath();

    public void addHalfEdge(OriVertex v, OriEdge e) {
        OriHalfEdge he = new OriHalfEdge(v, e, this);
        halfEdges.add(he);
    }

    public void setOutline(OriVertex v, OriEdge e) {
        outline.moveTo((float) (halfEdges.get(0).getVertex().getP().x),
                (float) (halfEdges.get(0).getVertex().getP().y));
        for (int i = 1; i < halfEdges.size(); i++) {
            outline.lineTo((float) (halfEdges.get(i).getVertex().getP().x),
                    (float) (halfEdges.get(i).getVertex().getP().y));
        }
        outline.closePath();
    }

    public void setPreviewOutline(OriVertex v, OriEdge e) {
        preOutline.reset();
        Vector2d centerP = new Vector2d();
        for (OriHalfEdge he : halfEdges) {
            centerP.add(he.getVertex().getP());
        }
        centerP.scale(1.0 / halfEdges.size());
        double rate = 0.5;

        preOutline.moveTo(
                (float) (halfEdges.get(0).getVertex().getP().x * rate
                        + centerP.x
                                * (1.0 - rate)),
                (float) (halfEdges.get(0).getVertex().getP().y * rate
                        + centerP.y
                                * (1.0 - rate)));
        for (int i = 1; i < halfEdges.size(); i++) {
            float x = (float) (halfEdges.get(i).getVertex().getP().x * rate
                    + centerP.x
                            * (1.0 - rate));
            float y = (float) (halfEdges.get(i).getVertex().getP().y * rate
                    + centerP.y
                            * (1.0 - rate));
            preOutline.lineTo(
                    x,
                    y);
        }
        preOutline.closePath();
    }

    public Vector2d getCenter() {
        Vector2d centerVec = new Vector2d();
        for (OriHalfEdge he : halfEdges) {
            centerVec.add(he.getVertex().getP());
        }
        centerVec.scale(1.0 / halfEdges.size());
        return centerVec;
    }

    public boolean isFaceFront() {
        return this.faceFront;
    }

    public void setFaceFront(boolean faceFront) {
        this.faceFront = faceFront;
    }

    public boolean isTmpFlg() {
        return this.tmpFlg;
    }

    public void setTmpFlg(boolean tmpFlg) {
        this.tmpFlg = tmpFlg;
    }

    public int getZ_order() {
        return this.z_order;
    }

    public void setZ_order(int z_order) {
        this.z_order = z_order;
    }

    public int getTmpInt() {
        return this.tmpInt;
    }

    public void setTmpInt(int tmpInt) {
        this.tmpInt = tmpInt;
    }
}
