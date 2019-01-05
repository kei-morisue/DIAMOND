/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.folded;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.fold.FoldedModelInfo;
import diamond.fold.Folder;
import diamond.fold.OrigamiModel;
import diamond.fold.OrigamiModelFactory;
import diamond.paint.creasepattern.CreasePattern;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey;
import diamond.viewsetting.folded.RenderFrameSettingDB;

/**
 * @author long_
 *
 */
public class FoldingAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean buildOK = false;
        Doc document = DocHolder.getDoc();
        CreasePattern creasePattern = document.getCreasePattern();
        OrigamiModel origamiModel = OrigamiModelFactory
                .createOrigamiModelNoCleanup(
                        creasePattern, document.getPaperSize());
        FoldedModelInfo foldedModelInfo = document.getFoldedModelInfo();

        if (origamiModel.isProbablyFoldable()) {
            buildOK = true;
        } else {
            if (JOptionPane.showConfirmDialog(
                    null,
                    ResourceHolder.getWarningString(
                            StringKey.WARNING.FOLD_FAILED_DUPLICATION),
                    "Failed", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                origamiModel = OrigamiModelFactory.createOrigamiModel(
                        creasePattern, document.getPaperSize());
                if (origamiModel.isProbablyFoldable()) {
                    buildOK = true;
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            ResourceHolder.getWarningString(
                                    StringKey.WARNING.FOLD_FAILED_WRONG_STRUCTURE),
                            "Failed Level1", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        Folder folder = new Folder();

        if (buildOK) {
            folder.fold(origamiModel, foldedModelInfo);
            document.setOrigamiModel(origamiModel);

            if (foldedModelInfo.getFoldablePatternCount() != 0) {
                RenderFrameSettingDB renderSetting = RenderFrameSettingDB
                        .getInstance();
                renderSetting.setFrameVisible(true);
                renderSetting.notifyObservers();
            }
        }
    }

}
