package diamond.paint.copypaste;

import javax.vecmath.Vector2d;

import diamond.paint.core.PaintContext;

class OriginHolder {

//--------------------------------------------------------------
	private static OriginHolder holder = null;
	
	public static OriginHolder getInstance(){
		if(holder == null){
			holder = new OriginHolder();
		}
		
		return holder;
	}
//--------------------------------------------------------------
	
	private Vector2d origin = null;

	private OriginHolder(){}
	
	public Vector2d getOrigin(PaintContext context){
		resetOrigin(context);
		
		return origin;
	}
	
	public void resetOrigin(PaintContext context){
    	if(origin == null){
    		if(context.getLineCount() > 0){
    			origin = context.getLine(0).p0;
    		}
		}	
	}
	
	public void setOrigin(Vector2d p){
		origin = p;
	}
	
}
