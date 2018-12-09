/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.uipanel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import diamond.paint.core.PaintConfig;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.uipanel.folding.FoldingAction;

/**
 * @author long_
 *
 */
public class FoldingButtonPanel extends JPanel {
    JButton buildButton = new JButton(
            ResourceHolder.getString(ResourceKey.LABEL, StringID.UI.FOLD_ID));

    JCheckBox doFullEstimationCheckBox = new JCheckBox(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.FULL_ESTIMATION_ID),
            false);

    public FoldingButtonPanel() {
        setLayout(new GridLayout(4, 1, 10, 2));
        add(buildButton);
        add(doFullEstimationCheckBox);

        doFullEstimationCheckBox.setSelected(true);

        buildButton.addActionListener(new FoldingAction());
        doFullEstimationCheckBox
                .addActionListener(
                        new DoFullEstimationCheckBoxAction());

    }

    private class DoFullEstimationCheckBoxAction
            implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            PaintConfig.bDoFullEstimation = doFullEstimationCheckBox
                    .isSelected();
        }
    }

}
