/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.screen.style.FaceStyle;

/**
 * @author Kei Morisue
 *
 */
public class OptionFace extends JPanel {
    private static final String faceFrontLabel = Labels.get("face_front_color");
    private static final String faceBackLabel = Labels.get("face_back_color");
    private Context context;

    public OptionFace(Context context) {
        this.context = context;
        JButton faceFront = new JButton(faceFrontLabel);
        JButton faceBack = new JButton(faceBackLabel);
        faceFront.setBackground(FaceStyle.COLOR_FRONT);
        faceBack.setBackground(FaceStyle.COLOR_BACK);
        faceFront.addActionListener(new FaceColorAction(faceFront, true));
        faceBack.addActionListener(new FaceColorAction(faceBack, false));
        setLayout(new GridLayout(2, 1));
        add(faceFront);
        add(faceBack);
    }

    private class FaceColorAction implements ActionListener {
        private boolean isFront;
        private AbstractButton parent;

        public FaceColorAction(AbstractButton parent, boolean isFront) {
            this.isFront = isFront;
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String title;
            Color initialColor;
            if (isFront) {
                title = faceFrontLabel;
                initialColor = FaceStyle.COLOR_FRONT;
            } else {
                title = faceBackLabel;
                initialColor = FaceStyle.COLOR_BACK;
            }
            JColorChooser chooser = new JColorChooser(initialColor);
            JDialog dialog = JColorChooser.createDialog(parent, title, true,
                    chooser, new OkListner(isFront, chooser),
                    new CancelListner());
            dialog.setVisible(true);
        }

        private class OkListner implements ActionListener {
            boolean isFront;
            JColorChooser chooser;

            public OkListner(boolean isFront, JColorChooser chooser) {
                this.isFront = isFront;
                this.chooser = chooser;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Color chosen = chooser.getColor();
                if (chosen == null) {
                    return;
                }
                if (isFront) {
                    FaceStyle.COLOR_FRONT = chosen;
                } else {
                    FaceStyle.COLOR_BACK = chosen;
                }
                parent.setBackground(chosen);
                context.repaint();
            }
        }

        private class CancelListner implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }

    }
}
