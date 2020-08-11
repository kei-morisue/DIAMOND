/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.option;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import diamond.controller.Context;
import diamond.controller.action.ChooseColor;
import diamond.model.Getter;
import diamond.model.Setter;
import diamond.model.cyborg.style.StylePage;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class PanelPage extends JPanel {
    private StylePage stylePage;
    private JTextField colField = new JTextField();
    private JTextField rowField = new JTextField();
    private JButton bgButton = new JButton();

    public PanelPage(Context context) {
        this.stylePage = context.getDiagram().getStylePage();
        buildBgButton();
        buildColField();
        buildRowField();
        setLayout(new GridLayout(3, 2));
        add(new JLabel(Labels.get("page_rows")));
        add(rowField);
        add(new JLabel(Labels.get("page_cols")));
        add(colField);
        add(new JLabel(Labels.get("page_bg_color")));
        add(bgButton);
    }

    private void buildBgButton() {
        bgButton.setBackground(stylePage.getBg());
        bgButton.addActionListener(
                new ChooseColor(
                        bgButton,
                        stylePage.new BgSetter()));
    }

    private void buildColField() {
        colField.setText(String.valueOf(stylePage.getCol()));
        colField.addActionListener(
                new Action(
                        new ColGetter(),
                        stylePage.new ColSetter()));
        colField.setHorizontalAlignment(JTextField.RIGHT);
    }

    private void buildRowField() {
        rowField.setText(String.valueOf(stylePage.getRow()));
        rowField.addActionListener(
                new Action(
                        new RowGetter(),
                        stylePage.new RowSetter()));
        rowField.setHorizontalAlignment(JTextField.RIGHT);
    }

    private class Action implements ActionListener {
        private Getter<String> getter;
        private Setter<Byte> setter;

        public Action(Getter<String> getter, Setter<Byte> setter) {
            this.getter = getter;
            this.setter = setter;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = getter.get();
            if (s != null) {
                setter.set(Byte.parseByte(s));
            }
        }

    }

    private class RowGetter implements Getter<String> {
        @Override
        public String get() {
            return rowField.getText();
        }
    }

    private class ColGetter implements Getter<String> {
        @Override
        public String get() {
            return colField.getText();
        }
    }
}
