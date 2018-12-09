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

package diamond.view.estimation;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.viewsetting.estimation.RenderFrameSettingDB;

public class EstimationResultFrame extends JFrame
        implements Observer {
    private static EstimationResultFrame instance = null;

    public static EstimationResultFrame getInstance() {
        if (instance == null) {
            instance = new EstimationResultFrame();
        }
        return instance;
    }

    private RenderFrameSettingDB setting = RenderFrameSettingDB.getInstance();

    private EstimationResultFrame() {
        setting.addObserver(this);
        setTitle(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.FoldedModel.TITLE_ID));
        setBounds(0, 0, 800, 600);
        add(new RenderPanel());
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (setting.isFrameVisible()) {
            setVisible(true);
        }
    }
}
