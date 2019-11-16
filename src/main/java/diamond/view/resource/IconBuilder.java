/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.resource;

import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @author Kei Morisue
 *
 */
public class IconBuilder {
    private static ImageIcon loadAsIcon(String name) {
        return loadAsIcon(name, IconBuilder.class);
    }

    private static ImageIcon loadAsIcon(String name, Class<?> c) {
        ClassLoader classLoader = c.getClassLoader();
        URL url = classLoader.getResource("icon/" + name);
        ImageIcon icon = new ImageIcon(url);
        return icon;

    }

    public static void set(AbstractButton button, String name) {
        button.setIcon(loadAsIcon(name));
    }

    public static void setSelected(AbstractButton button, String name) {
        button.setSelectedIcon(loadAsIcon(name));
    }

    public static void set(JFrame frame, String name) {
        frame.setIconImage(loadAsIcon(name).getImage());
    }
}
