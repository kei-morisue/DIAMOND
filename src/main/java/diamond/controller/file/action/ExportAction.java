/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.file.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import diamond.controller.file.DataSet;
import diamond.controller.file.ExporterXML;
import diamond.controller.paint.PaintContext;
import diamond.view.ProgressFrame;

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
        String path = null;
        if (paintContext.getFile() != null) {
            path = paintContext.getFile().getPath();
        } else if (JFileChooser.APPROVE_OPTION == chooser
                .showSaveDialog(parentComponent)) {
            path = chooser.getSelectedFile().getPath();
        }
        DataSet data = new DataSet(
                paintContext.palette.getDiagrams());

        paintContext.setFile(new File(path));
        ProgressFrame frame = new ProgressFrame("saving");

        ExporterXML exporterXML = new ExporterXML();
        exporterXML.export(data, path);
        frame.done();

    }

}
