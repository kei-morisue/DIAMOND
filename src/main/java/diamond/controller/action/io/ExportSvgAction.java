/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.io;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.Document;

import diamond.model.fold.Diagram;
import diamond.view.draw.ModelDrawer;
import diamond.view.draw.StringDrawer;

/**
 * @author Kei Morisue
 *
 */
public class ExportSvgAction extends ExportActionBase {
	private Diagram diagram;

	public ExportSvgAction(Diagram diagram, Component parent) {
		super(diagram, parent);
	}

	@Override
	protected void export(
			String path)
			throws IOException {
		for (int i = 0; i < diagram.size(); i++) {
			Document doc = GenericDOMImplementation
					.getDOMImplementation().createDocument(
							"http://www.w3.org/2000/svg",
							"svg",
							null);

			SVGGraphics2D g2d = new SVGGraphics2D(doc);
			g2d.setSVGCanvasSize(new Dimension(744, 1052));

			new ModelDrawer().draw(g2d, diagram.getCp(i));
			StringDrawer.drawPreviewStepNo(
					g2d,
					i + 1,
					100,
					100);
			try {
				g2d.stream(path + "/" + String.format("%03d", i) + ".svg");
			} catch (SVGGraphics2DIOException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	protected int getSelectionMode() {
		return JFileChooser.DIRECTORIES_ONLY;
	}

	@Override
	protected void setFileFilter(
			JFileChooser chooser) {

	}

}
