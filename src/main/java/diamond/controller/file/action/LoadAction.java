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
import diamond.controller.file.LoaderXML;
import diamond.controller.paint.ModelContext;
import diamond.controller.paint.PaintContext;

/**
 * @author long_
 *
 */
public class LoadAction implements ActionListener {
    PaintContext paintContext;
    ModelContext modelContext;
    Component parentComponent;

    public LoadAction(PaintContext context, ModelContext modelContext,
            Component parent) {
        this.paintContext = context;
        this.parentComponent = parent;
        this.modelContext = modelContext;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (JFileChooser.APPROVE_OPTION == chooser
                .showOpenDialog(parentComponent)) {
            LoaderXML loader = new LoaderXML();
            String path = chooser.getSelectedFile().getPath();
            DataSet data = loader.load(path);
            paintContext.palette.setDiagrams(data.getDiagrams());
            paintContext.palette.setStepNo(0);
            modelContext.transform = modelContext.palette.getDiagram()
                    .getTransform();
            //            for (Diagram diagram : paintContext.palette.getDiagrams()) {
            //                diagram.getCp().rebuildModel();
            //            }
        }
    }
}
