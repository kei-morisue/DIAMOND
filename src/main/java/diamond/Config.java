/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond;

import java.io.File;

/**
 * @author Kei Morisue
 *
 */
public class Config {

	final public static String INI_FILE_PATH
			= System.getProperty("user.home") + File.separator + "defox.ini";
	final public static int MAIN_FRAME_HEIGHT = 900;
	final public static int MAIN_FRAME_WIDTH = 1800;

	final public static int STYLE_DIALOG_HEIGHT = 500;
	final public static int STYLE_DIALOG_WIDTH = 500;

	final public static int PREVIEW_FRAME_WIDTH = 1000;
	final public static int PREVIEW_FRAME_HEIGHT = 1000;

	final public static double PAPER_SIZE = 400;

}
