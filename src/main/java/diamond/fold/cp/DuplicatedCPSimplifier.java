/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.fold.cp;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import diamond.geom.GeomUtil;
import diamond.value.OriLine;
import diamond.view.main.MainFrame;

/**
 * @author long_
 *
 */
public class DuplicatedCPSimplifier {
    private DuplicatedCPSimplifier() {

    }

    public static boolean simplify(
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

}
