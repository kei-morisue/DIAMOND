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
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import diamond.Initials;
import diamond.view.paint.PaintContext;
import diamond.view.paint.screen.PaintScreen;
import diamond.view.resource.ImageIconLoader;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

public class PaintFrame extends JFrame {

    public PaintFrame() {
        setBackground(Color.black);

        setSize(Initials.MAIN_FRAME_WIDTH,
                Initials.MAIN_FRAME_HEIGHT);
        setIconImage(
                ImageIconLoader.loadAsIcon("icon/diamond.gif", getClass())
                        .getImage());
        setTitle(ResourceHolder.getLabelString(
                LABEL.DEFAULT_FILE_NAME));
        {

            JMenuBar bar = new JMenuBar();
            bar.add(new JMenu("File"));
            add(bar, BorderLayout.NORTH);

        }

        add(new PaintScreen(new PaintContext()), BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void setTitle(String fileName) {
        super.setTitle(ResourceHolder.getLabelString(LABEL.MAIN_FRAME_TITLE));
    }

}
