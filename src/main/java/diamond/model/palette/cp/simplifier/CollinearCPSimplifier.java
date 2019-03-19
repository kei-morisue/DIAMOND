/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp.simplifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.vecmath.Vector2d;

import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;

/**
 * @author long_
 *
 */
public class CollinearCPSimplifier {
    private CollinearCPSimplifier() {

    }

    public static boolean simplify(Cp cp) {
        Set<OriLine> creasePattern = cp.getLines();
        ArrayList<OriLine> tmpLines = new ArrayList<OriLine>();
        for (OriLine line : creasePattern) {
            add(tmpLines, line);
        }

        if (creasePattern.size() == tmpLines.size()) {
            return false;
        }

        creasePattern.clear();
        creasePattern.addAll(tmpLines);
        JOptionPane.showMessageDialog(
                null,
                "Merged colliniear edges",
                "Simplifying CP", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    private static void add(Collection<OriLine> creasePattern,
            OriLine line) {
        for (OriLine tmpLine : creasePattern) {
            if (tmpLine.getType() != line.getType()) {
                continue;
            }
            OriLine mergedLine = new OriLine(line);
            if (mergeOripoint(mergedLine, tmpLine.p0)) {
                if (mergeOripoint(mergedLine, tmpLine.p1)) {
                    boolean isSepareted = mergedLine
                            .length() - tmpLine.length()
                            - line.length() > 0.0001;
                    if (!isSepareted) {
                        creasePattern.remove(tmpLine);
                        creasePattern.add(mergedLine);
                        return;
                    }
                }
            }
        }
        creasePattern.add(line);
    }

    private static boolean mergeOripoint(OriLine l, OriPoint v) {
        Vector2d x = new Vector2d(l.p0.x - v.x, l.p0.y - v.y);
        Vector2d y = new Vector2d(l.p1.x - v.x, l.p1.y - v.y);
        double dot = x.dot(y);
        double lx = x.length();
        double ly = y.length();
        double prod = lx * ly;
        if (Math.abs(dot - prod) <= 0.001 * prod) {
            if (lx > ly) {
                l.p1 = v;
                return true;
            }
            l.p0 = v;
            return true;
        }
        if (Math.abs(dot + prod) <= 0.001 * prod) {
            return true;
        }
        return false;
    }

}