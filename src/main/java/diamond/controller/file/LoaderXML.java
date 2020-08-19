/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import diamond.model.cyborg.diagram.Diagram;
import diamond.view.ui.panel.Util;

/**
 * @author Kei Morisue
 *
 */
public class LoaderXML implements Loader {

    @Override
    public Diagram load(String filepath) {
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(filepath)));
            Diagram diagram = (Diagram) decoder.readObject();
            decoder.close();
            return diagram;
        } catch (FileNotFoundException e) {
            Util.warn("no_file");
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
