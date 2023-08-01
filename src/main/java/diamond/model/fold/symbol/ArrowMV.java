/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold.symbol;

import java.awt.Graphics2D;
import java.util.List;

import diamond.model.Dir;
import diamond.model.XY;
import diamond.model.fold.Face;
import diamond.model.fold.Segment;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public final class ArrowMV extends SymbolBase {
	private Segment segment;
	private boolean isMountain = false;
	private boolean isBackward = false;
	private boolean isClockwise = false;
	private double scale0 = 1.0;
	private double scale1 = 1.0;

	public ArrowMV(Segment segment) {
		super();
		this.segment = segment;
	}

	@Override
	public Face getLayer(
			List<Face> faces) {
		return segment.getTopFace(faces);

	}

	@Override
	public void accept(
			DrawerBase drawer,
			Graphics2D g2d,
			double scale) {
		drawer.drawBody(g2d, this, scale);
		drawer.drawHead(g2d, this, scale, isMountain);
	}

	public XY[] getTailHead(
			DrawerBase drawer,
			double screenScale) {
		XY v0 = drawer.getXY(segment.getV0());
		XY v1 = drawer.getXY(segment.getV1());
		XY m = v0.mid(v1);
		Dir n = v0.dir(v1).perp();
		double scale = drawer.getArrowBodyScale(screenScale);
		XY vp = n.mul(0.5 * scale0 * scale).ver(m);
		XY vm = n.mul(-0.5 * scale1 * scale).ver(m);
		return isBackward ? new XY[] { vm, vp } : new XY[] { vp, vm };
	}

	public XY getControlPoint(
			XY tail,
			XY head) {
		Dir dc = tail.dir(head).mul(-0.25);
		Dir nc = dc.perp();
		return isClockwise ? dc.add(nc).ver(tail) : dc.sub(nc).ver(tail);
	}

	@Override
	public void flip() {
		isBackward = !isBackward;
		if (isBackward) {
			isClockwise = !isClockwise;
			if (isClockwise) {
				isMountain = !isMountain;
			}
		}
	}

}
