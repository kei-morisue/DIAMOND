/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2005-2009 Jun Mitani http://mitani.cs.tsukuba.ac.jp/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package diamond.doc.exporter;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import diamond.doc.Doc;
import diamond.paint.core.PaintContext;
import diamond.view.MainFrame;

public class ExporterPNG implements Exporter {

    @Override
    public boolean export(Doc doc, String filepath) throws IOException {
        Component originalComponent = MainFrame.getInstance();
        //TODO Extract original frame dependencies: "MainFrame"
        Image cpImage = PaintContext.getPainterScreen().getCreasePatternImage();
        BufferedImage image = new BufferedImage(
                cpImage.getWidth(originalComponent),
                cpImage.getHeight(originalComponent),
                BufferedImage.TYPE_INT_RGB);

        image.getGraphics().drawImage(cpImage, 0, 0,
                originalComponent);

        File file = new File(filepath);
        ImageIO.write(image,
                filepath.substring(filepath.lastIndexOf(".") + 1),
                file);
        return true;
    }
}
