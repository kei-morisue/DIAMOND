/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

import diamond.controller.Context;
import diamond.controller.Palette;
import diamond.controller.file.ExporterDMD;

/**
 * @author Kei Morisue
 *
 */
public class ExportDmdAction implements ActionListener {
    Context context;
    Component parentComponent;

    public ExportDmdAction(Context context, Component parent) {
        this.context = context;
        this.parentComponent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String path = null;
        if (JFileChooser.APPROVE_OPTION == chooser
                .showSaveDialog(parentComponent)) {
            path = chooser.getSelectedFile().getPath();
        }
        ExporterDMD exporterXML = new ExporterDMD();
        Palette palette = context.getPalette();
        try {
            exporterXML.export(palette, path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}