/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel.inputline;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.bind.button.addline.BisectorButtonFactory;
import diamond.bind.button.addline.LineByValueButtonFactory;
import diamond.bind.button.addline.MirrorCopyButtonFactory;
import diamond.bind.button.addline.P2PBisectorButtonFactory;
import diamond.bind.button.addline.P2PLineButtonFactory;
import diamond.bind.button.addline.P2PSegmentButtonFactory;
import diamond.bind.button.addline.SymmetricalLineButtonFactory;
import diamond.bind.button.addline.TriangleSplitButtonFactory;
import diamond.bind.button.addline.VerticalLineButtonFactory;
import diamond.file.ImageResourceLoader;
import diamond.paint.EditMode;
import diamond.view.paint.uipanel.LayoutUtil;
import diamond.viewsetting.main.uipanel.UIPanelSettingDB;

/**
 * @author long_
 *
 */
public class InputLineButtons extends JPanel implements Observer {
    private JRadioButton lineInputAngleBisectorButton;
    private JRadioButton lineInputByValueButton;
    private JRadioButton lineInputDirectVButton;
    private JRadioButton lineInputMirrorButton;
    private JRadioButton lineInputOnVButton;
    private JRadioButton lineInputPBisectorButton;
    private JRadioButton lineInputSymmetricButton;
    private JRadioButton lineInputTriangleSplitButton;
    private JRadioButton lineInputVerticalLineButton;

    public InputLineButtons() {
        createButtons();
        setIcons();
        setMnemonics();
        add();
        lineInputDirectVButton.doClick();
        lineInputDirectVButton.setSelected(true);
        UIPanelSettingDB.getInstance().addObserver(this);
    }

    private InputLineButtons add() {
        setLayout(new GridBagLayout());
        JLabel label = new JLabel("Command (1...9)");
        label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        add(label, LayoutUtil.buildGBC(1, 0, 4));
        ButtonGroup lineInputGroup = new ButtonGroup();
        lineInputGroup.add(lineInputDirectVButton);
        lineInputGroup.add(lineInputOnVButton);
        lineInputGroup.add(lineInputTriangleSplitButton);
        lineInputGroup.add(lineInputAngleBisectorButton);
        lineInputGroup.add(lineInputVerticalLineButton);
        lineInputGroup.add(lineInputSymmetricButton);
        lineInputGroup.add(lineInputMirrorButton);
        lineInputGroup.add(lineInputByValueButton);
        lineInputGroup.add(lineInputPBisectorButton);
        addPaintActionButtons(4, 9);
        return this;
    }

    /**
     *
     */
    private void createButtons() {
        lineInputAngleBisectorButton = new BisectorButtonFactory()
                .create(JRadioButton.class);
        lineInputByValueButton = new LineByValueButtonFactory()
                .create(JRadioButton.class);
        lineInputDirectVButton = new P2PSegmentButtonFactory()
                .create(JRadioButton.class);
        lineInputMirrorButton = new MirrorCopyButtonFactory()
                .create(JRadioButton.class);
        lineInputOnVButton = new P2PLineButtonFactory()
                .create(JRadioButton.class);
        lineInputPBisectorButton = new P2PBisectorButtonFactory()
                .create(JRadioButton.class);
        lineInputSymmetricButton = new SymmetricalLineButtonFactory()
                .create(JRadioButton.class);
        lineInputTriangleSplitButton = new TriangleSplitButtonFactory()
                .create(JRadioButton.class);
        lineInputVerticalLineButton = new VerticalLineButtonFactory()
                .create(JRadioButton.class);

    }

