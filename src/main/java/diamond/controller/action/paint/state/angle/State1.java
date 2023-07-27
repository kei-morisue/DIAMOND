/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.angle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

import diamond.controller.action.paint.state.FindingStateBase;
import diamond.controller.action.paint.state.PaintStateBase;
import diamond.model.Dir;
import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Crease;
import diamond.model.fold.Segment;
import diamond.model.fold.Vertex;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class State1 extends FindingStateBase {

	private Vertex v0;
	public static double DELTA = Math.PI / 8;
	private XY point;
	private XY extended;

	public State1(Vertex v0) {
		this.v0 = v0;
	}

	@Override
	protected PaintStateBase getNextState() {
		return new State0();
	}

	@Override
	protected PaintStateBase getPrevState() {
		return new State0();
	}

	@Override
	protected boolean undo(
			PaintScreen screen,
			XY p) {
		v0.isPicked = false;
		return true;
	}

	@Override
	protected void find(
			PaintScreen screen,
			XY p) {
		this.findCtrl(screen, p);
	}

	@Override
	protected void findCtrl(
			PaintScreen screen,
			XY p) {
		super.findCtrl(screen, p);
		XY p0 = v0.p;
		double angle = Math.round(p0.dir(p).angle() / DELTA) * DELTA;
		Dir dir0 = new Dir(angle);
		double s = dir0.dot(p0.dir(p));
		this.point = dir0.mul(s).ver(p0);
		extended = null;
		if (segment == null) {
			return;
		}
		extended = segment.foot(p0, dir0);

	}

	@Override
	protected void refresh(
			PaintScreen screen) {
		clearPicked();
		extended = null;
		point = null;
	}

	@Override
	protected boolean act(
			PaintScreen screen) {
		Cp cp = screen.getCp();
		if (segment == null) {
			Crease added
					= new Crease(v0, new Vertex(point), Segment.VALLEY);
			added.add(cp);
			return true;
		}
		Crease added = new Crease(v0, new Vertex(extended), Segment.VALLEY);
		added.add(cp);
		return true;
	}

	@Override
	protected boolean actCtrl(
			PaintScreen screen) {
		return act(screen);
	}

	@Override
	protected void drawState(
			Graphics2D g2d,
			PaintScreen screen) {
		if (point == null) {
			return;
		}
		g2d.setColor(Color.MAGENTA);
		g2d.setStroke(new BasicStroke(3.0f));
		if (extended != null) {
			Shape s = new Line2D.Double(v0.p.x, v0.p.y, extended.x, extended.y);
			g2d.draw(s);
			return;
		}
		Shape s = new Line2D.Double(v0.p.x, v0.p.y, point.x, point.y);
		g2d.draw(s);
	}

}
