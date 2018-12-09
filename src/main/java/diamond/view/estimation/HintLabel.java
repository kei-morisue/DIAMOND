/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.estimation;

import javax.swing.JLabel;

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;

/**
 * @author long_
 *
 */
public class HintLabel extends JLabel {
    public HintLabel() {
        super(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.FoldedModel.DIRECTION_BASIC_ID));
    }
}
