package diamond.doc;

import java.util.Deque;
import java.util.LinkedList;

public class UndoManager<Backup> {

	private Backup cache;
	private boolean changed = false;
	
	private int max = 1000;

	private Deque<Backup> undoStack = new LinkedList<>();

	public UndoManager() {
		
	}
	
	public UndoManager(int max){
		this.max = max;
	}

	public boolean canUndo(){
		return ! undoStack.isEmpty();
	}

	public void clearChanged(){
		changed = false;
	}

	public Backup getCache(){
		return cache;
	}
	
	public boolean isChanged(){
		return changed;
	}
	
	public Backup peek(){
		if (undoStack.isEmpty()) {
			return null;
		}

		return undoStack.peek();
	}
	
	public Backup pop() {
		if (undoStack.isEmpty()) {
			return null;
		}
		else {
			changed = true;
		}

		return undoStack.pop();
	}
	
	public void push(Backup uinfo){
		undoStack.push(uinfo);
		
		if(undoStack.size() > max){
			undoStack.removeFirst();
		}
		
		changed = true;
	}
	
	public void pushCachedInfo(){
		this.push(cache);
	}
	
	public void setCache(Backup info){
		cache = info;
	}
}
