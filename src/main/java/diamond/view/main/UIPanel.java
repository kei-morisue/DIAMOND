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

package diamond.view.main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import diamond.Config;
import diamond.bind.ButtonFactory;
import diamond.bind.PaintActionButtonFactory;
import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.fold.BoundBox;
import diamond.fold.FoldedModelInfo;
import diamond.fold.Folder;
import diamond.fold.OrigamiModel;
import diamond.fold.OrigamiModelFactory;
import diamond.paint.ScreenUpdaterInterface;
import diamond.paint.byvalue.ValueDB;
import diamond.paint.core.PaintConfig;
import diamond.paint.creasepattern.CreasePattern;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.value.OriLine;
import diamond.view.estimation.FoldabilityCheckFrameFactory;
import diamond.view.main.uipanel.EditModeButtons;
import diamond.view.main.uipanel.inputline.InputLineCommandButtons;
import diamond.view.main.uipanel.inputline.valuepanel.AngleValuePanel;
import diamond.view.main.uipanel.inputline.valuepanel.LengthValuePanel;
import diamond.viewsetting.estimation.RenderFrameSettingDB;
import diamond.viewsetting.main.MainScreenSettingDB;
import diamond.viewsetting.main.ScreenUpdater;
import diamond.viewsetting.main.uipanel.UIPanelSettingDB;
import diamond.viewsetting.model.ModelFrameSettingDB;

