/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class Export implements ActionListener {
    Context context;
    Component parentComponent;

    public Export(Context context, Component parent) {
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
        ExporterXml exporterXML = new ExporterXml();
        exporterXML.export(context.getDiagram(), path);
    }

}