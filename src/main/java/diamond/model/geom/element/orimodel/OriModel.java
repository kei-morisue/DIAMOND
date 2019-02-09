/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2d;

import diamond.model.geom.Constants;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.orimodel.util.OriModelUtil;
import diamond.model.geom.util.DistanceUtil;
import diamond.model.palette.cp.CreasePattern;

/**
 * @author long_
 *
 */
public class OriModel {
    private List<OriFace> faces = new ArrayList<OriFace>();
    private List<OriVertex> vertices = new ArrayList<OriVertex>();
    private List<OriEdge> edges = new ArrayList<OriEdge>();
    private boolean isFolded = false;

    public OriModel(CreasePattern cp) {
        addOriVertices(cp);
        addOriFaces();
    }

    private void addOriFaces() {
        for (OriVertex v : vertices) {
            for (OriEdge e : v.getEdges()) {
                if (e.getType() == LineType.CUT) {
                    continue;
                }
                if (OriModelUtil.faceExistsOnLeft(v, e)) {
                    continue;
                }
                makeGraph(v, e);
            }
        }
        // unfolded paper case
        if (faces.size() == 0) {
            for (OriVertex v : vertices) {
                for (OriEdge e : v.getEdges()) {
                    makeGraph(v, e);
                }
            }
        }
    }

    private OriFace makeGraph(OriVertex v, OriEdge e) {
        OriFace face = OriModelUtil.makeOriFaceOnLeft(v, e);
        faces.add(face);
        OriModelUtil.makePairsAndEdges(edges, faces);
        return face;
    }

    private void addOriVertices(CreasePattern cp) {
        for (OriLine l : cp.getLines()) {
            OriVertex sv = addOriVertex(l.p0);
            OriVertex ev = addOriVertex(l.p1);
            OriEdge eg = new OriEdge(sv, ev, l.getType());
            edges.add(eg);
            sv.addEdge(eg);
            ev.addEdge(eg);
        }
    }

    private OriVertex addOriVertex(Vector2d p) {
        OriVertex vtx = null;
        for (OriVertex v : vertices) {
            if (DistanceUtil.Distance(v.getP(), p) < Constants.POINT_EPS) {
                vtx = v;
            }
        }
        if (vtx == null) {
            vtx = new OriVertex(p);
            vertices.add(vtx);
        }
        return vtx;
    }

    public boolean isFolded() {
        return isFolded;
    }

    public List<OriFace> getFaces() {
        return this.faces;
    }

    public List<OriVertex> getVertices() {
        return this.vertices;
    }

    public List<OriEdge> getEdges() {
        return this.edges;
    }
}