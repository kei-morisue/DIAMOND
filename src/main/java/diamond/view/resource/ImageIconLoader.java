/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.resource;

import java.net.URL;

import javax.swing.ImageIcon;

/**
 * @author long_
 *
 */
public class ImageIconLoader {
    public ImageIcon loadAsIcon(String name) {
        return loadAsIcon(name, getClass());
    }

    public ImageIcon loadAsIcon(String name, Class<?> c) {
        ClassLoader classLoader = c.getClassLoader();
        URL url = classLoader.getResource(name);
        ImageIcon icon = new ImageIcon(url);

        return icon;

    }
}
