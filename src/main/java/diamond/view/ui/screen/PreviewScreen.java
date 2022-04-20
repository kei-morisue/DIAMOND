/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.model.cyborg.Cp;
import diamond.view.ui.panel.PageOrdinal;
import diamond.view.ui.panel.PageTop;
import diamond.view.ui.screen.style.FaceStyle;
import diamond.view.ui.screen.style.PageStyle;

/**
 * @author Kei Morisue
 *
 */
public class PreviewScreen extends AbstractPreviewScreen {

    public PreviewScreen(Context context) {
        super(context);
    }

    @Override
    protected JLabel buildNorthernLabel(int pageNo) {
        JLabel label = new JLabel("Powered by "
                + "DIAMOND "
                + "(c) Kei Morisue "
                + "2019 - 2021", JLabel.CENTER);
        FaceStyle faceStyle = context.getPalette().getFaceStyle();
        label.setBackground(faceStyle.getFront());
        label.setOpaque(true);
        return label;
    }

    @Override
    protected JLabel buildSouthernLabel(int pageNo) {
        JLabel label = new JLabel(String.valueOf(pageNo), JLabel.CENTER);
        FaceStyle faceStyle = context.getPalette().getFaceStyle();
        label.setBackground(faceStyle.getFront());
        label.setOpaque(true);
        return label;
    }

    @Override
    protected JPanel buildPage() {
        Vector<Cp> cps = context.getPalette().getCps();
        if (pageNo == 0) {
            return new PageTop(cps);

        } else {
            return new PageOrdinal(pageNo, cps);
        }
    }

    @Override
    public int maxPageNo() {
        int steps = context.getPalette().getCps().size();
        int n = PageStyle.DIAGRAM_ROW * PageStyle.DIAGRAM_COL;
        if (steps < n) {
            return 0;
        }
        int rest = steps - n + 1;
        return (rest / n) + ((rest % n == 0) ? 0 : 1);
    }

}