/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.file;

import static org.junit.Assert.*;

import org.junit.Test;

import diamond.controller.file.ExporterXml;
import diamond.controller.file.LoaderXml;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.Rational;
import diamond.model.math.field.Silver;

/**
 * @author Kei Morisue
 *
 */
public class XmlTest {
    private ExporterXml out = new ExporterXml();
    private LoaderXml<Ver<Silver>> in = new LoaderXml<Ver<Silver>>();
    private Silver x = new Silver(
            new Rational(1, 2),
            new Rational(5, 2));
    private Silver y = new Silver(
            new Rational(3, 4),
            new Rational(7, 2));

    @Test
    public void VerTest() {
        Ver<Silver> v = new Ver<Silver>(x, y);
        out.export(v, "silver");
        Ver<Silver> w = in.load("silver.dmd");
        assertFalse(v.toString() == w.toString());
    }

}
