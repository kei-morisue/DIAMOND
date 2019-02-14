/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author long_
 *
 */
public class LoaderXML implements Loader {

    @Override
    public DataSet load(String filepath) {
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(filepath)));
            DataSet data = (DataSet) decoder.readObject();
            decoder.close();
            return data;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}
