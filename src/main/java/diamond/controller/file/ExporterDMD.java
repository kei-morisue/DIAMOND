/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.imageio.IIOException;

import diamond.controller.Palette;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;

/**
 * @author Kei Morisue
 *
 */
public class ExporterDMD {

    public boolean export(Palette palette, String filePath)
            throws FileNotFoundException, IOException {

    	Vector<Cp> cps = palette.getCps();
        exportXml(cps,filePath+".dmd");
return true;
    }

    private void exportXml(Object o, String name) throws FileNotFoundException,IOException {
    	ObjectOutputStream ooStream =
                new ObjectOutputStream(
                        new FileOutputStream(name));
        ooStream.writeObject(o);
        ooStream.close();
    }
}