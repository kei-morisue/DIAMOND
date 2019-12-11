/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

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
    public static void buildSquare(LinkedList<Face> faces) {
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
        faces.add(f0);
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

    }

    public static Cp buildNext(Context context, Cp cp0) {
        Cp cp1 = new Cp();
        copyFaces(cp0, cp1);
        HashMap<HalfEdge, Symbol<HalfEdge>> symbolsHalfEdge = cp0
                .getSymbolsHalfEdge();
        ArrayList<HalfEdge> copy = new ArrayList<HalfEdge>(cp0.getHalfEdges());
        while (!copy.isEmpty()) {
            HalfEdge he = copy.get(0);
            if (!EdgeType.isSettled(he.getType())) {
                Symbol<HalfEdge> symbol = symbolsHalfEdge.get(he);
                if (symbol != null) {
                    if (symbol.getClass() == ArrowFoldUnfold.class) {
                        he.unfold();
                        copy.remove(0);
                        continue;
                    }
                }
                if (HalfEdgeModifier.settle(cp1, he)) {
                    //TODO
                }
            }
        }
        Folder.fold(cp1);
        reverse(cp1, symbolsHalfEdge);
        return cp1;//TODO
    }

    private static void copyFaces(Cp cp0, Cp cp1) {
        cp1.setTransform(new ScreenTransform(cp0.getTransform()));
        for (Face face : cp0.getFaces()) {
            Face f1 = new Face(face);
            cp1.add(f1);
            for (HalfEdge he : face.getHalfEdges()) {
                f1.add(new HalfEdge(he.getV0(), he.getV1(), he.getType()));
            }
            for (HalfEdge he : face.getUnsettledLines()) {
                f1.add(new HalfEdge(he.getV0(), he.getV1(), he.getType()));
            }
            if (face == cp0.getBaseFace()) {
                cp1.setBaseFace(face);
            }
        }
    }

    private static void reverse(Cp cp,
            HashMap<HalfEdge, Symbol<HalfEdge>> symbolsHalfEdge) {
        for (Symbol<HalfEdge> symbol : symbolsHalfEdge.values()) {
            if (symbol.getClass() == ArrowFlip.class) {
                Collections.reverse(cp.getFaces());
                return;
            }
        }
    }
}