public class UIPanel extends JPanel
        implements ActionListener, PropertyChangeListener, Observer {

    private MainScreenSettingDB screenDB = MainScreenSettingDB.getInstance();

    //---------------------------------------------------------------------------------------------------------------------------
    // Binding edit mode

    private UIPanelSettingDB settingDB = UIPanelSettingDB.getInstance();

    EditModeButtons editModeButtons;

    JButton buildButton = new JButton(
            ResourceHolder.getString(ResourceKey.LABEL, StringID.UI.FOLD_ID));

    JButton buttonCheckWindow = new JButton(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.CHECK_WINDOW_ID));

    ButtonFactory buttonFactory = new PaintActionButtonFactory();

    JCheckBox dispAuxLinesCheckBox = new JCheckBox(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.SHOW_AUX_ID),
            true);

    JCheckBox dispGridCheckBox = new JCheckBox(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.SHOW_GRID_ID),
            true);

    JCheckBox dispMVLinesCheckBox = new JCheckBox(
            ResourceHolder.getString(ResourceKey.LABEL, StringID.UI.SHOW_MV_ID),
            true);

    JCheckBox dispVertexCheckBox = new JCheckBox(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.SHOW_VERTICES_ID),
            false);

    JCheckBox doFullEstimationCheckBox = new JCheckBox(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.FULL_ESTIMATION_ID),
            false);

    JButton gridChangeButton = new JButton(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.GRID_SIZE_CHANGE_ID));
    JButton gridLargeButton = new JButton("x1/2");

    JPanel gridPanel = new JPanel();
    JButton gridSmallButton = new JButton("x2");

    JPanel mainPanel = new JPanel();

    JButton resetButton = new JButton("Reset");
    PainterScreen screen;
    //    JPanel subPanel1 = new JPanel();
    //    JPanel subPanel2 = new JPanel();
    //    JFormattedTextField textFieldAngle;

    JFormattedTextField textFieldGrid;

    //    JFormattedTextField textFieldLength;
    JButton buttonAngle = new JButton(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.MEASURE_ID));

    public UIPanel(PainterScreen __screen) {
        setPreferredSize(new Dimension(200, 800));
        this.screen = __screen;

        settingDB.addObserver(this);
        screenDB.addObserver(this);

        mainPanel.setLayout(new GridBagLayout());
        editModeButtons = new EditModeButtons(mainPanel);
        int n = 8;
        new InputLineCommandButtons(mainPanel);

        n++;

        setLayout(new FlowLayout());

        add(mainPanel);

        //------------------------------------
        // Panel input for length and angle
        //------------------------------------

        add(new LengthValuePanel());
        add(new AngleValuePanel());

        //------------------------------------
        // For the grid panel
        //------------------------------------
        JPanel divideNumSpecPanel = new JPanel();
        JLabel gridLabel1 = new JLabel(
                ResourceHolder.getString(ResourceKey.LABEL,
                        StringID.UI.GRID_DIVIDE_NUM_ID));

        textFieldGrid = new JFormattedTextField(new DecimalFormat("#"));
        textFieldGrid.setColumns(2);
        textFieldGrid.setValue(new Integer(Config.DEFAULT_GRID_DIV_NUM));
        textFieldGrid.setHorizontalAlignment(JTextField.RIGHT);
        gridChangeButton.addActionListener(this);

        divideNumSpecPanel.add(gridLabel1);
        divideNumSpecPanel.add(textFieldGrid);
        divideNumSpecPanel.add(gridChangeButton);

        JPanel gridButtonsPanel = new JPanel();
        gridButtonsPanel.add(gridSmallButton);
        n++;
        gridButtonsPanel.add(gridLargeButton);
        n++;

        n = 0;
        gridPanel.add(dispGridCheckBox);
        n++;
        gridPanel.add(divideNumSpecPanel);
        n++;
        gridPanel.add(gridButtonsPanel);
        n++;
        gridPanel.setLayout(new GridLayout(n, 1, 10, 2));
        gridPanel.setBorder(new EtchedBorder(BevelBorder.RAISED,
                getBackground().darker(), getBackground().brighter()));
        add(gridPanel);

        //------------------------------------
        // Buttons panel
        //------------------------------------
        JPanel buttonsPanel = new JPanel();
        n = 0;
        buttonsPanel.add(dispMVLinesCheckBox);
        n++;
        buttonsPanel.add(dispAuxLinesCheckBox);
        n++;
        buttonsPanel.add(dispVertexCheckBox);
        n++;
        buttonsPanel.add(buttonCheckWindow);
        n++;
        buttonsPanel.add(buildButton);
        n++;
        buttonsPanel.add(doFullEstimationCheckBox);
        n++;
        buttonsPanel.setLayout(new GridLayout(n, 1, 10, 2));

        add(buttonsPanel);

        ValueDB.getInstance().addObserver(this);

        addListenerToComponents();

    }

    private void addListenerToComponents() {

        dispGridCheckBox.addActionListener(this);
        gridSmallButton.addActionListener(this);
        gridLargeButton.addActionListener(this);
        buildButton.addActionListener(this);
        resetButton.addActionListener(this);
        dispVertexCheckBox.addActionListener(this);
        dispVertexCheckBox.setSelected(true);
        PaintConfig.dispVertex = true;
        dispMVLinesCheckBox
                .addActionListener(new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        PaintConfig.dispMVLines = dispMVLinesCheckBox
                                .isSelected();
                        screen.repaint();
                    }
                });
        dispAuxLinesCheckBox
                .addActionListener(new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        PaintConfig.dispAuxLines = dispAuxLinesCheckBox
                                .isSelected();
                        screen.repaint();
                    }
                });

        doFullEstimationCheckBox.setSelected(true);
        PaintConfig.bDoFullEstimation = true;
        doFullEstimationCheckBox
                .addActionListener(new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        PaintConfig.bDoFullEstimation = doFullEstimationCheckBox
                                .isSelected();
                        screen.repaint();
                    }
                });

        buttonCheckWindow
                .addActionListener(new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Doc document = DocHolder.getDoc();
                        OrigamiModel origamiModel;
                        Collection<OriLine> creasePattern = document
                                .getCreasePattern();

                        OrigamiModelFactory modelFactory = new OrigamiModelFactory();
                        origamiModel = modelFactory.createOrigamiModel3(
                                creasePattern, document.getPaperSize(), false);

                        FoldabilityCheckFrameFactory checkerFactory = new FoldabilityCheckFrameFactory();
                        JFrame checker = checkerFactory
                                .createFrame(origamiModel, creasePattern);
                        checker.setVisible(true);
                    }
                });

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Doc document = DocHolder.getDoc();

        ScreenUpdaterInterface screenUpdater = ScreenUpdater.getInstance();

        //TODO decompose this long long if-else.
        if (ae.getSource() == dispGridCheckBox) {
            screenDB.setGridVisible(dispGridCheckBox.isSelected());
            screenDB.notifyObservers();

            screenUpdater.updateScreen();

        } else if (ae.getSource() == gridSmallButton) {
            if (PaintConfig.gridDivNum < 65) {
                PaintConfig.gridDivNum *= 2;
                textFieldGrid.setValue(new Integer(PaintConfig.gridDivNum));

                screenUpdater.updateScreen();
            }
        } else if (ae.getSource() == gridLargeButton) {
            if (PaintConfig.gridDivNum > 3) {
                PaintConfig.gridDivNum /= 2;
                textFieldGrid.setValue(new Integer(PaintConfig.gridDivNum));

                screenUpdater.updateScreen();
            }
        } else if (ae.getSource() == dispVertexCheckBox) {
            PaintConfig.dispVertex = dispVertexCheckBox.isSelected();

            screenUpdater.updateScreen();
        } else if (ae.getSource() == resetButton) {
        } else if (ae.getSource() == buildButton) {
            boolean buildOK = false;
            CreasePattern creasePattern = document.getCreasePattern();

            //			if (document.buildOrigami3(origamiModel, false)) {
            OrigamiModelFactory modelFactory = new OrigamiModelFactory();
            OrigamiModel origamiModel = modelFactory.createOrigamiModel3(
                    creasePattern, document.getPaperSize());
            FoldedModelInfo foldedModelInfo = document.getFoldedModelInfo();

            if (origamiModel.isProbablyFoldable()) {
                buildOK = true;
            } else {
                if (JOptionPane.showConfirmDialog(
                        MainFrame.getInstance(),
                        ResourceHolder.getString(ResourceKey.WARNING,
                                StringID.Warning.FOLD_FAILED_DUPLICATION_ID),
                        "Failed", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

                    origamiModel = modelFactory
                            .createOrigamiModel3NoDuplicateLines(
                                    creasePattern, document.getPaperSize());
                    //if (document.buildOrigami3(origamiModel, false)) {
                    if (origamiModel.isProbablyFoldable()) {
                        buildOK = true;
                    } else {
                        JOptionPane.showMessageDialog(
                                MainFrame.getInstance(),
                                ResourceHolder.getString(ResourceKey.WARNING,
                                        StringID.Warning.FOLD_FAILED_WRONG_STRUCTURE_ID),
                                "Failed Level1",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            Folder folder = new Folder();

            if (buildOK) {
                folder.fold(origamiModel, foldedModelInfo);
                document.setOrigamiModel(origamiModel);

                //TODO move this block out of if(buildOK) statement.
                if (foldedModelInfo.getFoldablePatternCount() != 0) {
                    System.out.println("RenderFrame");
                    RenderFrameSettingDB renderSetting = RenderFrameSettingDB
                            .getInstance();
                    renderSetting.setFrameVisible(true);
                    renderSetting.notifyObservers();
                }

            } else {
                BoundBox boundBox = folder.foldWithoutLineType(origamiModel);
                foldedModelInfo.setBoundBox(boundBox);
                document.setOrigamiModel(origamiModel);
            }

            ModelFrameSettingDB modelSetting = ModelFrameSettingDB
                    .getInstance();
            modelSetting.setFrameVisible(true);
            modelSetting.notifyObservers();

        } else if (ae.getSource() == gridChangeButton) {
            int value;
            try {
                value = Integer.valueOf(textFieldGrid.getText());
                System.out.println("type");

                if (value < 128 && value > 2) {
                    textFieldGrid.setValue(value);
                    PaintConfig.gridDivNum = value;
                    screenUpdater.updateScreen();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
    }

    /**
     * observes DB for reflecting the changes to views.
     * toString() of given DB has to return a unique value among DB classes.
     * @param o Observable class which implements toString()
     *          to return its class name.
     * @param arg A parameter to specify the action
     *        for the given Observable object.
     */
    @Override
    public void update(Observable o, Object arg) {

        if (settingDB.hasGivenName(o.toString())) {
            UIPanelSettingDB setting = (UIPanelSettingDB) o;

            editModeButtons.updateEditModeButtonSelection(setting);

            repaint();
        } else if (screenDB.hasGivenName(o.toString())) {
            if (screenDB.isGridVisible() != dispGridCheckBox.isSelected()) {
                dispGridCheckBox.setSelected(screenDB.isGridVisible());

            }

            repaint();
        }

    }

}
