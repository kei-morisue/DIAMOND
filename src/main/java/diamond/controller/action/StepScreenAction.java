/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.AbstractStep;

/**
 * @author Kei Morisue
 *
 */
public class StepScreenAction extends AbstractScreenAction {
    private Cp cp;

    public StepScreenAction(AbstractStep screen, Cp cp) {
        super(screen);
        this.cp = cp;
    }

    @Override
    protected void zoom(MouseWheelEvent e) {
        super.zoom(e);
        cp.getTransform().zoom(zoomAmount(e));
    }

    @Override
    protected void translate(MouseEvent e) {
        super.translate(e);
    }

    @Override
    protected void rotate(MouseWheelEvent e) {
        super.rotate(e);
        cp.getTransform().rotate(thetaAmount(e));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

}
