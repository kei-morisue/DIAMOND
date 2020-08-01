/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.menu;

import javax.swing.JMenuBar;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class MainBar extends JMenuBar {
    public MainBar(Context context) {
        add(new MainFile(context));
        add(new MainOption(context));
    }
}
