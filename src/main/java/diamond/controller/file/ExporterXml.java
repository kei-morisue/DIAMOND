/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import diamond.view.ui.panel.Util;

/**
 * @author Kei Morisue
 *
 */
public class ExporterXml {

    public boolean export(Object diagram, String filePath) {
        if (filePath == null) {
            return false;
        }
        try {
            FileOutputStream out = new FileOutputStream(filePath);
            BufferedOutputStream buffer = new BufferedOutputStream(out);
            XMLEncoder enc = new XMLEncoder(buffer);
            //            enc.setPersistenceDelegate(SegmentType.class,
            //                    new EnumPersistenceDelegate());
            enc.writeObject(diagram);
            enc.close();
            buffer.close();
            out.close();
        } catch (FileNotFoundException e) {
            Util.warn("no_file");
            return false;
        } catch (StackOverflowError e) {
            Util.warn("memory_out");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
