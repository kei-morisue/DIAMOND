/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import diamond.model.Dir;
import diamond.model.Tuple;
import diamond.model.XY;
import diamond.model.fold.Edge.Assign;
import diamond.model.line.Line;

/**
 * @author Kei Morisue
 *
 */
public class Fold extends Flat {
	private List<XY> vfs = new ArrayList<XY>();
	private List<Boolean> flips = new ArrayList<Boolean>();

	public Fold(ArrayList<Line> l, ArrayList<Edge.Assign> a) {
		super(l, a);
		buildFolded(getFaces().get(1));
	}

	public Fold(String path) {
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
		buildFolded(getFaces().get(1));
	}

	public void clearFolded() {
		vertices.forEach(v -> {
			vfs.add(null);
		});
		faces.forEach(faces -> {
			flips.add(null);
		});
	}

	private void setF(Vertex v, XY vf) {
		int i = vertices.indexOf(v);
		vfs.set(i, vf);
	}

	private void setFlip(Face f, boolean isflip) {
		int i = faces.indexOf(f);
		flips.set(i, isflip);
	}

	public XY getF(Vertex v) {
		int i = vertices.indexOf(v);
		return vfs.get(i);
	}

	private void buildFolded(Face baseFace) {
		HashMap<Tuple<Vertex>, Edge> ve = FoldBuilder.getVE(edges);
		HashMap<Tuple<Vertex>, Face> vf = FoldBuilder.getVF(faces);
		clearFolded();
		HashSet<Face> queued = new HashSet<Face>();
		Vertex p0 = baseFace.getVertices().get(0);
		Vertex p1 = baseFace.getVertices().get(1);
		setF(p0, p0.getV());
		setF(p1, p1.getV());
		ArrayDeque<Face> queueFace = new ArrayDeque<>(Arrays.asList(baseFace));
		queued.add(baseFace);
		ArrayDeque<Vertex> queueV0 = new ArrayDeque<Vertex>(Arrays.asList(p0));
		ArrayDeque<Vertex> queueV1 = new ArrayDeque<Vertex>(Arrays.asList(p1));
		ArrayDeque<Boolean> queueFlip = new ArrayDeque<Boolean>(Arrays.asList(true));
		while (queueFace.size() > 0) {
			Face face = queueFace.poll();
			Vertex v0 = queueV0.poll();
			Vertex v1 = queueV1.poll();
			Boolean flip = queueFlip.poll();
			setFlip(face, !flip);
			ArrayList<Vertex> vs = face.getVertices();
			Dir x = v0.getV().dir(v1.getV()).unit();
			Dir y = x.perp();
			Dir xf = getF(v0).dir(getF(v1)).unit();
			Dir yf = xf.perp();
			Vertex vi = vs.get(vs.size() - 1);
			for (Vertex vj : vs) {
				if (getF(vj) == null) {
					Dir dir = v0.getV().dir(vj.getV());
					Dir dx = xf.mul(dir.dot(x));
					Dir dy = yf.mul(dir.dot(y) * (flip ? 1 : -1));
					setF(vj, dx.add(dy).ver(getF(v0)));
				}
				// adding next queue
				Tuple<Vertex> key = new Tuple<Vertex>(vi, vj);
				Face nextFace = vf.get(key);
				boolean nextFlip = (ve.get(key).isFlipping()) ? !flip : flip;
				if (nextFace != null && !queued.contains(nextFace)) {
					queueFace.add(nextFace);
					queueV0.add(vj);
					queueV1.add(vi);
					queueFlip.add(nextFlip);
					queued.add(nextFace);
				}
				vi = vj;
			}
		}

	}

	public List<XY> getVfs() {
		return vfs;
	}

	public List<Boolean> getFlips() {
		return flips;
	}

	@Override
	protected int getMaxFraction() {
		return 300;
	}

}
