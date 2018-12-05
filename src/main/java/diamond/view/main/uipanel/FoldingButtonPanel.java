/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.uipanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.fold.BoundBox;
import diamond.fold.FoldedModelInfo;
import diamond.fold.Folder;
import diamond.fold.OrigamiModel;
import diamond.fold.OrigamiModelFactory;
import diamond.paint.core.PaintConfig;
import diamond.paint.creasepattern.CreasePattern;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.value.OriLine;
import diamond.view.estimation.FoldabilityCheckFrameFactory;
import diamond.view.main.MainFrame;
import diamond.viewsetting.estimation.RenderFrameSettingDB;
import diamond.viewsetting.model.ModelFrameSettingDB;

/**
 * @author long_
 *
 */
public class FoldingButtonPanel extends JPanel implements ActionListener {
    JButton buildButton = new JButton(
            ResourceHolder.getString(ResourceKey.LABEL, StringID.UI.FOLD_ID));

    JButton buttonCheckWindow = new JButton(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.CHECK_WINDOW_ID));

    JCheckBox doFullEstimationCheckBox = new JCheckBox(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.UI.FULL_ESTIMATION_ID),
            false);

    public FoldingButtonPanel() {
        setLayout(new GridLayout(4, 1, 10, 2));
        add(buttonCheckWindow);
        add(buildButton);
        add(doFullEstimationCheckBox);

        doFullEstimationCheckBox.setSelected(true);

        buildButton.addActionListener(this);
        doFullEstimationCheckBox
                .addActionListener(
                        new DoFullEstimationCheckBoxActionListener());
        buttonCheckWindow
                .addActionListener(new ButtonCheckWindowListener());

    }

    private class ButtonCheckWindowListener implements ActionListener {
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
    }

    private class DoFullEstimationCheckBoxActionListener
            implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            PaintConfig.bDoFullEstimation = doFullEstimationCheckBox
                    .isSelected();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Doc document = DocHolder.getDoc();

        boolean buildOK = false;
        CreasePattern creasePattern = document.getCreasePattern();

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

    }

}
