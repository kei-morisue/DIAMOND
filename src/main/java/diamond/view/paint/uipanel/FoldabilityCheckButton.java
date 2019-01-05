/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.fold.OrigamiModel;
import diamond.fold.OrigamiModelFactory;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey.LABEL;
import diamond.value.OriLine;
import diamond.view.check.FoldabilityCheckFrame;

/**
 * @author long_
 *
 */
public class FoldabilityCheckButton extends JButton implements ActionListener {

    public FoldabilityCheckButton() {
        super(ResourceHolder.getLabelString(LABEL.CHECK_WINDOW_TITLE));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Doc document = DocHolder.getDoc();
        OrigamiModel origamiModel;
        Collection<OriLine> creasePattern = document.getCreasePattern();

        origamiModel = OrigamiModelFactory.createOrigamiModelNoCleanup(
                creasePattern,
                document.getPaperSize());
        FoldabilityCheckFrame checker = new FoldabilityCheckFrame();
        checker.setModel(origamiModel, creasePattern);
        checker.setVisible(true);
    }
}
