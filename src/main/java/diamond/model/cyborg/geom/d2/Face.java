/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.graphics.FaceDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenCp;

/**
 * @author Kei Morisue
 *
 */
public class Face<T extends F<T>> {
    private LinkedList<Ver<T>> vers = new LinkedList<Ver<T>>();
    private HashSet<Seg<T>> creases = new HashSet<Seg<T>>();

    @Deprecated
    public Face() {
    }

    @SafeVarargs
    public Face(Ver<T>... vers) {
        for (Ver<T> ver : vers) {
            this.vers.add(ver);
        }
    }

    public Link<T> link(Face<T> f) {
        Ver<T> p = null;
        Ver<T> q = null;
        for (Ver<T> ver : vers) {
            for (Ver<T> wer : f.vers) {
                if (ver == wer) {
                    if (p == null) {
                        p = ver;
                    } else {
                        q = ver;
                    }
                }
            }
        }
        if (p == null || q == null) {
            return null;
        }
        return new Link<T>(this, f, p, q);
    }

    public void draw(ScreenCp screen) {
        FaceDrawer.draw(screen, vers, creases);
    }

    @Deprecated
    public LinkedList<Ver<T>> getVers() {
        return vers;
    }

    @Deprecated
    public void setVers(LinkedList<Ver<T>> vers) {
        this.vers = vers;
    }

    @Deprecated
    public HashSet<Seg<T>> getCreases() {
        return creases;
    }

    @Deprecated
    public void setCreases(HashSet<Seg<T>> creases) {
        this.creases = creases;
    }
}
