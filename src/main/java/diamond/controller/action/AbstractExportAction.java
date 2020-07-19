/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;

import diamond.view.ui.screen.PreviewScreen;

/**
 * @author Kei Morisue
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

    abstract protected void export(String directory) throws IOException;

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

}