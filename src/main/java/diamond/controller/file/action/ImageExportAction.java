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
        if (chooser.showSaveDialog(
                parent) == JFileChooser.APPROVE_OPTION) {
            try {
                int w = screen.getWidth();
                int h = screen.getHeight();

                BufferedImage image = new BufferedImage(w, h,
                        BufferedImage.TYPE_INT_RGB);

                Graphics2D g2 = image.createGraphics();
                screen.paint(g2);
                screen.paintComponents(g2);
                g2.dispose();
                String filePath = chooser.getSelectedFile().getPath();
                File file = new File(filePath);
                ImageIO.write(image,
                        filePath.substring(filePath.lastIndexOf(".") + 1),
                        file);
            } catch (IOException exception) {
                System.err.println(exception);
            }
        }
        ;

    }
}
