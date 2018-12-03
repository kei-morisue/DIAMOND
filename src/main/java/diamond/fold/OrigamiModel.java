package diamond.fold;

import java.util.ArrayList;
import java.util.List;

import diamond.value.OriLine;


/**
 * Entity for folding-estimation
 * @author Koji
 *
 */
public class OrigamiModel {
	
	private List<OriLine> crossLines = new ArrayList<OriLine>();
	private List<OriEdge> edges = new ArrayList<OriEdge>();
	private List<OriFace> faces = new ArrayList<OriFace>();

	private boolean folded = false;

	private boolean hasModel = false;

	//private FoldedModelInfo foldedModelInfo = new FoldedModelInfo();
	
	private double paperSize = -1;

	private boolean probablyFoldable = false;

	private List<OriFace> sortedFaces = new ArrayList<OriFace>();
	
	private List<OriVertex> vertices = new ArrayList<OriVertex>();
	

	//=============================================================
	// Constructors
	//=============================================================
			
	@SuppressWarnings("unused")
	private OrigamiModel() {}

	public OrigamiModel(double paperSize) {
		setPaperSize(paperSize);
	}
	
	//=============================================================
	// Getter/Setter
	//=============================================================

	public List<OriLine> getCrossLines() {
		return crossLines;
	}

	public List<OriEdge> getEdges() {
		return edges;
	}

	
	
	public List<OriFace> getFaces() {
		return faces;
	}

	public double getPaperSize() {
		return paperSize;
	}

	public List<OriFace> getSortedFaces() {
		return sortedFaces;
	}
	
	
	
	public List<OriVertex> getVertices() {
		return vertices;
	}
	/**
	 * @return hasModel
	 */
	public boolean hasModel() {
		return hasModel;
	}


	public boolean isFolded() {
		return folded;
	}

	/**
	 * @return probablyFoldable
	 */
	public boolean isProbablyFoldable() {
		return probablyFoldable;
	}

	public void setCrossLines(List<OriLine> crossLines) {
		this.crossLines = crossLines;
	}

	/**
	 * @param edges edgesを登録する
	 */
	public void setEdges(List<OriEdge> edges) {
		this.edges = edges;
	}

	/**
	 * @param faces facesを登録する
	 */
	public void setFaces(List<OriFace> faces) {
		this.faces = faces;
	}

	public void setFolded(boolean folded) {
		this.folded = folded;
	}

//	public FoldedModelInfo getFoldedModelInfo() {
//		return foldedModelInfo;
//	}
//
//	public void setFoldedModelInfo(FoldedModelInfo foldedModelInfo) {
//		this.foldedModelInfo = foldedModelInfo;
//	}

	/**
	 * @param hasModel hasModelを登録する
	 */
	public void setHasModel(boolean hasModel) {
		this.hasModel = hasModel;
	}

	public void setPaperSize(double paperSize) {
		this.paperSize = paperSize;
	}

	/**
	 * @param probablyFoldable probablyFoldableを登録する
	 */
	public void setProbablyFoldable(boolean probablyFoldable) {
		this.probablyFoldable = probablyFoldable;
	}

	public void setSortedFaces(List<OriFace> sortedFaces) {
		this.sortedFaces = sortedFaces;
	}

	/**
	 * @param vertices verticesを登録する
	 */
	public void setVertices(List<OriVertex> vertices) {
		this.vertices = vertices;
	}
	
	
	
	
	
	
	
}
