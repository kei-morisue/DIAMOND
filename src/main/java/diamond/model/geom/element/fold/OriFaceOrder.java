/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.geom.util.OriFaceUtil;

/**
 * @author long_
 *
 */
public class OriFaceOrder {
    private ArrayList<Double> centerPoints = new ArrayList<>();

    public OriFaceOrder() {
    }

    public OriFaceOrder(OriFaceOrder order) {
        for (Double c : order.centerPoints) {
            this.centerPoints.add(c);
        }
    }

    public void save(Cp cp) {
        centerPoints.clear();
        List<OriFace> faces = cp.getOriModel().getFaces();
        for (OriFace face : faces) {
            centerPoints.add(OriFaceUtil.getCenterPoint(face));
        }
    }

    public void load(Cp cp) {
        List<OriFace> faces = cp.getOriModel().getFaces();
        ArrayList<OriFace> ordered = new ArrayList<>();
        for (Double c1 : centerPoints) {
            for (OriFace face : faces) {
                OriVertex c0 = OriFaceUtil.getCenterPoint(face);
                if (c0.distance(c1) < Constants.EPS) {
                    ordered.add(face);
                    continue;
                }
            }
        }
        for (OriFace face : faces) {
            if (ordered.indexOf(face) == -1) {
                ordered.add(face);
            }
        }
        faces.clear();
        faces.addAll(ordered);
    }

    public ArrayList<Double> getCenterPoints() {
        return this.centerPoints;
    }

    public void flip() {
        Collections.reverse(centerPoints);
    }

    public void setCenterPoints(ArrayList<Double> centerPoints) {
        this.centerPoints = centerPoints;
    }
}
