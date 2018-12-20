/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import diamond.Config;
import diamond.paint.ScreenUpdaterInterface;
import diamond.paint.core.PaintConfig;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;
import diamond.viewsetting.paint.PaintScreenSettingDB;
import diamond.viewsetting.paint.ScreenUpdater;

/**
 * @author long_
 *
 */
public class GridPanel extends JPanel implements ActionListener, Observer {
    private static PaintScreenSettingDB mainScreenSetting = PaintScreenSettingDB
            .getInstance();

    JButton gridChangeButton = new JButton(
            ResourceHolder.getLabelString(
                    StringID.UI.GRID_SIZE_CHANGE_ID));
    JButton gridLargeButton = new JButton("x1/2");
    JButton gridSmallButton = new JButton("x2");

    JFormattedTextField textField;

    JCheckBox dispGridCheckBox = new JCheckBox(
            ResourceHolder.getLabelString(
                    StringID.UI.SHOW_GRID_ID),
            true);

    public GridPanel() {
        JPanel divideNumSpecPanel = new JPanel();
        JLabel gridLabel1 = new JLabel(
                ResourceHolder.getLabelString(
                        StringID.UI.GRID_DIVIDE_NUM_ID));

        textField = new JFormattedTextField(new DecimalFormat("#"));
        textField.setColumns(2);
        textField.setValue(new Integer(Config.DEFAULT_GRID_DIV_NUM));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        gridChangeButton.addActionListener(this);

        divideNumSpecPanel.add(gridLabel1);
        divideNumSpecPanel.add(textField);
        divideNumSpecPanel.add(gridChangeButton);

        JPanel gridButtonsPanel = new JPanel();
        gridButtonsPanel.add(gridSmallButton);
        gridButtonsPanel.add(gridLargeButton);

        add(dispGridCheckBox);
        add(divideNumSpecPanel);
        add(gridButtonsPanel);
        setLayout(new GridLayout(3, 1, 10, 2));
        setBorder(new EtchedBorder(BevelBorder.RAISED,
                getBackground().darker(), getBackground().brighter()));

        dispGridCheckBox.addActionListener(this);
        gridSmallButton.addActionListener(this);
        gridLargeButton.addActionListener(this);

        mainScreenSetting.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ScreenUpdaterInterface screenUpdater = ScreenUpdater.getInstance();

        if (ae.getSource() == dispGridCheckBox) {
            mainScreenSetting.setGridVisible(dispGridCheckBox.isSelected());
            mainScreenSetting.notifyObservers();

            screenUpdater.updateScreen();

        } else if (ae.getSource() == gridSmallButton) {
            if (PaintConfig.gridDivNum < 65) {
                PaintConfig.gridDivNum *= 2;
                textField.setValue(new Integer(PaintConfig.gridDivNum));

                screenUpdater.updateScreen();
            }
        } else if (ae.getSource() == gridLargeButton) {
            if (PaintConfig.gridDivNum > 3) {
                PaintConfig.gridDivNum /= 2;
                textField.setValue(new Integer(PaintConfig.gridDivNum));

                screenUpdater.updateScreen();
            }
        } else if (ae.getSource() == gridChangeButton) {
            int value;
            try {
                value = Integer.valueOf(textField.getText());
                if (value < 128 && value > 2) {
                    textField.setValue(value);
                    PaintConfig.gridDivNum = value;
                    screenUpdater.updateScreen();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (mainScreenSetting.isGridVisible() != dispGridCheckBox
                .isSelected()) {
            dispGridCheckBox.setSelected(mainScreenSetting.isGridVisible());
        }
        repaint();
    }
}
