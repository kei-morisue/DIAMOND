package diamond.fold;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.vecmath.Vector2d;

import diamond.fold.cp.CollinearCPSimplifier;
import diamond.fold.cp.CreasePatternValidator;
import diamond.fold.cp.DuplicatedCPSimplifier;
import diamond.geom.GeomUtil;
import diamond.value.CalculationResource;
import diamond.value.OriLine;

public class OrigamiModelFactory {
    final public static int LOWER = 2;
    final public static int NO_OVERLAP = 0;
    final public static int UNDEFINED = 9;
    final public static int UPPER = 1;

    private static OriVertex addAndGetVertexFromVVec(
            List<OriVertex> vertices, Vector2d p) {
        for (OriVertex v : vertices) {
            if (GeomUtil.Distance(v.p, p) < CalculationResource.POINT_EPS) {
                return v;
            }
        }
        OriVertex vtx = new OriVertex(p);
        vertices.add(vtx);
        return vtx;
    }

    private static void makeEdges(List<OriEdge> edges, List<OriFace> faces) {
        edges.clear();

        ArrayList<OriHalfedge> tmpHalfedges = new ArrayList<OriHalfedge>();

        // Clear all the Halfedges
        for (OriFace face : faces) {
            for (OriHalfedge he : face.halfedges) {
                he.pair = null;
                he.edge = null;
                tmpHalfedges.add(he);
            }
        }

        // Search the halfedge pair
        int heNum = tmpHalfedges.size();
        for (int i = 0; i < heNum; i++) {
            OriHalfedge he0 = tmpHalfedges.get(i);
            if (he0.pair != null) {
                continue;
            }

            for (int j = i + 1; j < heNum; j++) {
                OriHalfedge he1 = tmpHalfedges.get(j);
                if (he0.vertex == he1.next.vertex
                        && he0.next.vertex == he1.vertex) {
                    OriEdge edge = new OriEdge();
                    he0.pair = he1;
                    he1.pair = he0;
                    he0.edge = edge;
                    he1.edge = edge;
                    edge.sv = he0.vertex;
                    edge.ev = he1.vertex;
                    edge.left = he0;
                    edge.right = he1;
                    edges.add(edge);
                    edge.type = OriLine.TYPE_NONE;//OriEdge.TYPE_NONE;
                }
            }
        }

        // If the pair wasnt found it should be an edge
        for (OriHalfedge he : tmpHalfedges) {
            if (he.pair == null) {
                OriEdge edge = new OriEdge();
                he.edge = edge;
                edge.sv = he.vertex;
                edge.ev = he.next.vertex;
                edge.left = he;
                edges.add(edge);
                edge.type = OriLine.TYPE_CUT;
            }
        }
    }

    public static OrigamiModel createOrigamiModel(
            Collection<OriLine> creasePattern, double paperSize) {
        DuplicatedCPSimplifier.simplify(creasePattern);
        CollinearCPSimplifier.simplify(creasePattern);

        return createOrigamiModelNoCleanup(creasePattern, paperSize);
    }

    /**
     *
     * @param creasePattern
     * @param paperSize
     * @param needCleanUp
     * @return A model data converted from crease pattern.
     */
    //TODO: change as: return OrigamiModel. throw error if creation failed.
    public static OrigamiModel createOrigamiModelNoCleanup(
            Collection<OriLine> creasePattern, double paperSize) {
        // Remove lines with the same position
        int debugCount = 0;
        OrigamiModel origamiModel = new OrigamiModel(paperSize);
        List<OriFace> faces = origamiModel.getFaces();
        List<OriEdge> edges = origamiModel.getEdges();
        List<OriVertex> vertices = origamiModel.getVertices();

        List<OriFace> sortedFaces = origamiModel.getSortedFaces();

        sortedFaces.clear();

        edges.clear();
        vertices.clear();
        faces.clear();

        // Create the edges from the vertexes
        for (OriLine l : creasePattern) {
            if (l.typeVal == OriLine.TYPE_NONE) {
                continue;
            }

            OriVertex sv = addAndGetVertexFromVVec(vertices, l.p0);
            OriVertex ev = addAndGetVertexFromVVec(vertices, l.p1);
            OriEdge eg = new OriEdge(sv, ev, l.typeVal);
            edges.add(eg);
            sv.addEdge(eg);
            ev.addEdge(eg);
        }
        // Construct the faces
        for (OriVertex v : vertices) {

            for (OriEdge e : v.edges) {

                if (e.type == OriLine.TYPE_CUT) {
                    continue;
                }
                if (v == e.sv && e.left != null) {
                    continue;
                }
                if (v == e.ev & e.right != null) {
                    continue;
                }

                OriFace face = new OriFace();
                faces.add(face);
                OriVertex walkV = v;
                OriEdge walkE = e;
                debugCount = 0;
                while (true) {
                    if (debugCount++ > 100) {
                        System.out.println("ERROR");
                        //						throw new UnfoldableModelException("algorithmic error");
                        return origamiModel;
                    }
                    OriHalfedge he = new OriHalfedge(walkV, face);
                    face.halfedges.add(he);
                    he.tmpInt = walkE.type;
                    if (walkE.sv == walkV) {
                        walkE.left = he;
                    } else {
                        walkE.right = he;
                    }
                    walkV = walkE.oppositeVertex(walkV);
                    walkE = walkV.getPrevEdge(walkE);
                    if (walkV == v) {
                        break;
                    }
                }
                face.makeHalfedgeLoop();
                face.setOutline();
                face.setPreOutline();
            }
        }
        if (faces.size() == 0) {
            OriFace face = new OriFace();
            for (OriVertex v : vertices) {
                face.halfedges.add(new OriHalfedge(v, face));

            }
            faces.add(face);
            face.makeHalfedgeLoop();
            face.setOutline();
            face.setPreOutline();
        }

        makeEdges(edges, faces);
        for (OriEdge e : edges) {
            e.type = e.left.tmpInt;
        }

        origamiModel.setHasModel(true);
        origamiModel.setProbablyFoldable(
                CreasePatternValidator.checkProblems(vertices, faces));
        return origamiModel;
    }

}
