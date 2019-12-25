/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import diamond.controller.Palette;
import diamond.model.cyborg.EdgeType;

/**
 * @author Kei Morisue
 *
 */
public class ExporterXML implements Exporter {

    public boolean export(Palette palette, String filePath) {
        try {
            XMLEncoder enc = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath)));
            enc.setPersistenceDelegate(EdgeType.class,
                    new EnumPersistenceDelegate());
            enc.writeObject(palette);
            enc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}