/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond;

import java.io.File;

import diamond.model.math.field.Rational;
import diamond.model.math.field.Real;
import diamond.model.math.field.Silver;

/**
 * @author Kei Morisue
 *
 */
public class Config {
    final public static String INI_FILE_PATH = System.getProperty("user.home")
            + File.separator + "defox.ini";
    final public static int MAIN_FRAME_HEIGHT = 1000;
    final public static int MAIN_FRAME_WIDTH = 1700;

    final public static int STYLE_FRAME_HEIGHT = 300;
    final public static int STYLE_FRAME_WIDTH = 300;

    final public static int PREVIEW_FRAME_WIDTH = 1000;
    final public static int PREVIEW_FRAME_HEIGHT = 1000;

    final public static int size = 200;
    final public static Rational SIZE_RATIONAL = new Rational(size, 1);
    final public static Real SIZE_REAL = new Real(size);
    final public static Silver SIZE_SILVER = new Silver(
            SIZE_RATIONAL,
            new Rational(0, 1));

    //    final public static int EPS_V = 50;
    //    final public static int EPS_F = 100;

    final public static String FONT = "Arial";

}
