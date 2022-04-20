/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractPreviewScreen extends JPanel {
    protected int pageNo = 0;
    protected Context context;
    protected JPanel page;

    protected AbstractPreviewScreen() {
    }

    protected AbstractPreviewScreen(Context context) {
        this.context = context;
        build();
    }

    protected void build() {
        setBackground(Color.white);
        setBorder(new LineBorder(Color.black));
        setLayout(new BorderLayout());
        buildContent();
    }

    private void buildContent() {
        add(buildPage(), BorderLayout.CENTER);
        add(buildNorthernLabel(pageNo + 1), BorderLayout.NORTH);
        add(buildSouthernLabel(pageNo + 1), BorderLayout.SOUTH);
    }

    abstract protected JLabel buildNorthernLabel(int pageNo);

    abstract protected JLabel buildSouthernLabel(int pageNo);

    abstract protected JPanel buildPage();

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

    abstract public int maxPageNo();

}