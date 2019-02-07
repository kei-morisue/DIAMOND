/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.orimodel.OriModel;
import diamond.model.palette.CreasePatternHolder;
import diamond.view.paint.screen.PaintScreen;
import diamond.view.resource.ImageIconLoader;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class panelUI extends JPanel {
    private JRadioButton axiom1Button = new JRadioButton(
            ResourceHolder.getLabelString(LABEL.AXIOM1));

    private JButton makeModelButton = new JButton("Make Faces");

    public panelUI(PaintScreen screen, PaintContext context) {
        //        setLayout(new FlowLayout());
        ImageIconLoader imageIconLoader = new ImageIconLoader();
        axiom1Button.setIcon(
                imageIconLoader.loadAsIcon("icon/segment.gif"));
        axiom1Button.setPressedIcon(
                imageIconLoader.loadAsIcon("icon/segment_p.gif"));
        add(axiom1Button);
        axiom1Button.setSelected(true);

        makeModelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new OriModel(CreasePatternHolder.getCP());

            }
        }

        );
        add(makeModelButton);
    }
}
