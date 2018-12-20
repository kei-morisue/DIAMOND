/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2005-2009 Jun Mitani http://mitani.cs.tsukuba.ac.jp/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package diamond.view.paint.menubar.edit;

import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;
import diamond.view.MainFrame;

public class RepeatCopyDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JButton jButtonCancel = null;
    private JButton jButtonOK = null;
    private JCheckBox jCheckBoxFill = null;
    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private JLabel jLabel1 = null;
    private JLabel jLabel2 = null;
    private JLabel jLabel3 = null;
    private JLabel jLabel4 = null;
    private JTextField jTextFieldIntX = null;
    private JTextField jTextFieldIntY = null;
    private JTextField jTextFieldX = null;
    private JTextField jTextFieldY = null;
    private boolean m_bFillSheet;
    private double m_interX, m_interY;
    private int m_row, m_col;

    /**
     * @param owner
     */
    public RepeatCopyDialog(Frame owner) {
        super(owner);
        initialize();
    }

    /**
     * This method initializes jButtonCancel
     *
     * @return javax.swing.JButton
     */
    private JButton getJButtonCancel() {
        if (jButtonCancel == null) {
            jButtonCancel = new JButton();
            jButtonCancel.setBounds(new Rectangle(10, 185, 96, 21));
            jButtonCancel.setText("Cancel");
            jButtonCancel
                    .addActionListener(new java.awt.event.ActionListener() {

                        @Override
                        public void actionPerformed(
                                java.awt.event.ActionEvent e) {
                            setVisible(false);
                        }
                    });
        }
        return jButtonCancel;
    }

    /**
     * This method initializes jButtonOK
     *
     * @return javax.swing.JButton
     */
    private JButton getJButtonOK() {
        if (jButtonOK == null) {
            jButtonOK = new JButton();
            jButtonOK.setBounds(new Rectangle(10, 160, 96, 21));
            jButtonOK.setText("OK");
            jButtonOK.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        m_row = Integer.valueOf(jTextFieldX.getText());
                    } catch (Exception ex) {
                        m_row = 0;
                    }

                    try {
                        m_col = Integer.valueOf(jTextFieldY.getText());
                    } catch (Exception ex) {
                        m_col = 0;
                    }

                    try {
                        m_interX = Double.valueOf(jTextFieldIntX.getText());
                    } catch (Exception ex) {
                        m_interX = 0;
                    }

                    try {
                        m_interY = Double.valueOf(jTextFieldIntY.getText());
                    } catch (Exception ey) {
                        m_interY = 0;
                    }

                    m_bFillSheet = jCheckBoxFill.isSelected();

                    if (!m_bFillSheet && (m_row == 0 || m_col == 0)) {
                        JOptionPane.showMessageDialog(
                                MainFrame.getInstance(),
                                "Specify non-Zero value to Low and Col.",
                                "ArrayCopy",
                                JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        Doc document = DocHolder.getDoc();
                        CreasePattern creasePattern = document
                                .getCreasePattern();
                        document.pushUndoInfo();

                        Painter painter = new Painter();
                        if (m_bFillSheet) {
                            PaintContext context = PaintContext.getInstance();
                            painter.fillOut(
                                    context.getLines(), creasePattern,
                                    creasePattern.getPaperSize());
                        } else {
                            painter.copyWithTiling(
                                    m_row, m_col, m_interX, m_interX,
                                    creasePattern,
                                    creasePattern.getPaperSize());
                        }
                        //TODO make it local access
                        MainFrame.getInstance().repaint();
                        setVisible(false);
                    }
                }
            });
        }
        return jButtonOK;
    }

    /**
     * This method initializes jCheckBoxFill
     *
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getJCheckBoxFill() {
        if (jCheckBoxFill == null) {
            jCheckBoxFill = new JCheckBox();
            jCheckBoxFill.setBounds(new Rectangle(5, 5, 91, 22));
            jCheckBoxFill.setSelected(true);
            jCheckBoxFill.setText("Fill Sheet");
            jCheckBoxFill.addItemListener(new java.awt.event.ItemListener() {

                @Override
                public void itemStateChanged(java.awt.event.ItemEvent e) {
                    jTextFieldX.setEnabled(!jCheckBoxFill.isSelected());
                    jTextFieldY.setEnabled(!jCheckBoxFill.isSelected());
                    jLabel.setEnabled(!jCheckBoxFill.isSelected());
                    jLabel1.setEnabled(!jCheckBoxFill.isSelected());
                }
            });
        }
        return jCheckBoxFill;
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabel4 = new JLabel();
            jLabel4.setBounds(new Rectangle(5, 130, 26, 21));
            jLabel4.setText("Y");
            jLabel3 = new JLabel();
            jLabel3.setBounds(new Rectangle(5, 105, 26, 21));
            jLabel3.setText("X");
            jLabel2 = new JLabel();
            jLabel2.setBounds(new Rectangle(5, 80, 59, 21));
            jLabel2.setText("Interval");
            jLabel1 = new JLabel();
            jLabel1.setBounds(new Rectangle(5, 55, 51, 21));
            jLabel1.setText("Col");
            jLabel1.setEnabled(false);
            jLabel = new JLabel();
            jLabel.setBounds(new Rectangle(5, 30, 51, 21));
            jLabel.setText("Row");
            jLabel.setEnabled(false);
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJTextFieldX(), null);
            jContentPane.add(jLabel, null);
            jContentPane.add(jLabel1, null);
            jContentPane.add(getJTextFieldY(), null);
            jContentPane.add(getJCheckBoxFill(), null);
            jContentPane.add(jLabel2, null);
            jContentPane.add(jLabel3, null);
            jContentPane.add(jLabel4, null);
            jContentPane.add(getJTextFieldIntX(), null);
            jContentPane.add(getJTextFieldIntY(), null);
            jContentPane.add(getJButtonOK(), null);
            jContentPane.add(getJButtonCancel(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jTextFieldIntX
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextFieldIntX() {
        if (jTextFieldIntX == null) {
            jTextFieldIntX = new JTextField();
            jTextFieldIntX.setBounds(new Rectangle(35, 105, 66, 21));
            jTextFieldIntX.setHorizontalAlignment(SwingConstants.RIGHT);
        }
        return jTextFieldIntX;
    }

    /**
     * This method initializes jTextFieldIntY
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextFieldIntY() {
        if (jTextFieldIntY == null) {
            jTextFieldIntY = new JTextField();
            jTextFieldIntY.setBounds(new Rectangle(35, 130, 66, 21));
            jTextFieldIntY.setHorizontalAlignment(SwingConstants.RIGHT);
        }
        return jTextFieldIntY;
    }

    /**
     * This method initializes jTextFieldX
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextFieldX() {
        if (jTextFieldX == null) {
            jTextFieldX = new JTextField();
            jTextFieldX.setBounds(new Rectangle(60, 30, 41, 21));
            jTextFieldX.setHorizontalAlignment(SwingConstants.RIGHT);
            jTextFieldX.setEnabled(false);
        }
        return jTextFieldX;
    }

    /**
     * This method initializes jTextFieldY
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextFieldY() {
        if (jTextFieldY == null) {
            jTextFieldY = new JTextField();
            jTextFieldY.setBounds(new Rectangle(60, 55, 41, 21));
            jTextFieldY.setHorizontalAlignment(SwingConstants.RIGHT);
            jTextFieldY.setEnabled(false);
        }
        return jTextFieldY;
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(123, 249);
        this.setLocation(MainFrame.getInstance().getLocation().x + 200,
                MainFrame.getInstance().getLocation().y + 100);
        this.setTitle(ResourceHolder.getLabelString(
                StringID.Main.REPEAT_COPY_ID));
        this.setContentPane(getJContentPane());
    }
} //  @jve:decl-index=0:visual-constraint="10,10"
