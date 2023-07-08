/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.util;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @author Kei Morisue
 *
 */
public class Icon {

	public static void set(JFrame frame, String path) {
		ImageIcon icon = new ImageIcon(path);
		frame.setIconImage(icon.getImage());
	}
}