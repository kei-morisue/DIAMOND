/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.file;

import java.awt.Color;

import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.color.OriFaceColor;

/**
 * @author long_
 *
 */
public class StyleSet {
    private Color oriFaceFront;
    private Color oriFaceBack;
    private int clipScale;

    public StyleSet() {
    }

    public void write() {
        oriFaceBack = OriFaceColor.ORI_FACE_BACK;
        oriFaceFront = OriFaceColor.ORI_FACE_FRONT;
        clipScale = LineStyle.CLIP_SCALE;

    }

    public void apply() {
        OriFaceColor.ORI_FACE_BACK = oriFaceBack;
        OriFaceColor.ORI_FACE_FRONT = oriFaceFront;
        LineStyle.CLIP_SCALE = clipScale;
    }

    public Color getOriFaceFront() {
        return this.oriFaceFront;
    }

    public void setOriFaceFront(Color oriFaceFront) {
        this.oriFaceFront = oriFaceFront;
    }

    public Color getOriFaceBack() {
        return this.oriFaceBack;
    }

    @Deprecated
    public void setOriFaceBack(Color oriFaceBack) {
        this.oriFaceBack = oriFaceBack;
    }

    @Deprecated
    public int getClipScale() {
        return this.clipScale;
    }

    @Deprecated
    public void setClipScale(int clipScale) {
        this.clipScale = clipScale;
    }

}
