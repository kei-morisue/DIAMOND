/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import diamond.controller.Palette;

/**
 * @author Kei Morisue
 *
 */
public class LoaderXML implements Loader {

    @Override
    public Palette load(String filepath) {
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(filepath)));
            Palette data = (Palette) decoder.readObject();
            decoder.close();
            return data;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}