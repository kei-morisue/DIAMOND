/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.uipanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.viewsetting.main.ScreenUpdater;

/**
 * @author long_
 *
 */
public class DisplayCeckBoxPanel extends JPanel implements ActionListener {
    JCheckBox dispAuxLinesCheckBox = new JCheckBox(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.SHOW_AUX_ID),
            true);

    JCheckBox dispMVLinesCheckBox = new JCheckBox(
            ResourceHolder.getString(ResourceKey.LABEL, StringID.UI.SHOW_MV_ID),
            true);

    JCheckBox dispVertexCheckBox = new JCheckBox(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.SHOW_VERTICES_ID),
            false);

    public DisplayCeckBoxPanel() {
        setLayout(new GridLayout(3, 1, 10, 2));
        add(dispMVLinesCheckBox);
        add(dispAuxLinesCheckBox);
        add(dispVertexCheckBox);

        dispVertexCheckBox.addActionListener(this);
        dispVertexCheckBox.setSelected(true);
        dispMVLinesCheckBox
                .addActionListener(new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        PaintConfig.dispMVLines = dispMVLinesCheckBox
                                .isSelected();
                        PaintContext.getPainterScreen().repaint();
                    }
                });
        dispAuxLinesCheckBox
                .addActionListener(new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        PaintConfig.dispAuxLines = dispAuxLinesCheckBox
                                .isSelected();

                        PaintContext.getPainterScreen().repaint();
                    }
                });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dispVertexCheckBox) {
            PaintConfig.dispVertex = dispVertexCheckBox.isSelected();

            ScreenUpdater.getInstance().updateScreen();
        }

    }

}
