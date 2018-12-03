package diamond.doc;

import diamond.doc.exporter.Exporter;
import diamond.file.SavingAction;

public class SavingDoc implements SavingAction {

    private Exporter exporter;

    public SavingDoc(Exporter exporter) {
        this.exporter = exporter;
    }

    @Override
    public boolean save(String path) {
        boolean success = false;
        try {
            success = exporter.export(DocHolder.getInstance().getDoc(), path);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return success;
    }

}