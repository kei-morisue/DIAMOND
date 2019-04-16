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

package diamond.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import diamond.Initials;
import diamond.controller.paint.ModelContext;
import diamond.controller.paint.PaintContext;
import diamond.controller.paint.Palette;
import diamond.view.resource.IconSetter;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.screen.ModelScreen;
import diamond.view.screen.PaintScreen;
import diamond.view.ui.button.DiagramDestroyButton;
import diamond.view.ui.button.DiagramInsertButton;
import diamond.view.ui.button.DiagramSwitchButton;
import diamond.view.ui.menu.MenuBar;
import diamond.view.ui.panel.UiPanel;

public class MainFrame extends JFrame {
    private Palette palette = new Palette();
    private PaintContext paintContext = new PaintContext(palette);
    private PaintScreen paintScreen = new PaintScreen(paintContext);
    private ModelContext modelContext = new ModelContext(palette);
    private ModelScreen modelScreen = new ModelScreen(
            modelContext);

    public MainFrame() {
        setSize(Initials.MAIN_FRAME_WIDTH,
                Initials.MAIN_FRAME_HEIGHT);
        IconSetter.set(this, "diamond.gif");
        setTitle(ResourceHolder.getLabelString(
                LABEL.TITLE));
        add(buildCenterPanel(), BorderLayout.CENTER);

        add(new UiPanel(paintContext), BorderLayout.WEST);
        setJMenuBar(new MenuBar(paintContext));

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void setTitle(String fileName) {
        super.setTitle(ResourceHolder.getLabelString(LABEL.TITLE));
    }

    private JPanel buildCenterPanel() {
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        JPanel panel = buildScreens();
        center.add(panel, BorderLayout.CENTER);
        center.add(
                new DiagramSwitchButton(DiagramSwitchButton.NEXT,
                        modelContext),
                BorderLayout.EAST);
        center.add(
                new DiagramSwitchButton(DiagramSwitchButton.PREV,
                        modelContext),
                BorderLayout.WEST);
        center.add(
                new DiagramDestroyButton(paintContext),
                BorderLayout.SOUTH);
        center.add(
                new DiagramInsertButton(paintContext),
                BorderLayout.NORTH);
        return center;
    }

    private JPanel buildScreens() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(paintScreen);
        panel.add(modelScreen);
        return panel;
    }
}
