/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import diamond.view.ui.screen.AbstractPreviewScreen;

/**
 * @author Kei Morisue
 *
 */
public class ExportImageAction extends AbstractExportAction {

    public ExportImageAction(
            Component parent,
            AbstractPreviewScreen screen) {
        super(parent, screen);
    }

    protected void export(String filePath) throws IOException {
        for (int i = 0; i < screen.maxPageNo() + 1; ++i) {
            BufferedImage image = getImage();
            String pathname = fileName(filePath, i);
            File file = new File(pathname + formatName());
            ImageIO.write(image, formatName(), file);
            screen.nextPage(1);
        }
    }

    public String fileName(String filePath, int i) {
        String pathname = filePath + "/page_"
                + String.format("%03d", i + 1) + ".";
        return pathname;
    }

    public String formatName() {
        return "png";
    }

    private BufferedImage getImage() {
        int w = screen.getWidth();
        int h = screen.getHeight();
        BufferedImage image = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        screen.paintComponents(g2);
        g2.dispose();
        return image;
    }
}