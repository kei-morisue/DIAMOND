/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.origami;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import diamond.model.geom.Constants;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.fold.Folder;
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
    private Set<OriHalfEdge> unsettledLines = new HashSet<OriHalfEdge>();
    private OriFace darkside = null;
    private OriFace baseFace = null;

    public OriModel(Cp cp) {
        build(cp);
    }

    public void build(Cp cp) {
        faces.clear();
        vertices.clear();
        auxLines.clear();
        unsettledLines.clear();
        baseFace = null;
        darkside = null;
        DuplicatedCPSimplifier.simplify(cp);
        buildVertices(cp);
        buildFaces();
        buildAuxLines();
        fold();
    }

    /**
     *
     */
    public void fold() {
        if (baseFace == null) {
            baseFace = faces.get(0);
        }
        Folder.fold(this);
        faces.sort(new OriFaceComparator(faces));
    }

    /**
     *
     */
    private void buildAuxLines() {
        for (OriHalfEdge aux : auxLines) {
            for (OriFace face : faces) {
                HashSet<OriHalfEdge> lines = face.getAuxLines();
                if (OriFaceUtil.onFace(face, aux)) {
                    lines.add(aux);
                }
            }
        }
        for (OriHalfEdge aux : unsettledLines) {
            for (OriFace face : faces) {
                HashSet<OriHalfEdge> lines = face.getUnettledLines();
                if (OriFaceUtil.onFace(face, aux)) {
                    lines.add(aux);
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
            if (line.getType() == LineType.CREASE) {
                auxLines.add(he0);
                continue;
            }
            if (line.getType() == LineType.UNSETTLED_MOUNTAIN
                    || line.getType() == LineType.UNSETTLED_VALLEY) {
                unsettledLines.add(he0);
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

    public OriFace getBaseFace() {
        return this.baseFace;
    }

    public void setBaseFace(OriFace baseFace) {
        this.baseFace = baseFace;
    }

}
