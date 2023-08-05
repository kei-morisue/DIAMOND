/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * @author Kei Morisue
 *
 */
public class CpSave implements Serializable {
	private byte[] cp;

	public CpSave() {
	}

	public Cp restore() {
		Object dec;
		try {
			dec = convertFromBytes(cp);
			return (Cp) dec;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void save(
			Cp cp)
			throws IOException {
		this.cp = convertToBytes(cp);
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
}
