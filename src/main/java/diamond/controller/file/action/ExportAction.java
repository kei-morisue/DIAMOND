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
import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.view.ProgressFrame;

/**
 * @author long_
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
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        if (paintScreenContext.getFile() != null) {
            path = paintScreenContext.getFile().getPath();
        } else if (JFileChooser.APPROVE_OPTION == chooser
                .showSaveDialog(parentComponent)) {
            path = chooser.getSelectedFile().getPath();
        }
        DataSet data = new DataSet(
                context.getPalette().getDiagrams());

        paintScreenContext.setFile(new File(path));
        ProgressFrame frame = new ProgressFrame("saving");

        ExporterXML exporterXML = new ExporterXML();
        exporterXML.export(data, path);
        frame.done();

    }

}
