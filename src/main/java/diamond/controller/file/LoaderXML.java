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
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;

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
            Palette palette = (Palette) decoder.readObject();
            for (Cp cp : palette.getCps()) {
                for (Face face : cp.getFaces()) {
                    for (HalfEdge he : face.getHalfEdges()) {
                        he.getV0().add(he);
                        for (HalfEdge h0 : face.getHalfEdges()) {
                            if (h0 == he) {
                                continue;
                            }
                            if (h0.getV0() == he.getV1()) {
                                he.connectTo(h0);
                            }
                        }
                    }
                    for (HalfEdge he : face.getUnsettledLines()) {
                        he.getV0().add(he);
                        he.connectTo(he.getPair());
                    }
                }
            }

            decoder.close();
            return palette;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}