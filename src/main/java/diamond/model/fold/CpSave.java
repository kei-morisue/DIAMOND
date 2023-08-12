/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.IOException;
import java.io.Serializable;

import diamond.model.fold.util.BytesUtil;

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
			dec = BytesUtil.convertFromBytes(cp);
			return (Cp) dec;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void save(
			Cp cp)
			throws IOException {
		this.cp = BytesUtil.convertToBytes(cp);
	}

}
