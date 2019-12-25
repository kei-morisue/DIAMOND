/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import diamond.controller.Context;
import diamond.controller.file.ExporterXML;

/**
 * @author Kei Morisue
 *
 */
public class ExportAction implements ActionListener {
    Context context;
    Component parentComponent;

    public ExportAction(Context context, Component parent) {
        this.context = context;
        this.parentComponent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        String path = null;
        if (JFileChooser.APPROVE_OPTION == chooser
                .showSaveDialog(parentComponent)) {
            path = chooser.getSelectedFile().getPath();
        }
        ExporterXML exporterXML = new ExporterXML();
        exporterXML.export(context.getPalette(), path);
    }

}