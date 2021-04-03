/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.util.LinkedList;

import diamond.model.cyborg.Pair;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.cyborg.graphics.find.Finder;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public final class Nodes<T extends F<T>> implements Graphic<T> {

    private LinkedList<Ver<T>> nodes = new LinkedList<Ver<T>>();

    public Nodes() {
    }

    public void add(Ver<T> v0) {
        for (Ver<T> v : nodes) {
            if (v.dir(v0).norm().isZero()) {
                return;
            }
        }
        nodes.add(v0);
    }

    public void remove(Ver<T> v) {
        nodes.remove(v);
    }

    public Ver<T> xNode(Line<T> l) {
        for (Ver<T> node : nodes) {
            if (l.isOn(node)) {
                return node;
            }
        }
        return null;
    }

    public Ver<T> find(Nodes<T> nodes) {
        for (Ver<T> node : nodes.nodes) {
            if (isNode(node)) {
                return node;
            }
        }
        return null;
    }

    public Pair<Nodes<T>> cut(
            Ver<T> p,
            Ver<T> r,
            Ver<T> q) {
        if (!isNode(r)) {
            return null;
        }
        nodes.sort(new NodeComparator<T>(p, q));
        int i = nodes.indexOf(r);
        Nodes<T> np = new Nodes<T>();
        np.nodes.addAll(nodes.subList(0, i));
        int size = nodes.size();
        Nodes<T> nq = new Nodes<T>();
        nq.nodes.addAll(nodes.subList(Math.min(i + 1, size - 1), size));
        return new Pair<Nodes<T>>(np, nq);
    }

    public boolean isNode(Ver<T> v) {
        for (Ver<T> node : nodes) {
            if (node == v) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            boolean isPointed) {
        for (Ver<T> node : nodes) {
            node.draw(screen, g2d, scale, isPointed);
        }
    }

    @Override
    public <S extends Graphic<T>> S find(
            Finder<T, S> finder,
            double x,
            double y,
            double scale) {
        return finder.find(nodes, x, y, scale);
    }

    @Deprecated
    public LinkedList<Ver<T>> getNodes() {
        return nodes;
    }

    @Deprecated
    public void setNodes(LinkedList<Ver<T>> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean isNear(double x, double y, double scale) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    @Override
    public double distSquare(double x, double y) {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

}
