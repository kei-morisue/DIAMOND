/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

/**
 * @author Kei Morisue
 *
 */
public class FaceCutter {
    //    public static void cut(Face f, HalfEdge cutter) {
    //        f.remove(cutter);
    //        cutter.settle();
    //        Vertex v0 = cutter.getV0();
    //        HalfEdge h0 = open(f, v0);
    //        if (h0 == null) {
    //            return;
    //        }
    //        HalfEdge hP = cutter.getPair();
    //        hP.connectTo(h0.getNext());
    //        h0.connectTo(cutter);
    //        cutter.connectTo(hP);
    //        f.add(cutter);
    //        f.add(hP);
    //    }
    //
    //    private static HalfEdge open(Face face, Vertex v0) {
    //        for (HalfEdge he : face.getSortedEdges()) {
    //            if (he.getV1() == v0) {
    //                return he;
    //            }
    //        }
    //        return null;
    //    }
    //
    //    public static void marge(Cp cp, HalfEdge h0) {
    //        HalfEdge h1 = h0.getPair();
    //        margeConnect(h0, h1);
    //        Face face = new Face();
    //        Face f0 = buildFace(h0, face);
    //        Face f1 = buildFace(h1, face);
    //        h0.unSettle();
    //        face.add(h0);
    //        cp.remove(f0);
    //        cp.remove(f1);
    //        cp.add(face);
    //    }
    //
    //    public static void unCut(HalfEdge he) {
    //        Face face = he.getFace();
    //        face.remove(he);
    //        HalfEdge hP = he.getPair();
    //        face.remove(hP);
    //        he.getPrev().connectTo(hP.getNext());
    //        he.unSettle();
    //        face.add(he);
    //    }
    //
    //    private static Face buildFace(HalfEdge h0, Face face) {
    //        Face f0 = h0.getFace();
    //        f0.remove(h0);
    //        face.add(f0.getSortedEdges());
    //        face.add(f0.getUnsettledLines());
    //        return f0;
    //    }
    //
    //    private static void margeConnect(HalfEdge h0, HalfEdge h1) {
    //        HalfEdge h0N = h0.getNext();
    //        HalfEdge h0P = h0.getPrev();
    //        HalfEdge h1N = h1.getNext();
    //        HalfEdge h1P = h1.getPrev();
    //        h1P.connectTo(h0N);
    //        h0P.connectTo(h1N);
    //    }
    //
    //    public static void split(Cp cp, Face face, HalfEdge splitter, HalfEdge h0,
    //            HalfEdge h1) {
    //        face.remove(splitter);
    //        splitter.settle();
    //        Face f0 = new Face(face);
    //        Face f1 = new Face(face);
    //        Vertex v0 = splitter.getV0();
    //        Vertex v1 = splitter.getV1();
    //        open(f0, splitter, h0);
    //        open(f1, splitter.getPair(), h1);
    //        close(f0, v0, splitter);
    //        close(f1, v1, splitter.getPair());
    //        distribute(f0, f1, face);
    //        cp.add(f0);
    //        cp.add(f1);
    //        cp.remove(face);
    //    }
    //
    //    private static void distribute(Face f0, Face f1, Face face) {
    //        HashSet<HalfEdge> unsettledLines = face.getUnsettledLines();
    //        for (HalfEdge he : unsettledLines) {
    //            if (he.getProperty().isDisabled()) {
    //                continue;
    //            }
    //            if (FaceUtil.onFace(f0, he)) {
    //                f0.add(he);
    //            } else {
    //                f1.add(he);
    //            }
    //        }
    //    }
    //
    //    private static void open(Face face, HalfEdge splitter, HalfEdge h0) {
    //        face.add(h0);
    //        splitter.connectTo(h0);
    //    }
    //
    //    private static void close(Face face, Vertex endV, HalfEdge splitter) {
    //        HalfEdge h = face.getSortedEdges().get(0);
    //        do {
    //            h = h.getNext();
    //            face.add(h);
    //        } while (h.getV1() != endV);
    //        face.add(splitter);
    //        h.connectTo(splitter);
    //    }
    //
    //    public static void marge(Cp cp, HalfEdge h0) {
    //        HalfEdge h1 = h0.getPair();
    //        margeConnect(h0, h1);
    //        Face face = new Face();
    //        Face f0 = buildFace(h0, face);
    //        Face f1 = buildFace(h1, face);
    //        h0.unSettle();
    //        face.add(h0);
    //        cp.remove(f0);
    //        cp.remove(f1);
    //        cp.add(face);
    //    }
    //
    //    public static void unCut(HalfEdge he) {
    //        Face face = he.getFace();
    //        face.remove(he);
    //        HalfEdge hP = he.getPair();
    //        face.remove(hP);
    //        he.getPrev().connectTo(hP.getNext());
    //        he.unSettle();
    //        face.add(he);
    //    }
    //
    //    private static Face buildFace(HalfEdge h0, Face face) {
    //        Face f0 = h0.getFace();
    //        f0.remove(h0);
    //        face.add(f0.getSortedEdges());
    //        face.add(f0.getUnsettledLines());
    //        return f0;
    //    }
    //
    //    private static void margeConnect(HalfEdge h0, HalfEdge h1) {
    //        HalfEdge h0N = h0.getNext();
    //        HalfEdge h0P = h0.getPrev();
    //        HalfEdge h1N = h1.getNext();
    //        HalfEdge h1P = h1.getPrev();
    //        h1P.connectTo(h0N);
    //        h0P.connectTo(h1N);
    //    }
}
