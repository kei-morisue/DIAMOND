package diamond.paint.byvalue;

import java.util.Observable;

public class ValueDB extends Observable{

	private static ValueDB instance = null;
	public static ValueDB getInstance(){
		if(instance == null){
			instance = new ValueDB();
		}
		
		return instance;
	}
	
	private double angle = 0;
	
	private double length = 0;
	
	private ValueDB(){}
	
	
	/**
	 * 
	 * @return angle [degree]
	 */
	public double getAngle() {
		return angle;
	}

	public double getLength() {
		return length;
	}

	public void set(double length, double angle){
		this.length = length;
		this.angle = angle;

		this.setChanged();
	}

	public void setAngle(double angle) {
		this.angle = angle;

		this.setChanged();
	}

	public void setLength(double length) {
		this.length = length;

		this.setChanged();
	}

	/**
	 * @return full-path class name
	 */
	@Override
	public String toString() {
		return this.getClass().getName();
	}
	
	
	
}
