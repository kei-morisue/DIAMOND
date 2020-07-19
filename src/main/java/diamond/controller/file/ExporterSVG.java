/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.awt.Dimension;
import java.io.File;
import java.util.Vector;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.dom.util.DOMUtilities;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.Document;

import diamond.controller.Palette;
import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.draw.FoldedScreenDrawer;
import diamond.view.ui.screen.draw.StringDrawer;

/**
 * @author Kei Morisue
 *
 */
public class ExporterSVG implements Exporter {
    @Override
    public boolean export(Palette palette, String directory) {
        Vector<Cp> cps = palette.getCps();
        for (Cp cp : cps) {
            Document doc = GenericDOMImplementation
                    .getDOMImplementation().createDocument(
                            "http://www.w3.org/2000/svg",
                            "svg",
                            null);

            SVGGraphics2D g2d = new SVGGraphics2D(doc);
            g2d.setSVGCanvasSize(new Dimension(744, 1052));

            FoldedScreenDrawer.draw(g2d, cp);
            int i = cps.indexOf(cp) + 1;
            StringDrawer.drawDiagramStepNo(
                    g2d,
                    i,
                    100,
                    100);
            try {
                g2d.stream(directory + "/" + String.format("%03d", i) + ".svg");
            } catch (SVGGraphics2DIOException e) {
                e.printStackTrace();
            }
            //            Element svgRoot = doc.getDocumentElement();
            //            Element tagG = g2d.getDOMFactory().createElementNS(
            //                    SVGGraphics2D.SVG_NAMESPACE_URI,
            //                    SVGGraphics2D.SVG_G_TAG);
            //            g2d.getRoot(tagG);
            //            Node node = doc.importNode(tagG, true);
            //            svgRoot.appendChild(node);
        }

        return true;
    }

    private void write(String filepath, Document doc) {
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance()
                    .newTransformer();
        } catch (TransformerConfigurationException e1) {
            e1.printStackTrace();
        } catch (TransformerFactoryConfigurationError e1) {
            e1.printStackTrace();
        }
        Result output = new StreamResult(new File(filepath));
        System.out
                .println(DOMUtilities.getXML(doc.getFirstChild()));
        Source input = new DOMSource(doc);
        try {
            transformer.transform(input, output);
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
