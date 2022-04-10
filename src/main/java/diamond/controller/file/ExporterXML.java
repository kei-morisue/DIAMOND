/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Vector;

import diamond.controller.Palette;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;

/**
 * @author Kei Morisue
 *
 */
public class ExporterXML {

    public boolean export(Palette palette, String filePath)
            throws FileNotFoundException {
        Vector<Cp> cps = palette.getCps();
        for (int i = 0; i < cps.size(); ++i) {
            exportXml(
                    cps.get(i),
                    filePath
                            + "/"
                            + String.format("%03d", i)
                            + ".stp");
        }
        return true;
    }

    private void exportXml(Object o, String name) throws FileNotFoundException {
        XMLEncoder enc = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(name)));
        enc.setPersistenceDelegate(EdgeType.class,
                new EnumPersistenceDelegate());
        enc.writeObject(o);
        enc.close();
    }
}