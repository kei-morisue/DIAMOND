/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.uipanel;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.appstate.InputCommandStatePopper;
import diamond.bind.ButtonFactory;
import diamond.bind.PaintActionButtonFactory;
import diamond.bind.binder.BinderInterface;
import diamond.bind.binder.ViewChangeBinder;
import diamond.paint.creasepattern.tool.TypeForChange;
import diamond.resource.StringID;
import diamond.view.main.uipanel.editmode.AlterLinePanel;
import diamond.view.main.uipanel.editmode.LineTypePanel;
import diamond.viewsetting.ChangeViewSetting;
import diamond.viewsetting.main.uipanel.ChangeOnPaintInputButtonSelected;
import diamond.viewsetting.main.uipanel.UIPanelSettingDB;

/**
 * @author long_
 *
 */
public class EditModeButtons {
    private ButtonGroup editModeGroup;
    private ButtonFactory buttonFactory = new PaintActionButtonFactory();

    private BinderInterface<ChangeViewSetting> viewChangeBinder = new ViewChangeBinder();
    TypeForChange[] alterLine_comboData_to = { TypeForChange.RIDGE,
            TypeForChange.VALLEY, TypeForChange.AUX,
            TypeForChange.CUT, TypeForChange.DELETE, TypeForChange.FLIP };
    TypeForChange[] alterLine_comboData_from = { TypeForChange.EMPTY,
            TypeForChange.RIDGE, TypeForChange.VALLEY };

    private JRadioButton editModeDeleteLineButton;
    private JRadioButton editModeDeleteVertex;
    private JRadioButton editModeInputLineButton;
    private JRadioButton editModeLineTypeButton;
    private JRadioButton editModePickLineButton;
    private JRadioButton editModeAddVertex;

    private JPanel basePanel;

    public EditModeButtons(JPanel __basePanel) {
        basePanel = __basePanel;
        createButtons();
        editModeInputLineButton.setSelected(true);
        editModeGroup = new ButtonGroup();
        editModeGroup.add(editModeInputLineButton);
        editModeGroup.add(editModePickLineButton);
        editModeGroup.add(editModeDeleteLineButton);
        editModeGroup.add(editModeLineTypeButton);
        editModeGroup.add(editModeAddVertex);
        editModeGroup.add(editModeDeleteVertex);

        basePanel.add(editModeInputLineButton, LayoutUtil.buildGBC(1, 0, 4));
        basePanel.add(new LineTypePanel(), LayoutUtil.buildGBC(1, 1, 4));
        basePanel.add(editModePickLineButton, LayoutUtil.buildGBC(1, 2, 4));
        basePanel.add(editModeDeleteLineButton, LayoutUtil.buildGBC(1, 3, 4));
        basePanel.add(editModeLineTypeButton, LayoutUtil.buildGBC(1, 4, 4));
        basePanel.add(new AlterLinePanel(), LayoutUtil.buildGBC(1, 5, 4));
        basePanel.add(editModeAddVertex, LayoutUtil.buildGBC(1, 6, 4));
        basePanel.add(editModeDeleteVertex, LayoutUtil.buildGBC(1, 7, 4));
        setMnemonics();

        editModeInputLineButton
                .addActionListener(new InputCommandStatePopper());
    }

    /**
     *
     */
    private void setMnemonics() {
        editModeInputLineButton.setMnemonic('I');
        editModePickLineButton.setMnemonic('S');
        editModeDeleteLineButton.setMnemonic('D');
        editModeLineTypeButton.setMnemonic('T');
        editModeDeleteVertex.setMnemonic('L');
    }

    private void createButtons() {
        editModeAddVertex = (JRadioButton) buttonFactory
                .create(basePanel, JRadioButton.class, StringID.ADD_VERTEX_ID);
        editModeDeleteLineButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class, StringID.DELETE_LINE_ID);
        editModeDeleteVertex = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class,
                        StringID.DELETE_VERTEX_ID);

        editModeInputLineButton = (JRadioButton) viewChangeBinder
                .createButton(
                        JRadioButton.class,
                        new ChangeOnPaintInputButtonSelected(),
                        StringID.UI.INPUT_LINE_ID);
        editModeLineTypeButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class,
                        StringID.CHANGE_LINE_TYPE_ID);
        editModePickLineButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class, StringID.SELECT_ID);

    }

    public void updateEditModeButtonSelection(UIPanelSettingDB setting) {
        switch (setting.getSelectedMode()) {
        case INPUT:
            selectEditModeButton(editModeInputLineButton);
            break;
        case SELECT:
            selectEditModeButton(editModePickLineButton);
            break;
        default:
            break;
        }
    }

    private void selectEditModeButton(AbstractButton modeButton) {
        editModeGroup.setSelected(modeButton.getModel(), true);
    }
}
