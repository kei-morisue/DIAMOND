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
import java.io.IOException;

import javax.swing.JFileChooser;

import diamond.view.screen.PreviewScreen;

/**
 * @author long_
 *
 */
public abstract class AbstractExportAction implements ActionListener {
    protected Component parent;
    protected PreviewScreen screen;

    @SuppressWarnings("unused")
    private AbstractExportAction() {

    }

    protected AbstractExportAction(Component parent, PreviewScreen screen) {
        this.parent = parent;
        this.screen = screen;
    }

    abstract protected void export(String filePath) throws IOException;

    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showSaveDialog(
                parent) == JFileChooser.APPROVE_OPTION) {
            screen.setToTop();
            try {
                String filePath = chooser.getSelectedFile().getPath();
                export(filePath);
            } catch (IOException exception) {
                System.err.println(exception);
            }
            screen.setToTop();
        }
    }

    protected BufferedImage getImage() {
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
