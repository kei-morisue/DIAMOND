/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.file;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import diamond.Config;
import diamond.controller.file.ExporterXml;
import diamond.controller.file.LoaderXml;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.Rational;
import diamond.model.math.field.Real;
import diamond.model.math.field.Silver;

/**
 * @author Kei Morisue
 *
 */
public class XmlTest {
    private ExporterXml out = new ExporterXml();
    private Silver x = new Silver(
            new Rational(1, 2),
            new Rational(5, 2));
    private Silver y = new Silver(
            new Rational(3, 4),
            new Rational(7, 2));

    @Test
    public void VerTest() {
        String path = "silver.dmd";
        LoaderXml<ArrayList<Ver<Silver>>> in = new LoaderXml<ArrayList<Ver<Silver>>>();
        Ver<Silver> v = new Ver<Silver>(x, y);
        ArrayList<Ver<Silver>> w = new ArrayList<Ver<Silver>>();
        w.add(v);
        w.add(v);
        out.export(w, path);
        w = in.load(path);
        assertFalse(v.toString() == w.get(0).toString());
        assertFalse(v.toString() == w.get(1).toString());
    }

    @Test
    public void ContextTest() {
        Diagram<Real> dgm0 = new Diagram<Real>(new Real(Config.size));
        LoaderXml<Diagram<Real>> in = new LoaderXml<Diagram<Real>>();
        String path = "dgm.dmd";
        out.export(dgm0, path);
        Diagram<Real> dgm1 = in.load(path);
        assertFalse(dgm0.toString() == dgm1.toString());
    }

}
