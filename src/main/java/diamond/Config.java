/** DIAMOND - Origami Diagram Editor*/

package diamond;

import java.io.File;

public class Config {

    final public static int DEFAULT_GRID_DIV_NUM = 2;
    final public static boolean FOR_STUDY = false;
    final public static String INI_FILE_PATH = System.getProperty("user.home")
            + File.separator + "diamond.ini";

    final public static int INITIAL_MAIN_FRAME_HEIGHT = 1000;
    final public static int INITIAL_MAIN_FRAME_WIDTH = 1500;

    final public static int INITIAL_MODEL_FRAME_HEIGHT = 400;
    final public static int INITIAL_MODEL_FRAME_WIDTH = 400;

    final public static int MRUFILE_NUM = 10;
    final public static double DEFAULT_PAPER_SIZE = 350;
    final public static double EPS = 1.0e-6;
}
