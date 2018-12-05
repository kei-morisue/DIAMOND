/**
 * ORIPA - Origami Pattern Editor
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

package diamond.view.main;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import diamond.Config;
import diamond.doc.DocHolder;
import diamond.file.ImageResourceLoader;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.menubar.MenuBar;
import diamond.view.main.menubar.file.Exit;

public class MainFrame extends JFrame implements WindowListener {

    private static MainFrame instance = null;

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private PainterScreen cpScreen = new PainterScreen();
    private PainterScreen secondScreen = new PainterScreen();
    private UIPanel uiPanel;

    public MainFrame() {
        addWindowListener(this);
        uiPanel = new UIPanel(cpScreen);
    }

    public void initialize() {
        setSize(Config.INITIAL_MAIN_FRAME_WIDTH,
                Config.INITIAL_MAIN_FRAME_HEIGHT);
        setIconImage(new ImageResourceLoader()
                .loadAsIcon("icon/diamond.gif", getClass())
                .getImage());
        updateTitleText();
        setJMenuBar(MenuBar.getInstance());
        layoutComponents();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void layoutComponents() {
        //TODO

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        layout(gbl, uiPanel, 0, 0, 1, 1);
        layout(gbl, cpScreen, 1, 0, 3, 3);
        layout(gbl, secondScreen, 4, 0, 3, 3);
        layout(gbl, new HintLabel(), 0, 1, 1, 1);
    }

    void layout(GridBagLayout gbl, Component c, int x, int y, int w, int h) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbl.setConstraints(c, gbc);
        add(c);
    }

    public void updateTitleText() {
        String fileName = DocHolder.getDoc().buildFileName();
        setTitle(fileName + " - "
                + ResourceHolder.getString(ResourceKey.LABEL,
                        StringID.Main.TITLE_ID));
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        Exit.getInstance().doClick();
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
    }

    public PainterScreen getCpScreen() {
        return this.cpScreen;
    }

    public UIPanel getUiPanel() {
        return this.uiPanel;
    }

}
