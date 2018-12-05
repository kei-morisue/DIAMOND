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

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import diamond.Config;
import diamond.doc.Doc;
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

    private MainFrame() {
        addWindowListener(this);
        setSize(Config.INITIAL_MAIN_FRAME_WIDTH,
                Config.INITIAL_MAIN_FRAME_HEIGHT);
        setIconImage(new ImageResourceLoader()
                .loadAsIcon("icon/diamond.gif", getClass())
                .getImage());
        updateTitleText();

        setLayout(new BorderLayout());
        PainterScreen painterScreen = new PainterScreen();
        add(painterScreen, BorderLayout.CENTER);
        add(new UIPanel(painterScreen), BorderLayout.WEST);
        add(new HintLabel(), BorderLayout.SOUTH);
        setJMenuBar(MenuBar.getInstance());

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void updateTitleText() {
        String fileName = buildFileName(DocHolder.getDoc());
        setTitle(fileName + " - "
                + ResourceHolder.getString(ResourceKey.LABEL,
                        StringID.Main.TITLE_ID));
    }

    private static String buildFileName(Doc doc) {
        if ((doc.getDataFilePath()).equals("")) {
            return ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.Default.FILE_NAME_ID);
        } else {
            return doc.getDataFileName();
        }
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

}
