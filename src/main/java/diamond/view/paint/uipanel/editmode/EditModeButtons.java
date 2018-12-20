/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel.editmode;

import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.appstate.InputCommandStatePopper;
import diamond.bind.button.editline.AddVertexButtonFactory;
import diamond.bind.button.editline.ChangeLineTypeButtonFactory;
import diamond.bind.button.editline.DeleteLineButtonFactory;
import diamond.bind.button.editline.DeleteVertexButtonFactory;
import diamond.bind.button.editline.InputLineButtonFactory;
import diamond.bind.button.editline.SelectLineButtonFactory;
import diamond.paint.creasepattern.tool.TypeForChange;
import diamond.view.paint.uipanel.LayoutUtil;
import diamond.viewsetting.paint.uipanel.UIPanelSettingDB;

/**
 * @author long_
 *
 */
public class EditModeButtons extends JPanel implements Observer {
    private ButtonGroup editModeGroup;

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

    public EditModeButtons() {
        createButtons();
        setMnemonics();
        editModeInputLineButton
                .addActionListener(new InputCommandStatePopper());
        editModeInputLineButton.setSelected(true);
        add();
        UIPanelSettingDB.getInstance().addObserver(this);
    }

    private EditModeButtons add() {
        editModeGroup = new ButtonGroup();
        editModeGroup.add(editModeInputLineButton);
        editModeGroup.add(editModePickLineButton);
        editModeGroup.add(editModeDeleteLineButton);
        editModeGroup.add(editModeLineTypeButton);
        editModeGroup.add(editModeAddVertex);
        editModeGroup.add(editModeDeleteVertex);
        setLayout(new GridBagLayout());
        add(editModeInputLineButton, LayoutUtil.buildGBC(1, 0, 4));
        add(new LineTypePanel(), LayoutUtil.buildGBC(1, 1, 4));
        add(editModePickLineButton, LayoutUtil.buildGBC(1, 2, 4));
        add(editModeDeleteLineButton, LayoutUtil.buildGBC(1, 3, 4));
        add(editModeLineTypeButton, LayoutUtil.buildGBC(1, 4, 4));
        add(new AlterLinePanel(), LayoutUtil.buildGBC(1, 5, 4));
        add(editModeAddVertex, LayoutUtil.buildGBC(1, 6, 4));
        add(editModeDeleteVertex, LayoutUtil.buildGBC(1, 7, 4));
        return this;
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
        editModeAddVertex = new AddVertexButtonFactory()
                .create(JRadioButton.class);
        editModeDeleteLineButton = new DeleteLineButtonFactory()
                .create(JRadioButton.class);
        editModeDeleteVertex = new DeleteVertexButtonFactory()
                .create(JRadioButton.class);
        editModeLineTypeButton = new ChangeLineTypeButtonFactory()
                .create(JRadioButton.class);
        editModePickLineButton = new SelectLineButtonFactory()
                .create(JRadioButton.class);
        editModeInputLineButton = new InputLineButtonFactory()
                .create(JRadioButton.class);

    }

    private void selectEditModeButton(AbstractButton modeButton) {
        editModeGroup.setSelected(modeButton.getModel(), true);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch (UIPanelSettingDB.getInstance().getSelectedMode()) {
        case INPUT:
            selectEditModeButton(editModeInputLineButton);
            break;
        case SELECT:
            selectEditModeButton(editModePickLineButton);
            break;
        default:
            break;
        }
        repaint();
    }
}
