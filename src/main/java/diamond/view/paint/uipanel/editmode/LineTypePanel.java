/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel.editmode;

import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.paint.util.LineTypeSetter;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;
import diamond.value.OriLine;
import diamond.viewsetting.main.uipanel.UIPanelSettingDB;

/**
 * @author long_
 *
 */
public class LineTypePanel extends JPanel implements Observer {
    JRadioButton lineTypeMountainButton = new JRadioButton(
            ResourceHolder.getLabelString(
                    StringID.UI.MOUNTAIN_ID));

    //    JPanel lineTypePanel = new JPanel();
    JRadioButton lineTypeSubButton = new JRadioButton(
            ResourceHolder.getLabelString( StringID.UI.AUX_ID));

    JRadioButton lineTypeValleyButton = new JRadioButton(
            ResourceHolder.getLabelString( StringID.UI.VALLEY_ID));

    public LineTypePanel() {
        ButtonGroup lineTypeGroup = new ButtonGroup();
        lineTypeGroup.add(lineTypeMountainButton);
        lineTypeGroup.add(lineTypeValleyButton);
        lineTypeGroup.add(lineTypeSubButton);

        setLayout(new GridBagLayout());
        add(lineTypeMountainButton);
        add(lineTypeValleyButton);
        add(lineTypeSubButton);

        lineTypeMountainButton.setSelected(true);

        setMnemonics();

        UIPanelSettingDB.getInstance().addObserver(this);
        lineTypeMountainButton.addActionListener(
                new LineTypeSetter(OriLine.TYPE_RIDGE));

        lineTypeValleyButton.addActionListener(
                new LineTypeSetter(OriLine.TYPE_VALLEY));

        lineTypeSubButton.addActionListener(
                new LineTypeSetter(OriLine.TYPE_NONE));
    }

    /**
     *
     */
    private void setMnemonics() {
        lineTypeSubButton.setMnemonic('A');
        lineTypeMountainButton.setMnemonic('M');
        lineTypeValleyButton.setMnemonic('V');

    }

    @Override
    public void update(Observable o, Object arg) {
        UIPanelSettingDB setting = UIPanelSettingDB.getInstance();
        lineTypeMountainButton
                .setEnabled(setting.isMountainButtonEnabled());
        lineTypeValleyButton.setEnabled(setting.isValleyButtonEnabled());
        lineTypeSubButton.setEnabled(setting.isAuxButtonEnabled());
        repaint();
    }

}
