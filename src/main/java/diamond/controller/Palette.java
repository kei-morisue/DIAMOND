/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.Vector;

import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.style.FaceStyle;
import diamond.view.ui.screen.style.HalfEdgeStyle;

/**
 * @author Kei Morisue
 *
 */
public class Palette {
    private Vector<Cp> cps = new Vector<>();
    private FaceStyle faceStyle = new FaceStyle();
    private HalfEdgeStyle haFaceStyle = new HalfEdgeStyle();

    public Palette() {
        Cp cp = new Cp();
        cps.add(cp);

    }

    public Vector<Cp> getCps() {
        return this.cps;
    }

    public void setCps(Vector<Cp> cps) {
        this.cps = cps;
    }
}