/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.button;

import javax.swing.JRadioButton;

import diamond.view.resource.IconBuilder;

/**
 * @author Kei Morisue
 *
 */
public class AbstractIconButton extends JRadioButton {
    public void setIcons(String iconBaseName) {
        setFocusable(false);
        IconBuilder.set(this, iconBaseName + ".gif");
        IconBuilder.setSelected(this, iconBaseName + "_p.gif");
    }

}
