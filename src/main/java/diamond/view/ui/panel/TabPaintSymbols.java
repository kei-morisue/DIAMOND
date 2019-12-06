/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;

import diamond.controller.Context;
import diamond.controller.action.LazyPaintAction;
import diamond.controller.action.SymbolHalfEdgePaintAction;
import diamond.controller.action.SymbolVertexPaintAction;
import diamond.model.symbol.Landmark;
import diamond.model.symbol.arrow.ArrowMountain;
import diamond.model.symbol.arrow.ArrowValley;
import diamond.view.ui.button.PaintActionButton;

/**
 * @author Kei Morisue
 *
 */
public class TabPaintSymbols extends AbstractTab {
    public TabPaintSymbols(Context context, ButtonGroup buttonGroup) {
        setLayout(new GridLayout(3, 4));
        PaintActionButton valley = new PaintActionButton(context,
                new SymbolHalfEdgePaintAction(ArrowValley.class));
        PaintActionButton mountain = new PaintActionButton(context,
                new SymbolHalfEdgePaintAction(ArrowMountain.class));
        PaintActionButton foldUnfold = new PaintActionButton(context,
                new LazyPaintAction());//TODO
        PaintActionButton sink = new PaintActionButton(context,
                new LazyPaintAction());//TODO
        PaintActionButton flip = new PaintActionButton(context,
                new LazyPaintAction());//TODO
        PaintActionButton rotate = new PaintActionButton(context,
                new LazyPaintAction());//TODO
        PaintActionButton repeat = new PaintActionButton(context,
                new LazyPaintAction());//TODO
        PaintActionButton landmark = new PaintActionButton(context,
                new SymbolVertexPaintAction(Landmark.class));

        addButton(buttonGroup, valley, "valley_arrow");
        addButton(buttonGroup, mountain, "mountain_arrow");
        addButton(buttonGroup, foldUnfold, "fold_unfold_arrow");
        addButton(buttonGroup, sink, "sink_arrow");
        addButton(buttonGroup, flip, "flip_arrow");
        addButton(buttonGroup, rotate, "rotate_arrow");
        addButton(buttonGroup, repeat, "repeat_arrow");
        addButton(buttonGroup, landmark, "landmark");

    }
}
