package diamond.doc;

import diamond.resource.Constants;

public class DocHolder {
    private static Doc doc = new Doc(Constants.DEFAULT_PAPER_SIZE);

    private DocHolder() {
    }

    public static Doc getDoc() {
        return doc;
    }

    public static void setDoc(Doc __doc) {
        doc = __doc;
    }
}
