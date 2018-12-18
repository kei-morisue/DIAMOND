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

package diamond.view.paint;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import diamond.paint.core.PaintConfig;
import diamond.view.paint.uipanel.DisplayCeckBoxPanel;
import diamond.view.paint.uipanel.GridPanel;
import diamond.view.paint.uipanel.editmode.EditModeButtons;
import diamond.view.paint.uipanel.inputline.InputLineButtons;
import diamond.view.paint.uipanel.inputline.valuepanel.AngleValuePanel;
import diamond.view.paint.uipanel.inputline.valuepanel.LengthValuePanel;

public class PaintUI extends JPanel {

    public PaintUI(PaintScreen painterScreen) {
        setPreferredSize(new Dimension(200, 800));
        setLayout(new FlowLayout());

        add(new EditModeButtons());
        add(new InputLineButtons());
        add(new LengthValuePanel());
        add(new AngleValuePanel());
        add(new GridPanel());
        add(new DisplayCeckBoxPanel());

        PaintConfig.dispVertex = true;
        PaintConfig.bDoFullEstimation = true;
    }

}
