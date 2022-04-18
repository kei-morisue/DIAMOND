/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.ui.button.AbstractIconButton;

/**
 * @author Kei Morisue
 *
 */
public class Tabs extends JPanel implements KeyListener {
    private ButtonGroup buttons = new ButtonGroup();
    private CardLayout card = new CardLayout();
    private JPanel panel = new JPanel();

    public Tabs(Context context) {
        panel.setFocusable(true);
        panel.addKeyListener(this);
        panel.setLayout(card);
        panel.add(new TabManageFaces(context, buttons));
        panel.add(new TabPaintSymbols(context, buttons));
        panel.add(new PaintPatternPanel(context, buttons));
        panel.add(new TabAlterLineType(context, buttons));
        add(panel);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!e.isControlDown()) {
            return;
        }
        if (e.getKeyCode() != KeyEvent.VK_DOWN
                &&
                e.getKeyCode() != KeyEvent.VK_LEFT
                &&
                e.getKeyCode() != KeyEvent.VK_RIGHT) {
            return;
        }
        JPanel tab = visiblePanel();
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            Component[] components = tab.getComponents();
            AbstractIconButton selected = null;
            for (Component comp : components) {
                AbstractIconButton button = (AbstractIconButton) comp;
                if (selected != null) {
                    button.doClick();
                    ;
                    return;
                }
                if (button.isSelected()) {
                    selected = button;
                }
            }
            ((AbstractIconButton) components[0]).setSelected(true);
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.card.next(panel);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.card.previous(panel);
        }
        AbstractIconButton button = (AbstractIconButton) visiblePanel()
                .getComponent(0);
        button.doClick();
    }

    private JPanel visiblePanel() {
        JPanel tab = null;
        for (Component comp : panel.getComponents()) {
            if (comp.isVisible() == true) {
                tab = (JPanel) comp;
            }
        }
        return tab;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
