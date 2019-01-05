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

package diamond.view.check;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.JFrame;

import diamond.fold.OrigamiModel;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey.LABEL;
import diamond.value.OriLine;

public class FoldabilityCheckFrame extends JFrame {

    FoldabilityScreen screen = new FoldabilityScreen();

    public FoldabilityCheckFrame() {
        super(ResourceHolder.getLabelString(LABEL.CHECK_WINDOW_TITLE));
        setBounds(0, 0, 800, 800);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(screen, BorderLayout.CENTER);

    }

    public void setModel(OrigamiModel origamiModel,
            Collection<OriLine> creasePattern) {
        screen.showModel(
                origamiModel, creasePattern);
    }

}
