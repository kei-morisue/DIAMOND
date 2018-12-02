package diamond.doc.loader;

import diamond.doc.Doc;
import diamond.file.FileVersionError;

public interface Loader {
    public Doc load(String filePath) throws FileVersionError;
}
