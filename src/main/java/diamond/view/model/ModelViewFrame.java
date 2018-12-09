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

package diamond.view.model;

import javax.swing.JFrame;

import diamond.Config;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.view.model.menu.MenuBar;

/**
 * A frame to show a transparent folded model.
 * @author Koji
 *
 */
public class ModelViewFrame extends JFrame {
    private static ModelViewFrame instance = null;

    public static ModelViewFrame getInstance() {
        if (instance == null) {
            instance = new ModelViewFrame();
        }
        return instance;
    }

    private ModelViewFrame() {

        setTitle(ResourceHolder
                .getString(ResourceKey.LABEL, "ExpectedFoldedOrigami"));
        ModelViewScreen screen = new ModelViewScreen();
        add(new RenderPanel(screen));
        setJMenuBar(new MenuBar(screen));

        setSize(Config.INITIAL_MODEL_FRAME_WIDTH,
                Config.INITIAL_MODEL_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
