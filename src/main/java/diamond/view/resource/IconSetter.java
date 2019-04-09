/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.resource;

import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @author long_
 *
 */
public class IconSetter {
    private static ImageIcon loadAsIcon(String name) {
        return loadAsIcon(name, IconSetter.class);
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
