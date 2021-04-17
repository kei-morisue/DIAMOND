/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.awt.Graphics2D;
import java.util.LinkedList;

import diamond.model.cyborg.graphics.Drawable;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.cyborg.graphics.find.Finder;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
//TODO
public class Folder<T extends F<T>> implements Drawable<T> {
    private LinkedList<Axiom<T>> axioms;

    public Folder() {
    }

    public void add(Axiom<T> axiom) {
        axioms.add(axiom);
    }

    public void remove(Axiom<T> axiom) {
        axioms.remove(axiom);
    }

    public void fold(Step<T> step) {
        for (Axiom<T> axiom : axioms) {
            axiom.fold(step);
        }
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(S screen, Graphics2D g2d,
            float scale, boolean isPointed) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public <S extends Graphic<T>> S find(Finder<T, S> finder, double x,
            double y, double scale) {
        return null;
    }

    @Deprecated
    public LinkedList<Axiom<T>> getAxioms() {
        return axioms;
    }

    @Deprecated
    public void setAxioms(LinkedList<Axiom<T>> axioms) {
        this.axioms = axioms;
    }
}
