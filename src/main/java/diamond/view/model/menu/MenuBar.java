/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.model.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.doc.exporter.Exporter;
import diamond.doc.exporter.ExporterDXF;
import diamond.doc.exporter.ExporterOBJ2;
import diamond.file.FileFilterEx;
import diamond.fold.FolderTool;
import diamond.fold.OrigamiModel;
import diamond.paint.core.PaintConfig;
import diamond.resource.Constants;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;
import diamond.view.model.ModelViewScreen;
import diamond.viewsetting.model.ModelFrameSettingDB;

/**
 * @author long_
 *
 */
public class MenuBar extends JMenuBar implements ActionListener, Observer {
    private JMenu dispSubMenu = new JMenu(ResourceHolder
            .getString(ResourceKey.LABEL, "MENU_DispType"));

    private JMenu menuDisp = new JMenu(ResourceHolder
            .getString(ResourceKey.LABEL, "MENU_Disp"));
    private JMenu menuFile = new JMenu(
            ResourceHolder.getString(ResourceKey.LABEL, "File"));
    private JCheckBoxMenuItem menuItemCrossLine = new JCheckBoxMenuItem(
            "Show Cross-Line", true);
    private JMenuItem menuItemExportDXF = new JMenuItem(
            ResourceHolder.getString(ResourceKey.LABEL,
                    "MENU_ExportModelLine_DXF"));
    private JMenuItem menuItemExportOBJ = new JMenuItem("Export to OBJ file");
    private JRadioButtonMenuItem menuItemFillAlpha = new JRadioButtonMenuItem(
            ResourceHolder.getString(ResourceKey.LABEL,
                    "MENU_FillAlpha"));
    private JRadioButtonMenuItem menuItemFillColor = new JRadioButtonMenuItem(
            ResourceHolder.getString(ResourceKey.LABEL,
                    "MENU_FillColor"));
    private JRadioButtonMenuItem menuItemFillNone = new JRadioButtonMenuItem(
            ResourceHolder.getString(ResourceKey.LABEL,
                    "MENU_DrawLines"));
    private JRadioButtonMenuItem menuItemFillWhite = new JRadioButtonMenuItem(
            ResourceHolder.getString(ResourceKey.LABEL,
                    "MENU_FillWhite"));
    private JMenuItem menuItemFlip = new JMenuItem(ResourceHolder
            .getString(ResourceKey.LABEL, "MENU_Invert"));

    private JCheckBoxMenuItem menuItemSlideFaces = new JCheckBoxMenuItem(
            ResourceHolder.getString(ResourceKey.LABEL,
                    "MENU_SlideFaces"),
            false);
    private ModelViewScreen screen;

    public MenuBar(ModelViewScreen screen) {
        this.screen = screen;
        menuFile.add(menuItemExportDXF);
        menuFile.add(menuItemExportOBJ);
        menuDisp.add(menuItemFlip);

        menuDisp.add(dispSubMenu);
        menuDisp.add(menuItemCrossLine);
        ButtonGroup dispGroup = new ButtonGroup();
        dispGroup.add(menuItemFillAlpha);
        dispSubMenu.add(menuItemFillAlpha);
        dispGroup.add(menuItemFillNone);
        dispSubMenu.add(menuItemFillNone);
        menuItemFillAlpha.setSelected(true);
        menuItemFlip.addActionListener(this);
        menuItemSlideFaces.addActionListener(this);
        menuItemFillColor.addActionListener(this);
        menuItemFillWhite.addActionListener(this);
        menuItemFillAlpha.addActionListener(this);
        menuItemFillNone.addActionListener(this);
        menuItemExportDXF.addActionListener(this);
        menuItemExportOBJ.addActionListener(this);

        menuItemCrossLine.addActionListener(this);
        add(menuFile);
        add(menuDisp);

    }

    private void exportFile(String ext) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileFilterEx(
                new String[] { "." + ext },
                "(*." + ext + ")" + ext
                        + ResourceHolder
                                .getString(ResourceKey.LABEL, "File")));
        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(this)) {
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                File file = new File(filePath);
                if (file.exists()) {
                    if (JOptionPane.showConfirmDialog(
                            null,
                            ResourceHolder
                                    .getString(ResourceKey.WARNING,
                                            "Warning_SameNameFileExist"),
                            ResourceHolder
                                    .getString(ResourceKey.LABEL,
                                            StringID.DIALOG_TITLE_SAVE_ID),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) {
                        return;
                    }
                }

                if (!filePath.endsWith("." + ext)) {
                    filePath += "." + ext;
                }
                switch (ext) {
                case "dxf":
                    ExporterDXF.exportModel(DocHolder.getDoc(),
                            filePath);
                    break;
                case "obj":
                    Exporter exporter = new ExporterOBJ2();
                    exporter.export(DocHolder.getDoc(), filePath);
                    break;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this, e.toString(),
                        ResourceHolder
                                .getString(ResourceKey.WARNING,
                                        "Error_FileSaveFailed"),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Doc document = DocHolder.getDoc();
        OrigamiModel origamiModel = document.getOrigamiModel();

        FolderTool folderTool = new FolderTool();
        if (e.getSource() == menuItemFlip) {
            folderTool.filpAll(origamiModel);
            screen.repaint();
        } else if (e.getSource() == menuItemSlideFaces) {
            folderTool.setFacesOutline(
                    origamiModel.getVertices(), origamiModel.getFaces(),
                    menuItemSlideFaces.isSelected());
            screen.repaint();
        } else if (e.getSource() == menuItemCrossLine) {
            PaintConfig.bDispCrossLine = menuItemCrossLine.isSelected();
            if (menuItemCrossLine.isSelected()) {
                screen.recalcCrossLine();
            } else {
                screen.repaint();
                MainFrame.getInstance().repaint();
            }
        } else if (e.getSource() == menuItemExportDXF) {
            exportFile("dxf");
        } else if (e.getSource() == menuItemExportOBJ) {
            exportFile("obj");
        } else if (e.getSource() == menuItemFillColor
                || e.getSource() == menuItemFillWhite
                || e.getSource() == menuItemFillAlpha
                || e.getSource() == menuItemFillNone) {
            if (menuItemFillColor.isSelected()) {
                PaintConfig.modelDispMode = Constants.ModelDispMode.FILL_COLOR;
            } else if (menuItemFillWhite.isSelected()) {
                PaintConfig.modelDispMode = Constants.ModelDispMode.FILL_WHITE;
            } else if (menuItemFillAlpha.isSelected()) {
                PaintConfig.modelDispMode = Constants.ModelDispMode.FILL_ALPHA;
            } else if (menuItemFillNone.isSelected()) {
                PaintConfig.modelDispMode = Constants.ModelDispMode.FILL_NONE;
            }

            System.out.println("fillMode" + PaintConfig.modelDispMode);
            screen.repaint();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (ModelFrameSettingDB.getInstance().isFrameVisible()) {
            setVisible(true);
            screen.resetViewMatrix();
            menuItemSlideFaces.setSelected(false);
            repaint();

        }
    }
}
