/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.uipanel.inputline;

import java.awt.GridBagConstraints;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.bind.ButtonFactory;
import diamond.bind.PaintActionButtonFactory;
import diamond.file.ImageResourceLoader;
import diamond.resource.StringID;
import diamond.view.main.uipanel.LayoutUtil;

/**
 * @author long_
 *
 */
public class InputLineCommandButtons {
    private int paintActionButtonCount = 0;
    private ButtonFactory buttonFactory = new PaintActionButtonFactory();

    private JRadioButton lineInputAngleBisectorButton;
    private JRadioButton lineInputByValueButton;
    private JRadioButton lineInputDirectVButton;
    private JRadioButton lineInputMirrorButton;
    private JRadioButton lineInputOnVButton;
    private JRadioButton lineInputPBisectorButton;
    private JRadioButton lineInputSymmetricButton;
    private JRadioButton lineInputTriangleSplitButton;
    private JRadioButton lineInputVerticalLineButton;
    private JPanel basePanel;

    public InputLineCommandButtons(JPanel __basePanel) {
        basePanel = __basePanel;
        JLabel label1 = new JLabel("Command (1...9)");
        label1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        basePanel.add(label1, LayoutUtil.buildGBC(1, 8, 4));

        createButtons();

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
        lineInputDirectVButton.setSelected(true);

        setIcons();
        setMnemonics();
        addPaintActionButtons(4, 9);

        lineInputDirectVButton.doClick();

    }

    /**
     *
     */
    private void createButtons() {
        lineInputAngleBisectorButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class, StringID.BISECTOR_ID);

        lineInputByValueButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class, StringID.BY_VALUE_ID);
        lineInputDirectVButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class, StringID.DIRECT_V_ID);
        lineInputMirrorButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class, StringID.MIRROR_ID);

        lineInputOnVButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class, StringID.ON_V_ID);
        lineInputPBisectorButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class,
                        StringID.PERPENDICULAR_BISECTOR_ID);
        lineInputSymmetricButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class, StringID.SYMMETRIC_ID);
        lineInputTriangleSplitButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class, StringID.TRIANGLE_ID);
        lineInputVerticalLineButton = (JRadioButton) buttonFactory
                .create(
                        basePanel, JRadioButton.class, StringID.VERTICAL_ID);

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

        paintActionButtonCount = 0;
        // put operation buttons in order
        addPaintActionButton(lineInputDirectVButton, gridWidth, gridy_start);
        addPaintActionButton(lineInputOnVButton, gridWidth, gridy_start);
        addPaintActionButton(lineInputPBisectorButton, gridWidth, gridy_start);
        addPaintActionButton(lineInputAngleBisectorButton, gridWidth,
                gridy_start);
        addPaintActionButton(lineInputTriangleSplitButton, gridWidth,
                gridy_start);
        addPaintActionButton(lineInputVerticalLineButton, gridWidth,
                gridy_start);
        addPaintActionButton(lineInputSymmetricButton, gridWidth, gridy_start);
        addPaintActionButton(lineInputMirrorButton, gridWidth, gridy_start);
        addPaintActionButton(lineInputByValueButton, gridWidth, gridy_start);
    }

    private void addPaintActionButton(AbstractButton button, int gridWidth,
            int gridy) {

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = paintActionButtonCount % gridWidth + 1;
        gridBagConstraints.gridy = gridy + paintActionButtonCount / gridWidth;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basePanel.add(button, gridBagConstraints);

        paintActionButtonCount++;

    }
}
