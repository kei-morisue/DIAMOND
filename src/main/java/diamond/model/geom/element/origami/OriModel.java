/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.origami;

import java.awt.geom.Point2D.Double;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import diamond.model.geom.Constants;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;
import diamond.model.geom.element.fold.Folder;
import diamond.model.geom.element.fold.OriFaceSorter;
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
    private boolean isFlip = false;

    public OriModel() {
    }

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
        isFlip = false;
        DuplicatedCPSimplifier.simplify(cp);
        buildVertices(cp);
        buildFaces();
        findBaseFace(cp);
        buildAuxLines();
        fold();
        liftArrows();
    }

    private void liftArrows() {
        for (OriFace face : faces) {
            for (OriHalfEdge he : face.getHalfEdges()) {
                if (he.getArrow() != null) {
                    OriHalfEdge pair = he.getPair();
                    OriFace f0 = pair.getFace();
                    int i = faces.indexOf(face);
                    int i0 = faces.indexOf(f0);
                    if (i < i0) {
                        pair.setArrow(he.getArrow());
                        he.setArrow(null);
                    }
                }
            }
        }

    }

    public void fold() {
        Folder.fold(this);
        faces = OriFaceSorter.sort(faces);
    }

    private void findBaseFace(Cp cp) {
        Double c = cp.getBaseFaceCenter();
        for (OriFace face : faces) {
            if (OriFaceUtil.onFace(face, c)) {
                baseFace = face;
                cp.setBaseFaceCenter(OriFaceUtil.getCenterPoint(baseFace));
                return;
            }
        }
        baseFace = faces.get(0);
        cp.setBaseFaceCenter(OriFaceUtil.getCenterPoint(baseFace));
    }

    private void buildAuxLines() {
        for (OriHalfEdge aux : auxLines) {
            for (OriFace face : faces) {
                HashSet<OriHalfEdge> lines = face.getCreaseLines();
                if (OriFaceUtil.onFace(face, aux)) {
                    lines.add(aux);
                }
            }
        }
        for (OriHalfEdge aux : unsettledLines) {
            for (OriFace face : faces) {
                HashSet<OriHalfEdge> lines = face.getUnsettledLines();
                if (OriFaceUtil.onFace(face, aux)) {
                    lines.add(aux);
                }
            }
        }

    }

    private void buildFaces() {
        cleanVertex();
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
                face.buildOutline(0.5);
            }
        }
    }

    /**
     *
     */
    private void cleanVertex() {
        HashSet<OriVertex> vs = new HashSet<OriVertex>();
        for (OriVertex vertex : vertices) {
            if (vertex.getHalfEdges().size() == 2) {
                OriHalfEdge h0 = vertex.getHalfEdges().get(0);
                OriHalfEdge h1 = vertex.getHalfEdges().get(1);
                double dt = Math.abs(h0.getAngle() - h1.getPair().getAngle());
                if (h0.getType() == h1.getType() && dt < Constants.RADIAN_EPS) {
                    OriVertex p0 = h0.getEv();
                    OriVertex p1 = h1.getEv();
                    AbstractArrow arrow = (h0.getArrow() == null)
                            ? h1.getArrow()
                            : h0.getArrow();
                    OriHalfEdge h2 = new OriHalfEdge(p0, p1, h0.getType(),
                            arrow);
                    OriHalfEdge h3 = new OriHalfEdge(p1, p0, h0.getType());
                    h2.setPair(h3);
                    h3.setPair(h2);
                    p0.getHalfEdges().remove(h0.getPair());
                    p1.getHalfEdges().remove(h1.getPair());
                    p0.addEdge(h2);
                    p1.addEdge(h3);
                } else {
                    vs.add(vertex);
                }
            } else {
                vs.add(vertex);
            }
        }
        vertices = vs;
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
            OriHalfEdge he0 = new OriHalfEdge(v0, v1, line.getType(),
                    line.getArrow());
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
            if (DistanceUtil.distance(v, oriVertex) < Constants.EPS) {
                return oriVertex;
            }
        }
        vertices.add(v);
        return v;
    }

    public boolean isFlip() {
        return isFlip;
    }

    public void flip() {
        isFlip = !isFlip;
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

    @Deprecated
    public Set<OriHalfEdge> getUnsettledLines() {
        return this.unsettledLines;
    }

    @Deprecated
    public void setUnsettledLines(Set<OriHalfEdge> unsettledLines) {
        this.unsettledLines = unsettledLines;
    }

    @Deprecated
    public OriFace getDarkside() {
        return this.darkside;
    }

    @Deprecated
    public void setDarkside(OriFace darkside) {
        this.darkside = darkside;
    }

    @Deprecated
    public void setFaces(LinkedList<OriFace> faces) {
        this.faces = faces;
    }

    @Deprecated
    public void setVertices(Set<OriVertex> vertices) {
        this.vertices = vertices;
    }

    @Deprecated
    public void setAuxLines(Set<OriHalfEdge> auxLines) {
        this.auxLines = auxLines;
    }

    @Deprecated
    public void setFlip(boolean isFlip) {
        this.isFlip = isFlip;
    }

}
