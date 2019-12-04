/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.LinkedList;

import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.style.FaceStyle;
import diamond.view.ui.screen.style.HalfEdgeStyle;

/**
 * @author Kei Morisue
 *
 */
public class Pallete {
    private LinkedList<Cp> cps = new LinkedList<>();
    private FaceStyle faceStyle = new FaceStyle();
    private HalfEdgeStyle haFaceStyle = new HalfEdgeStyle();

    public Pallete() {
        Cp cp = new Cp();
        cps.add(cp);

    }

    public LinkedList<Cp> getCps() {
        return this.cps;
    }

    public void setCps(LinkedList<Cp> cps) {
        this.cps = cps;
    }
}
