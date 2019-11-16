/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.ArrayList;

import diamond.model.Cp;

/**
 * @author Kei Morisue
 *
 */
public class Pallete {
    private ArrayList<Cp> cps = new ArrayList<>();

    public Pallete() {
        cps.add(new Cp());
    }

    public ArrayList<Cp> getCps() {
        return this.cps;
    }

    public void setCps(ArrayList<Cp> cps) {
        this.cps = cps;
    }
}
