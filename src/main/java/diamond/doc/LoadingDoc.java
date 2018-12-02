package diamond.doc;

import diamond.doc.loader.Loader;
import diamond.file.FileVersionError;

public class LoadingDoc implements diamond.file.LoadingAction{

    private Loader loader;

    public LoadingDoc(Loader loader) {
        this.loader = loader;

    }

    @Override
    public boolean load(String path) throws FileVersionError {
        boolean success = false;
        Doc doc = null;

        doc = loader.load(path);
        if (doc != null) {
            DocHolder.getInstance().setDoc(doc);
            if(path != ""){
                DocHolder.getInstance().getDoc().setDataFilePath(path);
            }
        }
        success = (doc != null);

        return success;
    }

}
