package diamond.fold;

import java.util.ArrayList;
import java.util.List;

public class FoldedModelInfo {
	private int currentORmatIndex = 0;
	private int overlapRelation[][] = null;
	private List<int[][]> overlapRelations = new ArrayList<int[][]>();

	BoundBox boundBox = new BoundBox(null, null);
	
	
	/**
	 * @return boundBox
	 */
	public BoundBox getBoundBox() {
		return boundBox;
	}

	public int getCurrentORmatIndex() {
		return currentORmatIndex;
	}

	public List<int[][]> getFoldableOverlapRelations() {
		return overlapRelations;
	}

	public int getFoldablePatternCount() {
		return overlapRelations.size();
	}

	public int[][] getOverlapRelation() {
		return overlapRelation;
	}

	/**
	 * @param boundBox boundBoxを登録する
	 */
	public void setBoundBox(BoundBox boundBox) {
		this.boundBox = boundBox;
	}
	

	public void setCurrentORmatIndex(int currentORmatIndex) {
		this.currentORmatIndex = currentORmatIndex;
	}

	public void setFoldableOverlapRelations(List<int[][]> foldableOverlapRelations) {
		this.overlapRelations = foldableOverlapRelations;
	}

	public void setNextORMat() {
		if (currentORmatIndex < overlapRelations.size() - 1) {
			currentORmatIndex++;
			Folder.matrixCopy(overlapRelations.get(currentORmatIndex), overlapRelation);
		}
	}

	public void setOverlapRelation(int[][] overlapRelation) {
		this.overlapRelation = overlapRelation;
	}

	public void setPrevORMat() {
		if (currentORmatIndex > 0) {
			currentORmatIndex--;
			Folder.matrixCopy(overlapRelations.get(currentORmatIndex), overlapRelation);
		}

	}
	
	
}
