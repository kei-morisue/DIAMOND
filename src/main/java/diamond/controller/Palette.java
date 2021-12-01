/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.Vector;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.fold.Folder;
import diamond.model.cyborg.util.CpBuilder;
import diamond.view.ui.screen.style.FaceStyle;
import diamond.view.ui.screen.style.HalfEdgeStyle;
import diamond.view.ui.screen.style.PageStyle;

/**
 * @author Kei Morisue
 *
 */
public class Palette {
    private Vector<Cp> cps = new Vector<>();
    private FaceStyle faceStyle = new FaceStyle();
    private HalfEdgeStyle halfEdgeStyle = new HalfEdgeStyle();
    private PageStyle pageStyle = new PageStyle();

    public Palette() {
        Cp cp = CpBuilder.buildSquare();
        Folder.fold(cp);
        cps.add(cp);
    }

    public Palette(int i) {
        Cp cp = CpBuilder.buildHex();
        Folder.fold(cp);
        cps.add(cp);
    }

    public Palette(Palette palette) {
        this.cps = palette.getCps();
        this.faceStyle = palette.faceStyle;
        this.halfEdgeStyle = palette.halfEdgeStyle;
        this.pageStyle = palette.pageStyle;

    }

    public Vector<Cp> getCps() {
        return this.cps;
    }

    public void setCps(Vector<Cp> cps) {
        this.cps = cps;
    }

    @Deprecated
    public FaceStyle getFaceStyle() {
        return faceStyle;
    }

    @Deprecated
    public void setFaceStyle(FaceStyle faceStyle) {
        this.faceStyle = faceStyle;
    }

    @Deprecated
    public HalfEdgeStyle getHalfEdgeStyle() {
        return halfEdgeStyle;
    }

    @Deprecated
    public void setHalfEdgeStyle(HalfEdgeStyle halfEdgeStyle) {
        this.halfEdgeStyle = halfEdgeStyle;
    }

    @Deprecated
    public PageStyle getPageStyle() {
        return pageStyle;
    }

    @Deprecated
    public void setPageStyle(PageStyle pageStyle) {
        this.pageStyle = pageStyle;
    }
}
