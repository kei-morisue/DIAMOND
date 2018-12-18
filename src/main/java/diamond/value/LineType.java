package diamond.value;

public enum LineType {
	CUT("CUT", 1), NONE("NONE", 0), RIDGE("RIDGE", 3), VALLEY("VALLEY", 2);
	
	private String name;
	private int val;
	
	private LineType(String name, int val){
		this.name = name;
		this.val = val;
	}
	
	public int toInt(){
		return val;
	}
	
	@Override
    public String toString(){
		return name;
	}
}
