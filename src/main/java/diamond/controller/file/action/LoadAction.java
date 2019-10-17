/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.file.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JFileChooser;

import diamond.controller.file.DataSet;
import diamond.controller.file.LoaderXML;
import diamond.controller.paint.context.ModelContext;
import diamond.controller.paint.context.PaintContext;
import diamond.model.geom.element.diagram.Diagram;
import diamond.view.ProgressFrame;

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
            ProgressFrame frame = new ProgressFrame("loading");
            DataSet data = loader.load(path);

            paintContext.getPalette().setDiagrams(data.getDiagrams());
            Vector<Diagram> diagrams = paintContext.getPalette().getDiagrams();
            paintContext.getPalette().setStepNo(diagrams.size() - 1);
            modelContext.setTransform(modelContext.getPalette().getDiagram()
                    .getTransform());
            for (Diagram diagram : diagrams) {
                diagram.getCp().rebuildModel();
                diagram.getCp().loadOrder();
            }
            data.getStyleSet().apply();
            frame.done();
            paintContext.setFile(new File(path));
        }
    }
}
