/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import diamond.model.Tuple;
import diamond.model.fold.Edge.Assign;
import diamond.model.line.Line;

/**
 * @author Kei Morisue
 *
 */
public class Cell extends Flat {
	protected List<ArrayList<Edge>> se = new ArrayList<>();
	protected List<ArrayList<Face>> cf = new ArrayList<ArrayList<Face>>();
	protected List<ArrayList<Face>> fc = new ArrayList<ArrayList<Face>>();

	public Cell(Fold fold) {
		buildSE(fold);
		buildCF();
		buildFC(fold);
		// stub
		Face cell = faces.get(3);
		cell.picked = true;
		ArrayList<Face> fs = getCF(cell);
		fs.forEach(f -> f.picked = true);
	}

	private void buildSE(Fold fold) {
		ArrayList<Line> l = new ArrayList<Line>();
		ArrayList<Assign> a = new ArrayList<Assign>();
		ArrayList<Edge> foldEdges = fold.getEdges();
		foldEdges.forEach(e -> {
			l.add(new Line(fold.getF(e.getV0()), fold.getF(e.getV1())));
			a.add(e.getA());
		});
		HashMap<Tuple<Vertex>, ArrayList<Line>> ve = this.build(l, a);
		ve.forEach((key, lines) -> {
			ArrayList<Edge> es = new ArrayList<Edge>();
			se.add(es);
			lines.forEach(line -> {
				es.add(foldEdges.get(l.indexOf(line)));
			});
		});
	}

	private void setCF(Face cell, ArrayList<Face> fs) {
		cf.set(faces.indexOf(cell), fs);
	}

	public ArrayList<Face> getCF(Face cell) {
		return cf.get(faces.indexOf(cell));
	}

	private void buildCF() {
		faces.forEach(c -> cf.add(null));
		HashMap<Tuple<Vertex>, Face> sCmap = CellBuilder.getSCmap(faces);
		HashMap<Tuple<Vertex>, ArrayList<Face>> sfMap = CellBuilder.getSFMap(se, edges);
		HashSet<Face> queued = new HashSet<Face>();
		ArrayDeque<Face> queue = new ArrayDeque<Face>();
		queue1stCell(sfMap, queued, queue);
		while (queue.size() > 0) {
			Face ci = queue.poll();
			ArrayList<Vertex> points = ci.getVertices();
			Vertex v1 = points.get(points.size() - 1);
			for (Vertex v2 : points) {
				Face nextCell = sCmap.get(new Tuple<Vertex>(v1, v2));
				if (nextCell != null && !queued.contains(nextCell)) {
					queue.push(nextCell);
					queued.add(nextCell);
					HashSet<Face> fs = new HashSet<Face>(getCF(ci));
					Tuple<Vertex> k = new Tuple<Vertex>(v1, v2);
					for (Face f : sfMap.get(k)) {
						boolean removed = fs.remove(f);
						if (!removed) {
							fs.add(f);
						}
					}
					setCF(nextCell, new ArrayList<Face>(fs));
				}
				v1 = v2;
			}
		}

	}

	private void buildFC(Fold fold) {
		ArrayList<Face> foldFaces = fold.getFaces();
		FaceIndexComparator fic = this.new FaceIndexComparator();
		foldFaces.forEach(f -> fc.add(new ArrayList<Face>()));
		for (int ci = 0; ci < cf.size(); ci++) {
			ArrayList<Face> fs = cf.get(ci);
			fs.sort(fic);
			for (Face f : fs) {
				Face cell = faces.get(ci);
				fc.get(foldFaces.indexOf(f)).add(cell);
			}
		}

	}

	private void queue1stCell(HashMap<Tuple<Vertex>, ArrayList<Face>> sfMap, HashSet<Face> queued,
			ArrayDeque<Face> queue) {
		for (int i = 0; i < edges.size(); i++) {
			Edge seg = edges.get(i);
			if (seg.getF1() == null) {
				Face ci = seg.getF0();
				Vertex v0 = seg.getV0();
				Vertex v1 = seg.getV1();
				Tuple<Vertex> key = new Tuple<Vertex>(v0, v1);
				setCF(ci, sfMap.get(key));
				queue.push(ci);
				queued.add(ci);
				break;
			}
		}
	}

	public class FaceIndexComparator implements Comparator<Face> {
		@Override
		public int compare(Face f1, Face f2) {
			return faces.indexOf(f1) - faces.indexOf(f2);
		}
	}

	public List<ArrayList<Edge>> getSe() {
		return se;
	}

	public List<ArrayList<Face>> getCf() {
		return cf;
	}

	public List<ArrayList<Face>> getFc() {
		return fc;
	}

	@Override
	protected int getMaxFraction() {
		return 1000;
	}

}
