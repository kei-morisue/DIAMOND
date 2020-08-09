/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

/**
 * @author Kei Morisue
 *
 */
public class SegmentModifier {
    //    public static void add(Context context, Point2D.Double p0,
    //            Point2D.Double p1) {
    //        EdgeType type = context.getInputType();
    //        Cp cp = context.getCp();
    //        addImpl(p0, p1, type, cp);
    //        addFollowing(context, p0, p1, type, cp);
    //    }

    //    private static void addFollowing(Context context, Point2D.Double p0,
    //            Point2D.Double p1, EdgeType type, Cp cp) {
    //        Vector<Cp> cps = context.getPalette().getCps();
    //        int i0 = cps.indexOf(cp);
    //        if (i0 == cps.size() - 1) {
    //            return;
    //        }
    //        if (EdgeType.isSettled(type)) {
    //            return;
    //        }
    //        if (JOptionPane.showConfirmDialog(context.getPaintScreen(),
    //                Labels.get("across_line")) != 0) {
    //            return;
    //        }
    //        for (int i = i0 + 1; i < cps.size(); i++) {
    //            Cp cp1 = cps.get(i);
    //            addImpl(p0, p1, EdgeType.CREASE, cp1);
    //            Folder.fold(cp1);
    //        }
    //    }

    //    private static void addImpl(Point2D.Double p0, Point2D.Double p1,
    //            EdgeType type, Cp cp) {
    //        for (Face face : cp.getFaces()) {
    //            ArrayList<HalfEdge> externals = CrossPointUtil.splitOutLines(face,
    //                    p0, p1);
    //            ArrayList<Vertex> internals = CrossPointUtil
    //                    .splitUnsettledLines(face, p0, p1);
    //            buildHalfEdges(externals, internals, face, type);
    //        }
    //    }
    //
    //    private static void buildHalfEdges(ArrayList<HalfEdge> externals,
    //            ArrayList<Vertex> internals, Face face, EdgeType type) {
    //        if (externals.size() == 0) {
    //            if (internals.size() == 0) {
    //                return;
    //            }
    //            internals.sort(new NormComparator(internals.get(0)));
    //            HalfEdgeConnector.connect(face, internals, type);
    //            return;
    //        }
    //        Vertex v0 = externals.get(0).getV0();
    //        internals.add(0, v0);
    //        if (externals.size() == 2) {
    //            Vertex v1 = externals.get(1).getV0();
    //            internals.add(v1);
    //            internals.sort(new NormComparator(v0));
    //        }
    //        internals.sort(new NormComparator(internals.get(0)));
    //        HalfEdgeConnector.connect(face, internals, type);
    //    }
    //
    //    public static Vertex inside(Face face, Point2D.Double p0,
    //            Point2D.Double p1) {
    //        if (FaceUtil.onFace(face, p0)) {
    //            return find(face, p0);
    //        }
    //        if ((FaceUtil.onFace(face, p1))) {
    //            return find(face, p1);
    //        }
    //        return null;
    //    }
    //
    //    private static Vertex find(Face face, Point2D.Double p) {
    //        for (HalfEdge halfEdge : face.getUnsettledLines()) {
    //            Vertex v0 = halfEdge.getV0();
    //            Vertex v1 = halfEdge.getV1();
    //            if (Fuzzy.around(v0.distance(p), 0.0)) {
    //                return v0;
    //            }
    //            if (Fuzzy.around(v1.distance(p), 0.0)) {
    //                return v1;
    //            }
    //        }
    //        return new Vertex(p);
    //    }
    //
    //    public static boolean settle(Cp cp, HalfEdge he) {
    //        Face face = he.getFace();
    //        HalfEdge h0 = null;
    //        HalfEdge h1 = null;
    //        Vertex v0 = he.getV0();
    //        Vertex v1 = he.getV1();
    //        for (HalfEdge h : face.getSortedEdges()) {
    //            if (h.getV0() == v0) {
    //                h1 = h;
    //            }
    //            if (h.getV0() == v1) {
    //                h0 = h;
    //            }
    //        }
    //        if (h0 == null && h1 == null) {
    //            return false;
    //        }
    //        if (h0 != null && h1 != null) {
    //            FaceSplitter.split(cp, face, he, h0, h1);
    //            return true;
    //        }
    //        if (h0 != null) {
    //            FaceCutter.cut(face, he.getPair());
    //        } else {
    //            FaceCutter.cut(face, he);
    //        }
    //        return true;
    //    }
    //
    //    public static void unSettle(Cp cp, HalfEdge he) {
    //        Face f0 = he.getFace();
    //        HalfEdge hP = he.getPair();
    //        Face f1 = hP.getFace();
    //        if (f0 != f1) {
    //            FaceMarger.marge(cp, he);
    //        } else {
    //            if (he.getNext() == hP) {
    //                FaceMarger.unCut(he);
    //            } else if (hP.getNext() == he) {
    //                FaceMarger.unCut(hP);
    //            } else {
    //                he.unSettle();
    //            }
    //        }
    //    }
    //
    //    public static void remove(Cp cp, HalfEdge he) {
    //        if (he.getType() == EdgeType.CUT) {
    //            return;
    //        }
    //        if (EdgeType.isSettled(he.getType())) {
    //            HalfEdgeModifier.unSettle(cp, he);
    //        }
    //        he.getFace().remove(he);
    //        Vertex v0 = he.getV0();
    //        Vertex v1 = he.getV1();
    //        v0.remove(he);
    //        v1.remove(he.getPair());
    //        VertexRemover.remove(cp, v0);
    //        VertexRemover.remove(cp, v1);
    //    }
    //
    //    public static Vertex split(Point2D.Double p0, Point2D.Double p1,
    //            HalfEdge he) {
    //        double[] ds = CrossPointUtil.getSplitter(p0, p1, he);
    //        if (ds == null) {
    //            return null;
    //        }
    //        if (Fuzzy.around(ds[1], 1.0)) {
    //            return he.getV1();
    //        }
    //        if (Fuzzy.around(ds[1], 0.0)) {
    //            return he.getV0();
    //        }
    //        return split(he, ds[1]).getV0();
    //    }
    //
    //    public static HalfEdge split(HalfEdge he, double t) {
    //        Double p = Point2DUtil.split(he.getV0(), he.getV1(), t);
    //        Vertex v = new Vertex(p);
    //        return split(he, v);
    //    }
    //
    //    private static HalfEdge split(HalfEdge he, Vertex v) {
    //        Vertex v0 = he.getV0();
    //        if (v0 == v) {
    //            return he;
    //        }
    //        Vertex v1 = he.getV1();
    //        if (v1 == v) {
    //            return null;
    //        }
    //        v1.remove(he.getPair());
    //        v1.remove(he);
    //        v0.remove(he.getPair());
    //        v0.remove(he);
    //        HalfEdge h0 = new HalfEdge(v0, v, he.getType());
    //        HalfEdge h1 = new HalfEdge(v, v1, he.getType());
    //        Face f = he.getFace();
    //        HalfEdge heP = he.getPair();
    //        HalfEdge h0P = h0.getPair();
    //        HalfEdge h1P = h1.getPair();
    //        manageFace(he, h0, h1, f);
    //        manageFace(heP, h0P, h1P, heP.getFace());
    //        link(he, h0, h1);
    //        link(heP, h1P, h0P);
    //        return h1;
    //    }
    //
    //    private static void link(HalfEdge he, HalfEdge h0, HalfEdge h1) {
    //        h0.connectTo(h1);
    //        if (he.getPrev() != null && he.getNext() != null) {
    //            he.getPrev().connectTo(h0);
    //            h1.connectTo(he.getNext());
    //        }
    //    }
    //
    //    private static void manageFace(HalfEdge he, HalfEdge h0, HalfEdge h1,
    //            Face f) {
    //        if (f == null) {
    //            return;
    //        }
    //        f.add(h0);
    //        f.add(h1);
    //        f.remove(he);
    //    }
}
