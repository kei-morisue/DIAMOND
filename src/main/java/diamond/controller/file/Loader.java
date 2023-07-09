/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.io.IOException;

import diamond.controller.Palette;

/**
 * @author Kei Morisue
 *
 */
public interface Loader {
	public Palette load(String filepath) throws IOException, ClassNotFoundException;
}
