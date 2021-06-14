/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.cyborg.geom.d1.Edge;
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
public class Cp<T extends F<T>> implements Drawable<T> {
    protected ArrayList<Face<T>> faces = new ArrayList<>();
    protected HashSet<Edge<T>> links = new HashSet<>();

    @Deprecated
    public Cp() {
    }

    public Cp(Step<T> step) {
        //TODO
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale, boolean isPointed) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public <S extends Graphic<T>> S find(
            Finder<T, S> finder,
            double x,
            double y,
            double scale) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
