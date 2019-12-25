/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import diamond.controller.Palette;

/**
 * @author Kei Morisue
 *
 */
public interface Exporter {
    public boolean export(Palette palette, String filepath);
}