/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import diamond.model.cyborg.diagram.Diagram;
import diamond.view.ui.screen.ScreenPage;

/**
 * @author Kei Morisue
 *
 */
public class ExporterPng implements Exporter {
    private ScreenPage screen;

    public ExporterPng(ScreenPage screen) {
        this.screen = screen;
    }

    @Override
    public boolean export(Diagram diagram, String filepath) {
        for (int i = 0; i < screen.maxPageNo() + 1; ++i) {
            BufferedImage image = getImage();
            String pathname = fileName(filepath, i);
            File file = new File(pathname + formatName());
            try {
                ImageIO.write(image, formatName(), file);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            screen.nextPage(1);
        }
        return true;
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
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        screen.paintComponents(g2);
        g2.dispose();
        return image;
    }
}
