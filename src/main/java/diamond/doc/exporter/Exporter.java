package diamond.doc.exporter;

import diamond.doc.Doc;

public interface Exporter {
	public boolean export(Doc doc, String filepath) throws Exception;
}
