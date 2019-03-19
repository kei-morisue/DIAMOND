/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import javax.swing.JRadioButton;

import diamond.view.resource.ImageIconLoader;

/**
 * @author long_
 *
 */
public class DiagramIcon extends JRadioButton {
    public DiagramIcon() {
        ImageIconLoader imgLoader = new ImageIconLoader();
        setIcon(
                imgLoader
                        .loadAsIcon("icon/diamond.gif"));
    }
}
