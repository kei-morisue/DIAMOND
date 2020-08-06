/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d1.SegmentType;
import diamond.view.ui.panel.Util;

/**
 * @author Kei Morisue
 *
 */
public class ExporterXML implements Exporter {

    public boolean export(Diagram diagram, String filePath) {
        try {
            XMLEncoder enc = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath + ".dmd")));
            enc.setPersistenceDelegate(SegmentType.class,
                    new EnumPersistenceDelegate());
            enc.writeObject(diagram);
            enc.close();
        } catch (FileNotFoundException e) {
            Util.warn("no_file");
            return false;
        } catch (StackOverflowError e) {
            Util.warn("memory_out");
            return false;
        }

        return true;
    }
}
