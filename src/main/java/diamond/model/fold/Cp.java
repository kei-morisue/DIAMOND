/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import diamond.model.XY;
import diamond.model.fold.Edge.Assign;
import diamond.model.line.Line;

/**
 * @author Kei Morisue
 *
 */
public class Cp extends Flat {
	private List<Boolean> flips = new ArrayList<Boolean>();
	private Face baseFace;
	private AffineTransform screenTransform;

	public Cp(ArrayList<Line> l, ArrayList<Edge.Assign> a) {
		super(l, a);
		buildFolded(getFaces().get(1));
	}

	public Cp(String path) {
		ArrayList<Line> lines = new ArrayList<Line>();
		ArrayList<Assign> assigns = new ArrayList<Assign>();
		Assign[] as = { Assign.B, Assign.M, Assign.V, Assign.F };
		try {
			String str;
			BufferedReader bfReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")));

			while ((str = bfReader.readLine()) != null) {
				String[] split = str.split(" ");
				if (split.length < 5) {
					continue;
				}
				Assign a = as[Integer.parseInt(split[0]) - 1];
				double x1 = Double.parseDouble(split[1]);
				double y1 = Double.parseDouble(split[2]);
				double x2 = Double.parseDouble(split[3]);
				double y2 = Double.parseDouble(split[4]);
				lines.add(new Line(new XY(x1, y1), new XY(x2, y2)));
				assigns.add(a);
			}
			bfReader.close();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		build(lines, assigns);
		this.baseFace = getFaces().get(1);
		buildFolded(baseFace);
	}

	public void clearFolded() {
		vertices.forEach(v -> {
			v.f = v.v;
		});
		faces.forEach(faces -> {
			flips.add(false);
		});
	}

	private void buildFolded(Face baseFace) {
		clearFolded();
	}

	public List<Boolean> getFlips() {
		return flips;
	}

	@Override
	protected int getMaxFraction() {
		return 300;
	}

}
