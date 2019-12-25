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
import diamond.controller.Palette;
import diamond.controller.file.LoaderXML;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;

/**
 * @author Kei Morisue
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
            Palette palette = loader.load(path);
            for (Cp cp : palette.getCps()) {
                for (Face face : cp.getFaces()) {
                    for (HalfEdge he : face.getHalfEdges()) {
                        he.getV0().add(he);
                    }
                    for (HalfEdge he : face.getUnsettledLines()) {
                        he.getV0().add(he);
                    }
                }
            }
            context.setPalette(palette);
        }
    }
}