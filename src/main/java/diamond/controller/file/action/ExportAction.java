/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.file.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import diamond.controller.file.DataSet;
import diamond.controller.file.ExporterXML;
import diamond.controller.paint.PaintContext;

/**
 * @author long_
 *
 */
public class ExportAction implements ActionListener {
    PaintContext paintContext;
    Component parentComponent;

    public ExportAction(PaintContext context, Component parent) {
        this.paintContext = context;
        this.parentComponent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (JFileChooser.APPROVE_OPTION == chooser
                .showSaveDialog(parentComponent)) {
            ExporterXML exporterXML = new ExporterXML();
            DataSet data = new DataSet(
                    paintContext.palette.getCreasePatterns());
            String path = chooser.getSelectedFile().getPath();
            exporterXML.export(data, path);
        }
    }

}
