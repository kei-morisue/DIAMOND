/** DIAMOND - Origami Diagram Editor*/

package diamond.controller.file;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import diamond.model.geom.element.LineType;

public class ExporterXML implements Exporter {

    public boolean export(DataSet data, String filePath) {
        try {
            XMLEncoder enc = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath + ".dmd")));
            enc.setPersistenceDelegate(LineType.class,
                    new EnumPersistenceDelegate());
            enc.writeObject(data);
            enc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
