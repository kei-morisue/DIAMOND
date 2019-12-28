/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import diamond.Config;
import diamond.controller.Context;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.fold.Folder;
import diamond.model.symbol.Symbol;
import diamond.model.symbol.arrow.ArrowFlip;
import diamond.model.symbol.arrow.ArrowFoldUnfold;
import diamond.view.ui.screen.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class CpBuilder {
    public static Cp buildSquare() {
        Cp cp = new Cp();
        double size = Config.PAPER_SIZE;
        Vertex v0 = new Vertex(size, size);
        Vertex v1 = new Vertex(-size, size);
        Vertex v2 = new Vertex(-size, -size);
        Vertex v3 = new Vertex(size, -size);

        HalfEdge he0 = new HalfEdge(v0, v1, EdgeType.CUT);
        HalfEdge he1 = new HalfEdge(v1, v2, EdgeType.CUT);
        HalfEdge he2 = new HalfEdge(v2, v3, EdgeType.CUT);
        HalfEdge he3 = new HalfEdge(v3, v0, EdgeType.CUT);

        Face f0 = new Face();
        cp.getFaces().add(f0);
        f0.add(he0);
        f0.add(he1);
        f0.add(he2);
        f0.add(he3);

        he0.connectTo(he1);
        he1.connectTo(he2);
        he2.connectTo(he3);
        he3.connectTo(he0);

        he0.getPair().connectTo(he3.getPair());
        he3.getPair().connectTo(he2.getPair());
        he2.getPair().connectTo(he1.getPair());
        he1.getPair().connectTo(he0.getPair());
        return cp;
    }

    public static Cp buildNext(Context context, Cp cp0) {
        Cp cp1 = copyCp(cp0);
        HashMap<HalfEdge, Symbol<HalfEdge>> symbolsHalfEdge = cp0
                .getSymbolsHalfEdge();
        settleCp(cp1);
        cp1.getSymbolsHalfEdge().clear();
        Folder.fold(cp1);
        if (reverse(cp1, symbolsHalfEdge)) {
            return cp1;
        }
        return cp1;
    }

    private static void settleCp(Cp cp) {
        ArrayList<HalfEdge> copy = new ArrayList<HalfEdge>(
                cp.getUnsettlEdges());
        HashMap<HalfEdge, Symbol<HalfEdge>> symbols = cp.getSymbolsHalfEdge();

        while (!copy.isEmpty()) {
            HalfEdge he = copy.get(0);
            if (he.getType() == EdgeType.CREASE) {
                copy.remove(he);
                continue;
            }
            if (he.getType() == EdgeType.UNSETTLED_MOUNTAIN
                    || he.getType() == EdgeType.UNSETTLED_VALLEY) {
                Symbol<HalfEdge> symbol = symbols.get(he);
                if (symbol != null) {
                    if (symbol.getClass() == ArrowFoldUnfold.class) {
                        he.unfold();
                        copy.remove(0);
                        continue;
                    }
                }
                if (HalfEdgeModifier.settle(cp, he)) {
                    copy.remove(he);
                    //TODO
                } else {
                    copy.remove(he);
                    copy.add(he);
                    //TODO
                }
            }
        }
    }

    private static Cp copyCp(Cp cp0) {
        Cp cp1 = new Cp();
        cp1.setTransform(new ScreenTransform(cp0.getTransform()));
        HashMap<Vertex, Vertex> vMap = new HashMap<Vertex, Vertex>();
        for (Face f0 : cp0.getFaces()) {
            Face f1 = buildFace(vMap, f0);
            cp1.add(f1);
            if (f0 == cp0.buildBaseFace()) {
                cp1.setBaseFace(f1);
            }
        }
        HashMap<HalfEdge, Symbol<HalfEdge>> symbols0 = cp0.getSymbolsHalfEdge();
        HashMap<HalfEdge, Symbol<HalfEdge>> symbols1 = cp1.getSymbolsHalfEdge();
        for (HalfEdge he0 : symbols0.keySet()) {
            Symbol<HalfEdge> symbol0 = symbols0.get(he0);
            HalfEdge he1 = vMap.get(he0.getV0())
                    .getConnection(vMap.get(he0.getV1()));
            he1.disablePair();
            symbol0.set(he1);
            symbols1.put(he1, symbol0);
        }
        return cp1;
    }

    private static Face buildFace(HashMap<Vertex, Vertex> vMap, Face f0) {
        Face f1 = new Face();
        f1.getProperty().setDisabled(f0.getProperty().isDisabled());
        for (HalfEdge he : f0.getUnsettledLines()) {
            HalfEdge he1 = buildHalfEdge(vMap, he);
            f1.add(he1);
        }
        ArrayList<HalfEdge> halfEdges = new ArrayList<HalfEdge>();
        for (HalfEdge he : f0.getSortedEdges()) {
            HalfEdge he1 = buildHalfEdge(vMap, he);
            f1.add(he1);
            halfEdges.add(he1);
        }
        int size = halfEdges.size();
        for (int i = 0; i < size; i++) {
            halfEdges.get(i).connectTo(halfEdges.get((i - 1 + size) % (size)));
            halfEdges.get((i + 1) % (size)).connectTo(halfEdges.get(i));
        }
        return f1;
    }

    private static HalfEdge buildHalfEdge(HashMap<Vertex, Vertex> vMap,
            HalfEdge he) {
        Vertex v0 = buildVertex(vMap, he.getV0());
        Vertex v1 = buildVertex(vMap, he.getV1());
        HalfEdge he1;
        if (v0.getConnection(v1) == null) {
            he1 = new HalfEdge(v0, v1, he.getType());
        } else {
            he1 = v0.getConnection(v1);
        }
        return he1;
    }

    private static Vertex buildVertex(HashMap<Vertex, Vertex> vMap, Vertex v) {
        if (!vMap.containsKey(v)) {
            Vertex v0 = new Vertex(v);
            vMap.put(v, v0);
            return v0;

        } else {
            return vMap.get(v);
        }
    }

    private static boolean reverse(Cp cp,
            HashMap<HalfEdge, Symbol<HalfEdge>> symbolsHalfEdge) {
        for (Symbol<HalfEdge> symbol : symbolsHalfEdge.values()) {
            if (symbol.getClass() == ArrowFlip.class) {
                Collections.reverse(cp.getFaces());
                return true;
            }
        }
        return false;
    }
}
