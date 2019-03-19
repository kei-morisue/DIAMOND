/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import diamond.model.geom.Constants;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.util.DistanceUtil;
import diamond.model.geom.util.OriFaceUtil;
import diamond.model.palette.cp.simplifier.DuplicatedCPSimplifier;

/**
 * @author long_
 *
 */
public class OriModel {
    private LinkedList<OriFace> faces = new LinkedList<OriFace>();
    private Set<OriVertex> vertices = new HashSet<OriVertex>();
    private Set<OriHalfEdge> auxLines = new HashSet<OriHalfEdge>();
    private OriFace darkside = null;
    private OriVertex originPoint = new OriVertex(0.0, 0.0);

    public OriModel(Cp cp) {
        DuplicatedCPSimplifier.simplify(cp);
        setOriginPoint(cp.getOrigin());
        buildVertices(cp);
        buildFaces();
        buildAuxLines();
    }

    /**
     *
     */
    private void buildAuxLines() {
        for (OriHalfEdge aux : auxLines) {
            for (OriFace face : faces) {
                if (OriFaceUtil.onFace(face, aux)) {
                    face.addAuxLine(aux);
                }
            }
        }

    }

    private void buildFaces() {
        for (OriVertex vertex : vertices) {
            vertex.setFoldability();
            for (OriHalfEdge he : vertex.getHalfEdges()) {
                if (he.getFace() != null) {
                    continue;
                }
                OriFace face = new OriFace();
                OriHalfEdge walkHe = he;
                do {
                    face.addHalfEdge(walkHe);
                    walkHe.setFace(face);
                    walkHe = walkHe.getEv().getPrevEdge(walkHe.getPair());
                } while (walkHe.getSv() != vertex);
                if (isCut(face) && darkside == null) {
                    darkside = face;
                    continue;
                }
                faces.add(face);
                face.makeHalfedgeLoop();
                face.setOutline(0.5);
            }
        }
    }

    private boolean isCut(OriFace face) {
        for (OriHalfEdge he : face.getHalfEdges()) {
            if (he.getType() != LineType.CUT) {
                return false;
            }
        }
        return true;
    }

    private void buildVertices(Cp cp) {
        for (OriLine line : cp.getLines()) {
            OriVertex v0 = new OriVertex(line.p0);
            OriVertex v1 = new OriVertex(line.p1);
            v0 = add(v0);
            v1 = add(v1);
            OriHalfEdge he0 = new OriHalfEdge(v0, v1, line.getType());
            OriHalfEdge he1 = new OriHalfEdge(v1, v0, line.getType());
            he0.setPair(he1);
            he1.setPair(he0);
            if (LineType.isAux(line.getType())) {
                auxLines.add(he0);
                continue;
            }
            v0.addEdge(he0);
            v1.addEdge(he1);
        }
    }

    private OriVertex add(OriVertex v) {
        for (OriVertex oriVertex : vertices) {
            if (DistanceUtil.Distance(v, oriVertex) < Constants.EPS) {
                return oriVertex;
            }
        }
        vertices.add(v);
        return v;
    }

    public LinkedList<OriFace> getFaces() {
        return this.faces;
    }

    public Set<OriVertex> getVertices() {
        return this.vertices;
    }

    public Set<OriHalfEdge> getAuxLines() {
        return this.auxLines;
    }

    public OriVertex getOriginPoint() {
        return this.originPoint;
    }

    public void setOriginPoint(Point2D.Double originPoint) {
        this.originPoint = new OriVertex(originPoint.x, originPoint.y);
    }

}
