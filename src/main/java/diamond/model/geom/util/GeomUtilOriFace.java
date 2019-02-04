/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

/**
 * @author long_
 *
 */
public class GeomUtilOriFace {
    public final static double EPS = 1.0e-6;

    //
    //    public static boolean isContainsPointFoldedFace(OriFace face, Vector2d v,
    //            double eps) {
    //
    //        int heNum = face.halfedges.size();
    //
    //        // If its on the faces edge, return false
    //        for (int i = 0; i < heNum; i++) {
    //            OriHalfedge he = face.halfedges.get(i);
    //            if (GeomUtil_.DistancePointToSegment(v, he.positionAfterFolded,
    //                    he.next.positionAfterFolded) < eps) {
    //                return false;
    //            }
    //        }
    //
    //        OriHalfedge baseHe = face.halfedges.get(0);
    //        boolean baseFlg = GeomUtil_.CCWcheck(baseHe.positionAfterFolded,
    //                baseHe.next.positionAfterFolded, v);
    //
    //        for (int i = 1; i < heNum; i++) {
    //            OriHalfedge he = face.halfedges.get(i);
    //            if (GeomUtil_.CCWcheck(he.positionAfterFolded,
    //                    he.next.positionAfterFolded, v) != baseFlg) {
    //                return false;
    //            }
    //        }
    //
    //        return true;
    //    }

    //    private static int whichSide(Triangle tri, Vector2d P, Vector2d D) {
    //
    //        // Vertices are projected to the form P+t*D.  Return value is +1 if all
    //        // t > 0, -1 if all t < 0, 0 otherwise, in which case the line splits the
    //        // triangle.
    //
    //        int iPositive = 0, iNegative = 0, iZero = 0;
    //
    //        for (int i = 0; i < 3; i++) {
    //            Vector2d vi_p = new Vector2d();
    //            vi_p.set(tri.p[i].x - P.x, tri.p[i].y - P.y);
    //            double fT = D.dot(vi_p);
    //
    //            if (fT > 0.0f) {
    //                iPositive++;
    //            } else if (fT < 0.0f) {
    //                iNegative++;
    //            } else {
    //                iZero++;
    //            }
    //
    //            if (iPositive > 0 && iNegative > 0) {
    //                return 0;
    //            }
    //        }
    //
    //        return (iZero == 0 ? (iPositive > 0 ? 1 : -1) : 0);
    //    }

    //    public static boolean isFaceOverlap(OriFace face0, OriFace face1,
    //            double eps) {
    //        Vector2d center0 = new Vector2d();
    //        Vector2d center1 = new Vector2d();
    //
    //        // If the vertices of face0 are on face1, true
    //        for (OriHalfedge he : face0.halfedges) {
    //            if (isContainsPointFoldedFace(face1, he.positionAfterFolded, eps)) {
    //                return true;
    //            }
    //            center0.add(he.positionAfterFolded);
    //        }
    //
    //        // If the vertices of face1 are on face0, true
    //        for (OriHalfedge he : face1.halfedges) {
    //            if (isContainsPointFoldedFace(face0, he.positionAfterFolded, eps)) {
    //                return true;
    //            }
    //            center1.add(he.positionAfterFolded);
    //        }
    //
    //        center0.scale(1.0 / face0.halfedges.size());
    //        // If the gravity center of face0 is on face1, true
    //        if (isContainsPointFoldedFace(face1, center0, eps)) {
    //            return true;
    //        }
    //
    //        center1.scale(1.0 / face1.halfedges.size());
    //        // If the gravity center of face1 is on face0, true
    //        if (isContainsPointFoldedFace(face0, center1, eps)) {
    //            return true;
    //        }
    //
    //        // If the outline of face0 intersects face1`s, true
    //        for (OriHalfedge he0 : face0.halfedges) {
    //            if (isLineCrossFace(face1, he0, eps)) {
    //                return true;
    //            }
    //        }
    //
    //        for (OriHalfedge he1 : face1.halfedges) {
    //            if (isLineCrossFace(face0, he1, eps)) {
    //                return true;
    //            }
    //        }
    //        return false;
    //    }
    //
    //    public static boolean isLineCrossFace(OriFace face, OriHalfedge heg,
    //            double eps) {
    //        Vector2d p1 = heg.positionAfterFolded;
    //        Vector2d p2 = heg.next.positionAfterFolded;
    //        Vector2d dir = new Vector2d();
    //        dir.sub(p2, p1);
    //        Line heLine = new Line(p1, dir);
    //
    //        for (OriHalfedge he : face.halfedges) {
    //            // About the relationship  of each outline`s segment
    //
    //            if (GeomUtil_.DistancePointToLine(he.positionAfterFolded,
    //                    heLine) < eps
    //                    && GeomUtil_.DistancePointToLine(he.next.positionAfterFolded,
    //                            heLine) < eps) {
    //                return false;
    //            }
    //        }
    //        Vector2d preCrossPoint = null;
    //        for (OriHalfedge he : face.halfedges) {
    //            Vector2d cp = GeomUtil_.getCrossPoint(he.positionAfterFolded,
    //                    he.next.positionAfterFolded, heg.positionAfterFolded,
    //                    heg.next.positionAfterFolded);
    //            if (cp == null) {
    //                continue;
    //            }
    //
    //            if (preCrossPoint == null) {
    //                preCrossPoint = cp;
    //            } else {
    //                if (GeomUtil_.Distance(cp, preCrossPoint) > eps) {
    //                    // Intersects at least in two places
    //                    return true;
    //                }
    //            }
    //        }
    //        // If at least one of the endpoints is fully contained
    //        if (GeomUtil_.isContainsPointFoldedFace(face, heg.positionAfterFolded,
    //                eps)) {
    //            return true;
    //        }
    //        if (GeomUtil_.isContainsPointFoldedFace(face,
    //                heg.next.positionAfterFolded, eps)) {
    //            return true;
    //        }
    //        return false;
    //    }
}
