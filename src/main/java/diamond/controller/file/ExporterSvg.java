/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.awt.Dimension;
import java.util.LinkedList;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.Document;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.diagram.step.Step;
import diamond.view.ui.screen.ScreenStep;

/**
 * @author Kei Morisue
 *
 */
public class ExporterSvg implements Exporter {
    private static short w = 744;//TODO
    private static short h = 1052;//TODO
    private ScreenStep screen;

    public ExporterSvg(ScreenStep screen) {
        this.screen = screen;
    }

    @Override
    public boolean export(Diagram diagram, String filepath) {
        LinkedList<Step> steps = diagram.getSteps();
        for (Step step : steps) {
            Document doc = GenericDOMImplementation
                    .getDOMImplementation().createDocument(
                            "http://www.w3.org/2000/svg",
                            "svg",
                            null);
            SVGGraphics2D g2d = new SVGGraphics2D(doc);
            g2d.setSVGCanvasSize(new Dimension(w, h));
            //            step.draw(g2d, screen);
            try {
                g2d.stream(filepath + "/"
                        + String.format("%03d", steps.indexOf(step) + 1)
                        + ".svg");
            } catch (SVGGraphics2DIOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

}
