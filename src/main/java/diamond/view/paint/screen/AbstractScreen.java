/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * @author long_
 *
 */
public abstract class AbstractScreen extends JPanel
        implements ComponentListener, Observer {
    public AbstractScreen() {
        addComponentListener(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
