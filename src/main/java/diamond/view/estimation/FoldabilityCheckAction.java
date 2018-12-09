/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.estimation;

import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JFrame;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.fold.OrigamiModel;
import diamond.fold.OrigamiModelFactory;
import diamond.value.OriLine;

/**
 * @author long_
 *
 */
public class FoldabilityCheckAction implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Doc document = DocHolder.getDoc();
        OrigamiModel origamiModel;
        Collection<OriLine> creasePattern = document
                .getCreasePattern();

        origamiModel = OrigamiModelFactory.createOrigamiModel(
                creasePattern, document.getPaperSize());
        FoldabilityCheckFrameFactory checkerFactory = new FoldabilityCheckFrameFactory();
        JFrame checker = checkerFactory
                .createFrame(origamiModel, creasePattern);
        checker.setVisible(true);
    }
}
