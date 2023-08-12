/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * @author Kei Morisue
 *
 */
public class BytesUtil {
	public static byte[] convertToBytes(
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

	public static Object convertFromBytes(
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
