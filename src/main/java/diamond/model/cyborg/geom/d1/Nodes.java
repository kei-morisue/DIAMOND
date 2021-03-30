/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.find.NodesFinder;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class Nodes<T extends F<T>> {

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

    public void cut(
            Ver<T> r,
            Ver<T> p,
            Ver<T> q,
            D1<T> sp,
            D1<T> sq) {
        if (!isNode(r)) {
            return;
        }
        nodes.sort(new NodeComparator<T>(p, q));
        int i = nodes.indexOf(r);
        Nodes<T> nq = new Nodes<T>();
        Nodes<T> np = nq;
        np.nodes.addAll(nodes.subList(0, i));
        sp.add(np);
        int size = nodes.size();
        new Nodes<T>();
        nq.nodes.addAll(nodes.subList(Math.min(i + 1, size - 1), size));
        sq.add(nq);
    }

    public boolean isNode(Ver<T> v) {
        for (Ver<T> node : nodes) {
            if (node == v) {
                return true;
            }
        }
        return false;
    }

    public Ver<T> findNode(double x, double y, double scale) {
        return NodesFinder.findNode(nodes, x, y, scale);
    }

    public void drawPointed(ScreenModel<T> screen, Graphics2D g2d) {
        for (Ver<T> node : nodes) {
            node.drawPointed(screen, g2d);
        }
    };

    public void draw(ScreenModel<T> screen, Graphics2D g2d) {
        for (Ver<T> node : nodes) {
            node.draw(screen, g2d);
        }
    };

    @Deprecated
    public LinkedList<Ver<T>> getNodes() {
        return nodes;
    }

    @Deprecated
    public void setNodes(LinkedList<Ver<T>> nodes) {
        this.nodes = nodes;
    }

}
