/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Component;

import diamond.controller.Palette;
import diamond.controller.file.ExporterSVG;
import diamond.view.ui.screen.PreviewScreen;

/**
 * @author Kei Morisue
 *
 */
public class ExportSvgAction extends AbstractExportAction {
    Palette palette;

    public ExportSvgAction(
            Component parent,
            PreviewScreen screen,
            Palette palette) {
        super(parent, screen);
        this.palette = palette;
    }

    protected void export(String directory) {
        ExporterSVG exporter = new ExporterSVG();
        exporter.export(palette, directory);
    }

}