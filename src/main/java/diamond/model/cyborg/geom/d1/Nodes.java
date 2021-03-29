/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class Nodes<T extends F<T>> {

    private LinkedList<Ver<T>> nodes = new LinkedList<Ver<T>>();
    private NodeComparator<T> c;

    @Deprecated
    public Nodes() {
    }

    public Nodes(Ver<T> p, Ver<T> q) {
        c = new NodeComparator<T>(p, q);
    }

    public void add(Ver<T> v0) {
        for (Ver<T> v : nodes) {
            if (v.dir(v0).norm().isZero()) {
                return;
            }
        }
        nodes.add(v0);
        nodes.sort(c);
    }

    public void add(List<Ver<T>> vers) {
        if (nodes.size() != 0) {
            return;
        }
        nodes.addAll(vers);
        nodes.sort(c);
    }

    public void remove(Ver<T> v) {
        nodes.remove(v);
        nodes.sort(c);
    }

    public Ver<T> xPoint(Line<T> l) {
        for (Ver<T> node : nodes) {
            if (l.isOn(node)) {
                return node;
            }
        }
        return null;
    }

    public Ver<T> find(Nodes<T> nodes) {
        for (Ver<T> node : nodes.nodes) {
            Ver<T> found = find(node);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public List<Ver<T>> cut(Ver<T> v, List<Ver<T>> s0) {
        if (find(v) == null) {
            return null;
        }
        int i = nodes.indexOf(v);
        s0.addAll(s0.subList(0, Math.max(i - 1, 0)));
        int size = nodes.size() - 1;
        return nodes.subList(Math.min(i + 1, size), size);
    }

    public Ver<T> find(Ver<T> v) {
        for (Ver<T> node : nodes) {
            if (node == v) {
                return v;
            }
        }
        return null;
    }

    public Ver<T> findNode(double x, double y, double scale) {
        for (Ver<T> node : nodes) {
            if (node.isNear(x, y, scale)) {
                return node;
            }
        }
        return null;
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

    @Deprecated
    public NodeComparator<T> getC() {
        return c;
    }

    @Deprecated
    public void setC(NodeComparator<T> c) {
        this.c = c;
    }

}
