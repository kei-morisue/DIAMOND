/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.file.action;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import diamond.view.screen.PreviewScreen;

/**
 * @author long_
 *
 */
public class ImageExportAction implements ActionListener {
    private Component parent;
    private PreviewScreen screen;

    public ImageExportAction(Component parent, PreviewScreen screen) {
        this.parent = parent;
        this.screen = screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showSaveDialog(
                parent) == JFileChooser.APPROVE_OPTION) {
            screen.setToTop();
            try {
                String filePath = chooser.getSelectedFile().getPath();
                for (int i = 0; i < screen.maxPageNo() + 1; ++i) {
                    BufferedImage image = getImage();
                    String pathname = fileName(filePath, i);
                    File file = new File(pathname + formatName());
                    ImageIO.write(image, formatName(), file);
                    screen.nextPage(1);
                }
            } catch (IOException exception) {
                System.err.println(exception);
            }
            screen.setToTop();
        }
    }

    private String fileName(String filePath, int i) {
        String pathname = filePath + "/page_"
                + String.format("%03d", i + 1) + ".";
        return pathname;
    }

    /**
     * @return
     */
    private String formatName() {
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
