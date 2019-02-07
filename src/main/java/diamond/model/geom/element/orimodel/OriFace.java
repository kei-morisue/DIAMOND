/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class OriFace {
    public ArrayList<OriHalfEdge> halfEdges = new ArrayList<>();
    public boolean selected = false;
    public boolean convex = false;

    public GeneralPath outline = new GeneralPath();
    public GeneralPath preOutline = new GeneralPath();

    public void setOutline() {
        outline.reset();
        outline.moveTo((float) (halfEdges.get(0).getVertex().getP().x),
                (float) (halfEdges.get(0).getVertex().getP().y));
        for (int i = 1; i < halfEdges.size(); i++) {
            outline.lineTo((float) (halfEdges.get(i).getVertex().getP().x),
                    (float) (halfEdges.get(i).getVertex().getP().y));
        }
        outline.closePath();
    }

    public void setPreviewOutline() {
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
}
