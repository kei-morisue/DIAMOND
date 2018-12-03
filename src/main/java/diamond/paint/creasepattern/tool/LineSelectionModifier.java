package diamond.paint.creasepattern.tool;

import java.util.Collection;

import diamond.value.OriLine;

public class LineSelectionModifier {
	public int countSelectedLines(Collection<OriLine> creasePattern) {
		int count = 0;
		for (OriLine l : creasePattern) {
			if (l.selected) {
				count++;
			}
		}
		return count;

	}

	/**
	 * reset selection mark of all lines in given collection.
	 * @param creasePattern
	 */
	public void resetSelectedOriLines(Collection<OriLine> creasePattern) {
		for (OriLine line : creasePattern) {
			line.selected = false;
		}
	}

	/**
	 * set  {@code true} to selection mark of all lines in given collection.
	 * @param creasePattern
	 */
	public void selectAllOriLines(Collection<OriLine> creasePattern) {
		for (OriLine l : creasePattern) {
			if (l.typeVal != OriLine.TYPE_CUT) {
				l.selected = true;
			}
		}
	}

}
