/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel.inputline.valuepanel;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import diamond.bind.state.action.PaintActionSetter;
import diamond.paint.core.GraphicMouseAction;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.main.uipanel.ChangeOnByValueButtonSelected;
import diamond.viewsetting.main.uipanel.UIPanelSettingDB;

/**
 * @author long_
 *
 */
public abstract class ValuePanel extends JPanel implements Observer {

    public ValuePanel() {
        setVisible(false);
        setLayout(new FlowLayout());
        add(createLabel());
        add(createTextField());
        add(createButton());
        UIPanelSettingDB.getInstance().addObserver(this);
    }

    private JButton createButton() {
        JButton button = new JButton(
                ResourceHolder.getLabelString(
                        StringID.UI.MEASURE_ID));
        button.addActionListener(
                new ViewChangeListener(new ChangeOnByValueButtonSelected()));
        button.addActionListener(
                new PaintActionSetter(createMeasuringActionListner()));
        return button;
    }

    protected abstract TextField createTextField();

    protected abstract GraphicMouseAction createMeasuringActionListner();

    protected abstract String createStringId();

    protected JLabel createLabel() {
        return new JLabel(
                ResourceHolder.getLabelString(
                        createStringId()));
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(UIPanelSettingDB.getInstance().isValuePanelVisible());
        repaint();
    }
}
