/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.util;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 * @author Kei Morisue
 *
 */
public class Icon {

	public static void set(JFrame frame, String name) {
		ImageIcon icon = loadAsIcon(name);
		frame.setIconImage(icon.getImage());
	}

	public static void set(JRadioButton button, String name) {
		ImageIcon icon = loadAsIcon(name);
		button.setIcon(icon);
		String[] split = name.split("\\.");
		icon = loadAsIcon(split[0] + "_p." + split[1]);
		button.setSelectedIcon(icon);
	}

	private static ImageIcon loadAsIcon(String name) {
		return loadAsIcon(name, Icon.class);
	}

	private static ImageIcon loadAsIcon(String name, Class<?> c) {
		ClassLoader classLoader = c.getClassLoader();
		URL url = classLoader.getResource("icon/" + name);
		ImageIcon icon = new ImageIcon(url);
		return icon;

	}

}