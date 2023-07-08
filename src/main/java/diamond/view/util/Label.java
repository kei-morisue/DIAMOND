/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Kei Morisue
 *
 */
public class Label {
	private static final ResourceBundle bundle = createResource(".string.Resource");

	private static ResourceBundle createResource(String classPath) {
		try {
			return ResourceBundle.getBundle("resource");
		} catch (MissingResourceException e) {
		}
		try {
			return ResourceBundle.getBundle("resource_en", Locale.ENGLISH);
		} catch (MissingResourceException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Label() {
	}

	public static String get(String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
			return null;
		}
	}
}
