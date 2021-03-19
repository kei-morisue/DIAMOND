/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.util.LinkedList;

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

    public void add(Ver<T> v) {
        nodes.add(v);
        nodes.sort(c);
    }

    public void remove(Ver<T> v) {
        nodes.remove(v);
        nodes.sort(c);
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

    public Nodes<T> cut0(Ver<T> p, Ver<T> q) {
        Nodes<T> sub = new Nodes<T>(p, q);
        for (Ver<T> node : nodes) {
            if (node == q) {
                return sub;
            }
            add(node);
        }
        return sub;
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
