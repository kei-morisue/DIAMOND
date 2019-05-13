/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * @author long_
 *
 */
public class ProgressFrame extends JFrame {
    private static JProgressBar bar = new JProgressBar(0, 100);

    public ProgressFrame(String title) {
        super(title);
        getContentPane().add(bar);
        bar.setValue(0);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(300, 100);
    }

    public void done() {
        bar.setValue(100);
        setVisible(false);
    }
}
