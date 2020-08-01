/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.option;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.screen.style.PageStyle;

/**
 * @author Kei Morisue
 *
 */
public class PanelPage extends JPanel {
    private static final String rows = Labels.get("page_rows");
    private static final String cols = Labels.get("page_cols");
    private static final String bg = Labels.get("page_bg_color");

    private Context context;
    private JTextField colfField = new JTextField(
            String.valueOf(PageStyle.DIAGRAM_COL));
    private JTextField rowField = new JTextField(
            String.valueOf(PageStyle.DIAGRAM_ROW));
    private JButton bgButton = new JButton();

    public PanelPage(Context context) {
        this.context = context;
        bgButton.setBackground(PageStyle.bg);
        bgButton.addActionListener(new PageColorAction(bgButton));
        colfField.addActionListener(new PageGridAction(true));
        rowField.addActionListener(new PageGridAction(false));
        add(colfField);
        add(rowField);
        add(bgButton);
    }

    private class PageGridAction implements ActionListener {
        private boolean isCol;

        public PageGridAction(boolean isCol) {
            this.isCol = isCol;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isCol) {
                String c = colfField.getText();
                if (c != null) {
                    PageStyle.DIAGRAM_COL = Integer.parseInt(c);
                }
            } else {
                String r = colfField.getText();
                if (r != null) {
                    PageStyle.DIAGRAM_ROW = Integer.parseInt(r);
                }
            }
        }

    }

    private class PageColorAction implements ActionListener {
        private AbstractButton parent;

        public PageColorAction(AbstractButton parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = null;
            Color initialColor = PageStyle.bg;
            JColorChooser chooser = new JColorChooser(initialColor);
            JDialog dialog = JColorChooser.createDialog(parent, title, true,
                    chooser, new OkListner(chooser),
                    new CancelListner());
            dialog.setVisible(true);
        }

        private class OkListner implements ActionListener {
            JColorChooser chooser;

            public OkListner(JColorChooser chooser) {
                this.chooser = chooser;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Color chosen = chooser.getColor();
                if (chosen == null) {
                    return;
                }
                PageStyle.bg = chosen;
                parent.setBackground(chosen);
                //TODO
                //                context.repaint();
            }
        }

        private class CancelListner implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }

    }
}
