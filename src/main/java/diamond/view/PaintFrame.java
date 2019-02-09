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

import javax.swing.JFrame;

import diamond.Initials;
import diamond.controller.paint.PaintContext;
import diamond.view.paint.screen.PaintScreen;
import diamond.view.paint.ui.panelUI;
import diamond.view.resource.ImageIconLoader;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

public class PaintFrame extends JFrame {

    public PaintFrame() {
        setSize(Initials.MAIN_FRAME_WIDTH,
                Initials.MAIN_FRAME_HEIGHT);
        setIconImage(
                new ImageIconLoader().loadAsIcon("icon/diamond.gif")
                        .getImage());
        setTitle(ResourceHolder.getLabelString(
                LABEL.TITLE));

        PaintContext paintContext = new PaintContext();
        PaintScreen paintScreen = new PaintScreen(paintContext);

        panelUI ui = new panelUI(paintScreen, paintContext);
        add(paintScreen, BorderLayout.CENTER);
        add(ui, BorderLayout.WEST);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void setTitle(String fileName) {
        super.setTitle(ResourceHolder.getLabelString(LABEL.TITLE));
    }

}
