/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.palette.cp.CreasePattern;
import diamond.model.palette.file.DataSet;
import diamond.model.palette.file.ExporterXML;
import diamond.model.palette.file.LoaderXML;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class MenuBar extends JMenuBar {
    private PaintContext paintContext;

    public MenuBar(PaintContext paintContext) {
        this.paintContext = paintContext;
        JMenu fileMenu = new JMenu(ResourceHolder.getLabelString(LABEL.FILE));
        JMenuItem save = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.SAVE));
        JMenuItem open = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.OPEN));
        fileMenu.add(open);
        fileMenu.add(save);
        add(fileMenu);
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (JFileChooser.APPROVE_OPTION == chooser
                        .showSaveDialog(fileMenu)) {
                    ExporterXML exporterXML = new ExporterXML();
                    DataSet data = new DataSet(paintContext.getCP());
                    String path = chooser.getSelectedFile().getPath();
                    exporterXML.export(data, path);
                }
            }
        });
        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (JFileChooser.APPROVE_OPTION == chooser
                        .showOpenDialog(fileMenu)) {
                    LoaderXML loader = new LoaderXML();
                    String path = chooser.getSelectedFile().getPath();
                    DataSet data = loader.load(path);
                    CreasePattern cp = paintContext.getCP();
                    cp.clear();
                    for (OriLine line : data.getLines()) {
                        cp.addLine(line);
                    }
                }
            }
        });
    }
}
