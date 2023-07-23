/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class TabManageFaces extends AbstractTab {
	public TabManageFaces(Context context, ButtonGroup buttonGroup) {
		setLayout(new GridLayout(2, 2));

	}
}
