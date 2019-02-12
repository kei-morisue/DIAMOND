/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp.simplifier;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.util.LineGeomUtil;
import diamond.model.palette.cp.CreasePattern;

/**
 * @author long_
 *
 */
public class DuplicatedCPSimplifier {
    private DuplicatedCPSimplifier() {

    }

    public static boolean simplify(
            CreasePattern cp) {
        Collection<OriLine> creasePattern = cp.getLines();
        System.out.println("pre cleanDuplicatedLines " + creasePattern.size());
        ArrayList<OriLine> tmpLines = new ArrayList<OriLine>();
        for (OriLine l : creasePattern) {
            OriLine ll = l;

            boolean bSame = false;
            // Test if the line is already in tmpLines to prevent duplicity
            for (OriLine line : tmpLines) {
                if (LineGeomUtil.isSameLineSegment(line, ll)) {
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
                null,
                "Removing multiples edges with the same position ",
                "Simplifying CP", JOptionPane.INFORMATION_MESSAGE);

        return true;
    }

}