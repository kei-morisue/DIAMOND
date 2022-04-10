/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.stream.Stream;

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
        Palette palette = new Palette();
        Vector<Cp> cps = palette.getCps();
        try {
            Stream<Path> files = Files.list(Paths.get(filepath));
            files.forEach(p -> add(cps, p));
            files.close();
        } catch (IOException e) {
            return null;
        }
        cps.remove(0);
        connectEdges(cps);

        return palette;
    }

    private void connectEdges(Vector<Cp> cps) {
        for (Cp cp : cps) {
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
    }

    private void add(Vector<Cp> cps, Path path) {
        System.out.println("Loading: " + path);
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(path.toString())));
            Object object = decoder.readObject();
            cps.add((Cp) object);
            decoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}