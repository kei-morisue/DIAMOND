/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.file.action;

import java.awt.Component;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import diamond.view.screen.PreviewScreen;

public class PdfExportAction extends AbstractExportAction {

    public PdfExportAction(Component parent, PreviewScreen screen) {
        super(parent, screen);
    }

    @Override
    protected void export(String filePath) throws IOException {
        ImageExportAction imageExportAction = new ImageExportAction(parent,
                screen);
        imageExportAction.export(filePath);
        ;

        PDDocument document = new PDDocument();

        for (int i = 0; i < screen.maxPageNo() + 1; ++i) {
            PDPage page = new PDPage();
            document.addPage(page);
            String imagePath = imageExportAction.fileName(filePath, i)
                    + "." + imageExportAction.formatName();
            PDImageXObject image = PDImageXObject
                    .createFromFile(imagePath, document);

            PDPageContentStream contentStream = new PDPageContentStream(
                    document, page);
            contentStream.drawImage(image, 0, 0, image.getWidth(),
                    image.getHeight());

            contentStream.close();

            screen.nextPage(1);
        }

        document.save(filePath + "/pages.pdf");
        document.close();
    }

}
