/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.fold;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;
import javax.vecmath.Vector2d;

import diamond.geom.GeomUtil;
import diamond.value.OriLine;
import diamond.value.OriPoint;
import diamond.view.main.MainFrame;

/**
 * @author long_
 *
 */
public class CreasePatternSimplifier {
    private CreasePatternSimplifier() {

    }

    public static boolean cleanDuplicatedLines(
            Collection<OriLine> creasePattern) {
        System.out.println("pre cleanDuplicatedLines " + creasePattern.size());
        ArrayList<OriLine> tmpLines = new ArrayList<OriLine>();
        for (OriLine l : creasePattern) {
            OriLine ll = l;

            boolean bSame = false;
            // Test if the line is already in tmpLines to prevent duplicity
            for (OriLine line : tmpLines) {
                if (GeomUtil.isSameLineSegment(line, ll)) {
                    bSame = true;
                    break;
                }
            }
            if (bSame) {
                continue;
            }
            tmpLines.add(ll);
        }

        if (creasePattern.size() == tmpLines.size()) {
            return false;
        }

        creasePattern.clear();
        creasePattern.addAll(tmpLines);
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(),
                "Removing multiples edges with the same position ",
                "Simplifying CP", JOptionPane.INFORMATION_MESSAGE);

        return true;
    }

    public static boolean mergeColinearLines(
            Collection<OriLine> creasePattern) {
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
                MainFrame.getInstance(),
                "Merged colliniear edges",
                "Simplifying CP", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    private static void add(Collection<OriLine> tmpLines, OriLine line) {
        for (OriLine tmpLine : tmpLines) {
            if (tmpLine.typeVal != line.typeVal) {
                continue;
            }
            OriLine mergedLine = new OriLine(line);
            if (mergeOripoint(mergedLine, tmpLine.p0)) {
                if (mergeOripoint(mergedLine, tmpLine.p1)) {
                    boolean isSepareted = mergedLine
                            .length() - tmpLine.length()
                            - line.length() > 0.0001;
                    if (!isSepareted) {
                        tmpLines.remove(tmpLine);
                        tmpLines.add(mergedLine);
                        return;
                    }
                }
            }
        }
        tmpLines.add(line);
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
