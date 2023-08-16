/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.io;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import diamond.model.fold.Diagram;
import diamond.view.ui.panel.PreviewBase;

/**
 * @author Kei Morisue
 *
 */
public class ExportImageAction extends ExportActionBase {
	private PreviewBase preview;
	private static final String FORMAT = "png";

	public ExportImageAction(
			Diagram diagram, Component parent, PreviewBase preview) {
		super(diagram, parent);
		this.preview = preview;
	}

	@Override
	protected void export(
			String filePath)
			throws IOException {
		for (int i = 0; i <= preview.maxPageIdx(); ++i) {
			BufferedImage image = getImage();
			String pathname = fileName(filePath, i);
			File file = new File(pathname + FORMAT);
			ImageIO.write(image, FORMAT, file);
			preview.nextPage(1);
		}
	}

	public String fileName(
			String filePath,
			int i) {
		String pathname = filePath + "/page_"
				+ String.format("%03d", i + 1) + ".";
		return pathname;
	}

	private BufferedImage getImage() {
		int w = preview.getWidth();
		int h = preview.getHeight();
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		preview.paintComponents(g2);
		g2.dispose();
		return image;
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
