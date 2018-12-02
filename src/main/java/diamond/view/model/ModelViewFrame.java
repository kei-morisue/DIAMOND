/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2005-2009 Jun Mitani http://mitani.cs.tsukuba.ac.jp/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package diamond.view.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollBar;

import diamond.DIAMOND;
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
import diamond.viewsetting.model.ModelFrameSettingDB;

/**
 * A frame to show a transparent folded model.
 * @author Koji
 *
 */
public class ModelViewFrame extends JFrame
implements ActionListener, AdjustmentListener, Observer{

    private ModelFrameSettingDB setting = ModelFrameSettingDB.getInstance();

    ModelViewScreen screen;
    private JMenu menuDisp = new JMenu(ResourceHolder.getInstance()
            .getString(ResourceKey.LABEL, "MENU_Disp"));
    private JMenu menuFile = new JMenu(
            ResourceHolder.getInstance().getString(ResourceKey.LABEL, "File"));
    private JMenuItem menuItemExportDXF = new JMenuItem(
            ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                    "MENU_ExportModelLine_DXF"));
    private JMenuItem menuItemExportOBJ = new JMenuItem("Export to OBJ file");
    private JMenuItem menuItemFlip = new JMenuItem(ResourceHolder.getInstance()
            .getString(ResourceKey.LABEL, "MENU_Invert"));
    private JCheckBoxMenuItem menuItemCrossLine = new JCheckBoxMenuItem("Show Cross-Line", false);
    public JCheckBoxMenuItem menuItemSlideFaces = new JCheckBoxMenuItem(
            ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                    "MENU_SlideFaces"),
            false);
    public JLabel hintLabel = new JLabel(ResourceHolder.getInstance()
            .getString(ResourceKey.LABEL, "Basic"));
    private JMenu dispSubMenu = new JMenu(ResourceHolder.getInstance()
            .getString(ResourceKey.LABEL, "MENU_DispType"));
    private JRadioButtonMenuItem menuItemFillColor = new JRadioButtonMenuItem(
            ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                    "MENU_FillColor"));
    private JRadioButtonMenuItem menuItemFillWhite = new JRadioButtonMenuItem(
            ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                    "MENU_FillWhite"));
    private JRadioButtonMenuItem menuItemFillAlpha = new JRadioButtonMenuItem(
            ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                    "MENU_FillAlpha"));
    private JRadioButtonMenuItem menuItemFillNone = new JRadioButtonMenuItem(
            ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                    "MENU_DrawLines"));
    private JScrollBar scrollBarAngle = new JScrollBar(JScrollBar.HORIZONTAL, 90, 5, 0, 185);
    private JScrollBar scrollBarPosition = new JScrollBar(JScrollBar.VERTICAL, 0, 5, -150, 150);

    public ModelViewFrame() {

        setting.addObserver(this);

        setTitle(ResourceHolder.getInstance()
                .getString(ResourceKey.LABEL, "ExpectedFoldedOrigami"));
        screen = new ModelViewScreen();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(screen, BorderLayout.CENTER);
        getContentPane().add(hintLabel, BorderLayout.SOUTH);
        getContentPane().add(scrollBarAngle, BorderLayout.NORTH);
        getContentPane().add(scrollBarPosition, BorderLayout.WEST);

        // Construct menu bar
        JMenuBar menuBar = new JMenuBar();

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
        menuBar.add(menuFile);
        menuBar.add(menuDisp);

        setJMenuBar(menuBar);

        scrollBarAngle.addAdjustmentListener(this);
        scrollBarPosition.addAdjustmentListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Doc document = DocHolder.getInstance().getDoc();
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
                DIAMOND.mainFrame.repaint();
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
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getSource() == scrollBarAngle) {
            screen.setCrossLineAngle(e.getValue());
        } else if (e.getSource() == scrollBarPosition) {
            screen.setCrossLinePosition(e.getValue());
        }

    }

    private void exportFile(String ext) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileFilterEx(
                new String[] { "." + ext },
                "(*." + ext + ")" + ext
                        + ResourceHolder.getInstance()
                                .getString(ResourceKey.LABEL, "File")));
        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(this)) {
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                File file = new File(filePath);
                if (file.exists()) {
                    if (JOptionPane.showConfirmDialog(
                            null,
                            ResourceHolder.getInstance()
                                    .getString(ResourceKey.WARNING,
                                            "Warning_SameNameFileExist"),
                            ResourceHolder.getInstance()
                                    .getString(ResourceKey.LABEL,
                                            "DialogTitle_FileSave"),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE)
                            != JOptionPane.YES_OPTION) {
                        return;
                    }
                }

                if (!filePath.endsWith("." + ext)) {
                    filePath += "." + ext;
                }
                switch (ext) {
                    case "dxf":
                        ExporterDXF.exportModel(DocHolder.getInstance().getDoc(), filePath);
                        break;
                    case "obj":
                        Exporter exporter = new ExporterOBJ2();
                        exporter.export(DocHolder.getInstance().getDoc(), filePath);
                        break;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this, e.toString(),
                        ResourceHolder.getInstance()
                                .getString(ResourceKey.WARNING,
                                        "Error_FileSaveFailed"),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (setting.isFrameVisible()) {
            setVisible(true);
            screen.resetViewMatrix();
            menuItemSlideFaces.setSelected(false);
            repaint();

        }
    }
}
