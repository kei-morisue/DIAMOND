/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.file.FileFilterEx;
import diamond.file.FileIOUtil;
import diamond.file.FilterDB;
import diamond.fold.OrigamiModel;
import diamond.fold.OrigamiModelFactory;
import diamond.paint.creasepattern.CreasePattern;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;

/**
 * @author long_
 *
 */
public class Export implements ActionListener {

    public static JMenuItem menuItemExportCP = new JMenuItem(
            ResourceHolder.getLabelString(StringID.Main.EXPORT_CP_ID));
    public static JMenuItem menuItemExportDXF = new JMenuItem(
            ResourceHolder.getLabelString(StringID.Main.EXPORT_DXF_ID));
    public static JMenuItem menuItemExportOBJ = new JMenuItem(
            ResourceHolder.getLabelString(StringID.Main.EXPORT_OBJ_ID));
    public static JMenuItem menuItemExportSVG = new JMenuItem(
            ResourceHolder.getLabelString(StringID.Main.EXPORT_SVG_ID));

    public Export() {
        menuItemExportDXF.addActionListener(this);
        menuItemExportOBJ.addActionListener(this);
        menuItemExportCP.addActionListener(this);
        menuItemExportSVG.addActionListener(this);
    }

    //TODO Needs more refactoring
    private void exportFile(String ext) {
        Doc document = DocHolder.getDoc();
        CreasePattern creasePattern = document.getCreasePattern();
        OrigamiModel origamiModel = document.getOrigamiModel();

        boolean hasModel = origamiModel.hasModel();

        origamiModel = OrigamiModelFactory.createOrigamiModelNoCleanup(
                creasePattern,
                document.getPaperSize());
        document.setOrigamiModel(origamiModel);

        if ("obj".equals(ext) == false) {

        } else if (!hasModel && !origamiModel.isProbablyFoldable()) {

            JOptionPane.showConfirmDialog(null,
                    "Warning: Building a set of polygons from crease pattern "
                            + "was failed.",
                    "Warning",
                    JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
        }

        FileIOUtil.exportFile(null,
                new FileFilterEx[] { FilterDB.getInstance().getFilter(ext) });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemExportDXF) {
            exportFile("dxf");
        } else if (e.getSource() == menuItemExportOBJ) {
            exportFile("obj");
        } else if (e.getSource() == menuItemExportCP) {
            exportFile("cp");
        } else if (e.getSource() == menuItemExportSVG) {
            exportFile("svg");
        }
    }
}
