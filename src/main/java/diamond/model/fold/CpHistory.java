/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * @author Kei Morisue
 *
 */
public class CpHistory {
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	byte[] current;
	Deque<byte[]> future = new ArrayDeque<>();

	Deque<byte[]> history = new ArrayDeque<>();
	final int UNDO_TOTAL_MAX = 1000;
	int undoTotal = 50; // Number of times you can undo up to how many times ago
						// is increased automatically up to UNDO_TOTAL_MAX if
						// enough ram is available

	public void addPropertyChangeListener(
			PropertyChangeListener propertyChangeListener) {
		this.pcs.addPropertyChangeListener(propertyChangeListener);
	}

	public boolean canRedo() {
		return !future.isEmpty();
	}

	public boolean canUndo() {
		return !history.isEmpty();
	}

	public void record(
			CpSave s0) {
		if (current != null)
			history.addFirst(current);
		updateUndoTotal();
		try {
			current = convertToBytes(s0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Future becomes irrelevant
		future.clear();

		while (history.size() > undoTotal) {
			history.removeLast();
		}
		this.pcs.firePropertyChange(null, null, null);
	}

	public CpSave redo() {
		if (future.isEmpty()) {
			return getCurrent();
		}

		history.addFirst(current);
		current = future.removeFirst();
		this.pcs.firePropertyChange(null, null, null);
		return getCurrent();
	}

	public void reset() {
		history.clear();
		future.clear();
		current = null;
		this.pcs.firePropertyChange(null, null, null);
	}

	public CpSave undo() {
		if (history.isEmpty()) {
			return getCurrent();
		}
		future.addFirst(current);
		current = history.removeFirst();
		this.pcs.firePropertyChange(null, null, null);
		return getCurrent();
	}

	private Object convertFromBytes(
			byte[] bytes)
			throws IOException,
			ClassNotFoundException {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				InflaterInputStream decomp = new InflaterInputStream(bis);
				ObjectInputStream in = new ObjectInputStream(decomp)) {
			return in.readObject();
		}
	}

	private byte[] convertToBytes(
			Object object)
			throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				DeflaterOutputStream comp = new DeflaterOutputStream(bos);
				ObjectOutputStream out = new ObjectOutputStream(comp)) {
			out.writeObject(object);
			out.close();
			return bos.toByteArray();
		}
	}

	private CpSave getCurrent() {
		if (current == null) {
			return new CpSave();
		}

		try {
			return (CpSave) convertFromBytes(current);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new CpSave();
		}
	}

	private void updateUndoTotal() {
		long entrySize = 0;
		if (current != null) {
			entrySize = current.length;
		}
		entrySize = Math.max(entrySize, 100000);
		long freeMemory = Runtime.getRuntime().freeMemory();
		if (freeMemory < entrySize * 30) {
			undoTotal = (int) (undoTotal * 0.9) + 2; // never goes below 20
		} else if (undoTotal < UNDO_TOTAL_MAX && freeMemory > entrySize * 80) {
			undoTotal += 2;
		}
	}
}
