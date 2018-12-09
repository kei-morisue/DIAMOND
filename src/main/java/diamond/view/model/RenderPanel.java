/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import diamond.Config;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;

/**
 * @author long_
 *
 */
public class RenderPanel extends JPanel implements AdjustmentListener {
    private JScrollBar scrollBarAngle = new JScrollBar(JScrollBar.HORIZONTAL,
            90, 5, 0, 185);
    private JScrollBar scrollBarPosition = new JScrollBar(JScrollBar.VERTICAL,
            0, 5, -150, 150);

    public JLabel hintLabel = new JLabel(ResourceHolder
            .getString(ResourceKey.LABEL, "Basic"));
    private ModelViewScreen screen;

    public RenderPanel(ModelViewScreen screen) {
        this.screen = screen;
        int size = (int) (Config.DEFAULT_PAPER_SIZE * 1.1);
        screen.setPreferredSize(new Dimension(size, size));
        setLayout(new BorderLayout());
        add(screen, BorderLayout.CENTER);
        add(hintLabel, BorderLayout.SOUTH);
        add(scrollBarAngle, BorderLayout.NORTH);
        add(scrollBarPosition, BorderLayout.WEST);
        scrollBarAngle.addAdjustmentListener(this);
        scrollBarPosition.addAdjustmentListener(this);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getSource() == scrollBarAngle) {
            screen.setCrossLineAngle(e.getValue());
        } else if (e.getSource() == scrollBarPosition) {
            screen.setCrossLinePosition(e.getValue());
        }

    }
}
