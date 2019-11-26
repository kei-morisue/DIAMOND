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
    final public static String INI_FILE_PATH = System.getProperty("user.home")
            + File.separator + "diamond.ini";
    final public static int MAIN_FRAME_HEIGHT = 1000;
    final public static int MAIN_FRAME_WIDTH = 1700;

    final public static int DIALOG_HEIGHT = 800;
    final public static int DIALOG_WIDTH = 1000;

    final public static int STYLE_FRAME_HEIGHT = 300;
    final public static int STYLE_FRAME_WIDTH = 300;

    final public static int PREVIEW_FRAME_WIDTH = 1000;
    final public static int PREVIEW_FRAME_HEIGHT = 1000;

    final public static double PAPER_SIZE = 400;
    final public static int PAPER_EDGES = 4;

    final public static double EPSILON = 1.0e-6;
    final public static double EPSILON_RADIAN = 1.0e-5;

}
