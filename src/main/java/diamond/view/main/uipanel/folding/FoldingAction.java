/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.uipanel.folding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.fold.BoundBox;
import diamond.fold.FoldedModelInfo;
import diamond.fold.Folder;
import diamond.fold.OrigamiModel;
import diamond.fold.OrigamiModelFactory;
import diamond.paint.creasepattern.CreasePattern;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;
import diamond.viewsetting.estimation.RenderFrameSettingDB;
import diamond.viewsetting.model.ModelFrameSettingDB;

/**
 * @author long_
 *
 */
public class FoldingAction implements ActionListener {
    private boolean buildOK;

    @Override
    public void actionPerformed(ActionEvent e) {
        Doc document = DocHolder.getDoc();

        buildOK = false;
        CreasePattern creasePattern = document.getCreasePattern();

        OrigamiModel origamiModel = buildOrigamiModel(creasePattern, document);

        FoldedModelInfo foldedModelInfo = document.getFoldedModelInfo();
        if (foldedModelInfo.getFoldablePatternCount() != 0) {
            RenderFrameSettingDB renderSetting = RenderFrameSettingDB
                    .getInstance();
            renderSetting.setFrameVisible(true);
            renderSetting.notifyObservers();
        }

        Folder folder = new Folder();
        if (buildOK) {
            folder.fold(origamiModel, foldedModelInfo);
            document.setOrigamiModel(origamiModel);
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

    private OrigamiModel buildOrigamiModel(CreasePattern creasePattern,
            Doc document) {
        OrigamiModel origamiModel = OrigamiModelFactory
                .createOrigamiModelNoCleanup(
                        creasePattern, document.getPaperSize());
        if (origamiModel.isProbablyFoldable()) {
            buildOK = true;
        } else {
            if (confirmDuplicatedLines() == JOptionPane.YES_OPTION) {
                origamiModel = OrigamiModelFactory.createOrigamiModel(
                        creasePattern, document.getPaperSize());
                if (origamiModel.isProbablyFoldable()) {
                    buildOK = true;
                } else {
                    showFailureMessage();
                }
            }
        }
        return origamiModel;
    }

    private static void showFailureMessage() {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(),
                ResourceHolder.getString(ResourceKey.WARNING,
                        StringID.Warning.FOLD_FAILED_WRONG_STRUCTURE_ID),
                "Failed Level1",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static int confirmDuplicatedLines() {
        return JOptionPane.showConfirmDialog(
                MainFrame.getInstance(),
                ResourceHolder.getString(ResourceKey.WARNING,
                        StringID.Warning.FOLD_FAILED_DUPLICATION_ID),
                "Failed", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }
}
