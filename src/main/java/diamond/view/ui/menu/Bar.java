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
public class Bar extends JMenuBar {
    public Bar(Context context) {
        add(new File(context));
        add(new Option(context));
    }
}
