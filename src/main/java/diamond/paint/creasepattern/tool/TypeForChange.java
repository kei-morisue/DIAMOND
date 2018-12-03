package diamond.paint.creasepattern.tool;

// TODO move to view layer, or integrate with LineTypes

public enum TypeForChange {
	AUX("Aux"),
	CUT("Cut"), DELETE("Del"), EMPTY("-"), FLIP("Flip"), 
	RIDGE("M"), VALLEY("V");
	
	private String shortName;
	
	private TypeForChange(String shortName) {
		this.shortName = shortName;
	}
	
	@Override
	public String toString(){
		return shortName;
	}
}