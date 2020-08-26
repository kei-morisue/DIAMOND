/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
public class PreviewScreen extends JPanel {
    private int pageNo = 0;
    private Context context;
    private JPanel page;

    public PreviewScreen(Context context) {
        this.context = context;
        build();
    }

    private void build() {
        setBackground(Color.white);
        setBorder(new LineBorder(Color.black));
        setLayout(new BorderLayout());
        page = buildPage();
        add(page, BorderLayout.CENTER);
        add(buildNorthernLabel(), BorderLayout.NORTH);
        add(buildSouthernLabel(pageNo + 1), BorderLayout.SOUTH);
    }

    private JLabel buildNorthernLabel() {
        JLabel label = new JLabel("Powered by "
                + "DIAMOND "
                + "(c) Kei Morisue "
                + "2019", JLabel.CENTER);
        FaceStyle faceStyle = context.getPalette().getFaceStyle();
        label.setBackground(faceStyle.getCOLOR_FRONT());
        label.setOpaque(true);
        return label;
    }

    private JLabel buildSouthernLabel(int pageNo) {
        JLabel label = new JLabel(String.valueOf(pageNo), JLabel.CENTER);
        FaceStyle faceStyle = context.getPalette().getFaceStyle();
        label.setBackground(faceStyle.getCOLOR_FRONT());
        label.setOpaque(true);
        return label;
    }

    public JPanel buildPage() {
        Vector<Cp> cps = context.getPalette().getCps();
        if (pageNo == 0) {
            return new PageTop(cps);

        } else {
            return new PageOrdinal(pageNo, cps);
        }
    }

    public void nextPage(int pages) {
        pageNo = Math.max(0, pageNo + pages);
        pageNo = Math.min(pageNo, maxPageNo());
        removeAll();
        build();
        validate();
        repaint();
    }

    public void setToTop() {
        pageNo = 0;
        removeAll();
        build();
        validate();
        repaint();
    }

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