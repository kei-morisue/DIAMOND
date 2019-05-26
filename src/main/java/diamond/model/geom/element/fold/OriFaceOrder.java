/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
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

    public void save(Cp cp) {
        centerPoints.clear();
        List<OriFace> faces = cp.getOriModel().getFaces();
        for (OriFace face : faces) {
            centerPoints.add(OriFaceUtil.getCenterPoint(face));
        }
    }

    public void order(Cp cp) {
        List<OriFace> faces = cp.getOriModel().getFaces();
        ArrayList<OriFace> ordered = new ArrayList<>();
        for (Double c1 : centerPoints) {
            for (int i = 0; i < faces.size(); i++) {
                OriFace face0 = faces.get(i);
                OriVertex c0 = OriFaceUtil.getCenterPoint(face0);
                if (c0.distance(c1) < Constants.EPS) {
                    ordered.add(face0);
                    faces.remove(i);
                    i--;
                }
            }
        }
        for (OriFace face : faces) {
            ordered.add(face);
        }
        faces.clear();
        faces.addAll(ordered);
    }

    public ArrayList<Double> getCenterPoints() {
        return this.centerPoints;
    }

    public void setCenterPoints(ArrayList<Double> centerPoints) {
        this.centerPoints = centerPoints;
    }
}
