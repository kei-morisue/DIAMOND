/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenCp;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class FaceDrawer {
    //    private static Color front = Color.GRAY;
    //    private static Color back = Color.WHITE;
    //    private static final Color POINTED = Color.GREEN;

    public static <T extends F<T>> void draw(
            ScreenModel<T> screen,
            Graphics2D g2d,
            LinkedList<Link<T>> edges,
            HashSet<Seg<T>> creases) {
        g2d.setColor(Color.white);//TODO
        g2d.fill(ShapeBuilder.build(Link.vers(edges)));
        for (Link<T> edge : edges) {
            edge.draw(screen, g2d);
        }
        for (Seg<T> crease : creases) {
            crease.draw(screen, g2d);
        }
    }

    public static <T extends F<T>> void draw(
            ScreenCp<T> screen,
            Graphics2D g2d,
            LinkedList<Link<T>> edges,
            HashSet<Seg<T>> creases) {
        //        // TODO 自動生成されたメソッド・スタブ
        //        Ver<T> wer = vers.getLast();
        //        g2d.setColor(front);//TODO
        //        g2d.fill(ShapeBuilder.build(vers));
        //        for (Seg<T> crease : creases) {
        //            crease.draw(screen, vers, g2d);
        //        }
        //        for (Ver<T> ver : vers) {
        //            new Link<T>(null, null, wer, ver).draw(screen, g2d);
        //            wer = ver;
        //        }

    }
}
