/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.uipanel;

import java.awt.GridBagConstraints;

/**
 * @author long_
 *
 */
public class LayoutUtil {
    private LayoutUtil() {
    }

    public static GridBagConstraints buildGBC(int gridx, int gridy,
            int gridwidth) {
        GridBagConstraints gbConstraints = new GridBagConstraints();
        gbConstraints.gridx = gridx;
        gbConstraints.gridy = gridy;
        gbConstraints.gridwidth = gridwidth;
        gbConstraints.anchor = java.awt.GridBagConstraints.WEST;
        return gbConstraints;
    }
}
