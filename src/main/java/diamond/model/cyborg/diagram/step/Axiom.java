/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.awt.Graphics2D;
import java.util.LinkedList;

import diamond.model.cyborg.geom.d1.Crease;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.Drawable;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.cyborg.graphics.find.Finder;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class Axiom<T extends F<T>> implements Drawable<T> {
    private LinkedList<Crease<T>> creases;
    private LinkedList<Face<T>> faces;
    private boolean isUnfold = false;

    public Axiom(boolean isUnfold) {
        this.isUnfold = isUnfold;
    }

    public void add(Face<T> face, Crease<T> crease) {
        faces.add(face);
        creases.add(crease);
    }

    public void fold(Step<T> step) {
        for (int i = 0; i < faces.size(); i++) {
            if (isUnfold) {
                faces.get(i).cut(creases.get(i), step);
            } else {
                faces.get(i).add(creases.get(i));
                faces.get(i).cut(creases.get(i), step);
            }
        }
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(S screen, Graphics2D g2d,
            float scale, boolean isPointed) {
        for (Crease<T> c : creases) {
            c.draw(screen, g2d, scale, true);
        }
    }

    @Override
    public <S extends Graphic<T>> S find(Finder<T, S> finder, double x,
            double y, double scale) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }
}
