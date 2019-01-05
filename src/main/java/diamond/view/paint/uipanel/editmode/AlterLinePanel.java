/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel.editmode;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import diamond.paint.creasepattern.tool.TypeForChange;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey.LABEL;
import diamond.viewsetting.paint.uipanel.FromLineTypeItemListener;
import diamond.viewsetting.paint.uipanel.ToLineTypeItemListener;
import diamond.viewsetting.paint.uipanel.UIPanelSettingDB;

/**
 * @author long_
 *
 */
public class AlterLinePanel extends JPanel implements Observer {
    private UIPanelSettingDB settingDB = UIPanelSettingDB.getInstance();
    private TypeForChange[] alterLine_comboData_to = { TypeForChange.FLIP,
            TypeForChange.RIDGE,
            TypeForChange.VALLEY, TypeForChange.AUX,
            TypeForChange.CUT, TypeForChange.DELETE };
    private TypeForChange[] alterLine_comboData_from = { TypeForChange.EMPTY,
            TypeForChange.RIDGE, TypeForChange.VALLEY };

    private JComboBox<TypeForChange> alterLine_combo_from = new JComboBox<>(
            alterLine_comboData_from);

    private JComboBox<TypeForChange> alterLine_combo_to = new JComboBox<>(
            alterLine_comboData_to);

    public AlterLinePanel() {
        add(new JLabel(
                ResourceHolder.getLabelString(
                        LABEL.CHANGE_LINE_TYPE_FROM)));
        add(alterLine_combo_from);
        add(new JLabel(
                ResourceHolder.getLabelString(
                        LABEL.CHANGE_LINE_TYPE_TO)));

        add(alterLine_combo_to);
        setVisible(false);
        settingDB.setTypeFrom(
                (TypeForChange) alterLine_combo_from.getSelectedItem());
        settingDB.setTypeTo(
                (TypeForChange) alterLine_combo_to.getSelectedItem());
        alterLine_combo_from.addItemListener(new FromLineTypeItemListener());
        alterLine_combo_to.addItemListener(new ToLineTypeItemListener());

        UIPanelSettingDB.getInstance().addObserver(this);

    }

    public static void update() {

    }

    @Override
    public void update(Observable o, Object arg) {
        UIPanelSettingDB setting = (UIPanelSettingDB) o;
        setVisible(setting.isAlterLineTypePanelVisible());
        repaint();
    }
}
