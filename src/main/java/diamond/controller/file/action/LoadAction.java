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
import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.ModelScreenContext;
import diamond.controller.paint.context.Palette;
import diamond.model.geom.element.diagram.Diagram;
import diamond.view.ProgressFrame;

/**
 * @author long_
 *
 */
public class LoadAction implements ActionListener {
    Context context;
    Component parentComponent;

    public LoadAction(Context context, Component parent) {
        this.context = context;
        this.parentComponent = parent;
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

            Palette palette = context.getPalette();//TODO needs to be delegated to other place
            palette.setDiagrams(data.getDiagrams());
            Vector<Diagram> diagrams = palette.getDiagrams();
            palette.setStepNo(diagrams.size() - 1);
            ModelScreenContext modelScreenContext = context
                    .getModelScreenContext();
            modelScreenContext
                    .setTransform(palette.getDiagram()
                            .getTransform());
            for (Diagram diagram : diagrams) {
                diagram.getCp().rebuildModel();
                diagram.getCp().loadOrder();
            }
            data.getStyleSet().apply();
            frame.done();
            context.getPaintScreenContext().setFileInformation(new File(path));
        }
    }
}
