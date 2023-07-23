/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;

import diamond.controller.Context;
import diamond.controller.action.SymbolHalfEdgeOffsetAction;
import diamond.controller.action.SymbolHalfEdgePaintAction;
import diamond.controller.action.SymbolHalfEdgeScaleAction;
import diamond.controller.action.SymbolRepeatPaintAction;
import diamond.controller.action.SymbolVertexPaintAction;
import diamond.model.symbol.AuxSegment;
import diamond.model.symbol.Landmark;
import diamond.model.symbol.arrow.ArrowFlip;
import diamond.model.symbol.arrow.ArrowFoldUnfold;
import diamond.model.symbol.arrow.ArrowMountain;
import diamond.model.symbol.arrow.ArrowRotate;
import diamond.model.symbol.arrow.ArrowSink;
import diamond.model.symbol.arrow.ArrowValley;
import diamond.view.ui.button.PaintActionButton;

/**
 * @author Kei Morisue
 *
 */
public class TabPaintSymbols extends AbstractTab {
	public TabPaintSymbols(Context context, ButtonGroup buttonGroup) {
		setLayout(new GridLayout(6, 2));
		PaintActionButton valley = new PaintActionButton(context, new SymbolHalfEdgePaintAction(ArrowValley.class));
		PaintActionButton mountain = new PaintActionButton(context, new SymbolHalfEdgePaintAction(ArrowMountain.class));
		PaintActionButton foldUnfold = new PaintActionButton(context,
				new SymbolHalfEdgePaintAction(ArrowFoldUnfold.class));
		PaintActionButton sink = new PaintActionButton(context, new SymbolHalfEdgePaintAction(ArrowSink.class));
		PaintActionButton flip = new PaintActionButton(context, new SymbolHalfEdgePaintAction(ArrowFlip.class));
		PaintActionButton rotate = new PaintActionButton(context, new SymbolHalfEdgePaintAction(ArrowRotate.class));
		PaintActionButton repeat = new PaintActionButton(context, new SymbolRepeatPaintAction());
		PaintActionButton landmark = new PaintActionButton(context, new SymbolVertexPaintAction(Landmark.class));
		PaintActionButton aux = new PaintActionButton(context, new SymbolHalfEdgePaintAction(AuxSegment.class));
		PaintActionButton scale = new PaintActionButton(context, new SymbolHalfEdgeScaleAction());
		PaintActionButton offset = new PaintActionButton(context, new SymbolHalfEdgeOffsetAction());

		addButton(buttonGroup, valley, "valley_arrow");
		addButton(buttonGroup, mountain, "mountain_arrow");
		addButton(buttonGroup, foldUnfold, "fold_unfold_arrow");
		addButton(buttonGroup, sink, "sink_arrow");
		addButton(buttonGroup, flip, "flip_arrow");
		addButton(buttonGroup, rotate, "rotate_arrow");
		addButton(buttonGroup, repeat, "repeat_arrow");
		addButton(buttonGroup, landmark, "landmark");
		addButton(buttonGroup, aux, "aux_");
		addButton(buttonGroup, scale, "scale");
		addButton(buttonGroup, offset, "offset");

	}
}