    private void setIcons() {
        ImageResourceLoader imgLoader = new ImageResourceLoader();
        lineInputDirectVButton
                .setIcon(imgLoader.loadAsIcon("icon/segment.gif"));
        lineInputDirectVButton
                .setSelectedIcon(imgLoader.loadAsIcon("icon/segment_p.gif"));

        lineInputOnVButton.setIcon(imgLoader.loadAsIcon("icon/line.gif"));
        lineInputOnVButton
                .setSelectedIcon(imgLoader.loadAsIcon("icon/line_p.gif"));

        lineInputPBisectorButton
                .setIcon(imgLoader.loadAsIcon("icon/pbisector.gif"));
        lineInputPBisectorButton
                .setSelectedIcon(imgLoader.loadAsIcon("icon/pbisector_p.gif"));

        lineInputAngleBisectorButton
                .setIcon(imgLoader.loadAsIcon("icon/bisector.gif"));
        lineInputAngleBisectorButton
                .setSelectedIcon(imgLoader.loadAsIcon("icon/bisector_p.gif"));

        lineInputTriangleSplitButton
                .setIcon(imgLoader.loadAsIcon("icon/incenter.gif"));
        lineInputTriangleSplitButton
                .setSelectedIcon(imgLoader.loadAsIcon("icon/incenter_p.gif"));

        lineInputVerticalLineButton
                .setIcon(imgLoader.loadAsIcon("icon/vertical.gif"));
        lineInputVerticalLineButton
                .setSelectedIcon(imgLoader.loadAsIcon("icon/vertical_p.gif"));

        lineInputSymmetricButton
                .setIcon(imgLoader.loadAsIcon("icon/symmetry.gif"));
        lineInputSymmetricButton
                .setSelectedIcon(imgLoader.loadAsIcon("icon/symmetry_p.gif"));

        lineInputMirrorButton.setIcon(imgLoader.loadAsIcon("icon/mirror.gif"));
        lineInputMirrorButton
                .setSelectedIcon(imgLoader.loadAsIcon("icon/mirror_p.gif"));

        lineInputByValueButton
                .setIcon(imgLoader.loadAsIcon("icon/by_value.gif"));
        lineInputByValueButton
                .setSelectedIcon(imgLoader.loadAsIcon("icon/by_value_p.gif"));
    }

    private void setMnemonics() {
        lineInputDirectVButton.setMnemonic('1');
        lineInputOnVButton.setMnemonic('2');
        lineInputPBisectorButton.setMnemonic('3');
        lineInputAngleBisectorButton.setMnemonic('4');
        lineInputTriangleSplitButton.setMnemonic('5');
        lineInputVerticalLineButton.setMnemonic('6');
        lineInputSymmetricButton.setMnemonic('7');
        lineInputMirrorButton.setMnemonic('8');
        lineInputByValueButton.setMnemonic('9');

    }

    private void addPaintActionButtons(int gridWidth, int gridy_start) {

        int count = 0;
        // put operation buttons in order
        addPaintActionButton(lineInputDirectVButton, gridWidth, gridy_start,
                count);
        count++;
        addPaintActionButton(lineInputOnVButton, gridWidth, gridy_start, count);
        count++;
        addPaintActionButton(lineInputPBisectorButton, gridWidth, gridy_start,
                count);
        count++;
        addPaintActionButton(lineInputAngleBisectorButton, gridWidth,
                gridy_start, count);
        count++;
        addPaintActionButton(lineInputTriangleSplitButton, gridWidth,
                gridy_start, count);
        count++;
        addPaintActionButton(lineInputVerticalLineButton, gridWidth,
                gridy_start, count);
        count++;
        addPaintActionButton(lineInputSymmetricButton, gridWidth, gridy_start,
                count);
        count++;
        addPaintActionButton(lineInputMirrorButton, gridWidth, gridy_start,
                count);
        count++;
        addPaintActionButton(lineInputByValueButton, gridWidth, gridy_start,
                count);
    }

    private void addPaintActionButton(AbstractButton button, int gridWidth,
            int gridy, int count) {

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = count % gridWidth + 1;
        gridBagConstraints.gridy = gridy + count / gridWidth;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(button, gridBagConstraints);

    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(UIPanelSettingDB.getInstance()
                .getSelectedMode() == EditMode.INPUT);
    }

}
