/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.frame;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

import diamond.controller.Context;
import diamond.controller.file.SerializerXML;
import diamond.model.cyborg.diagram.Diagram;
import diamond.view.resource.string.Labels;
import diamond.view.ui.tree.Tree;

/**
 * @author Kei Morisue
 *
 */
public class TreeFrame extends JFrame {
    public TreeFrame(Context context) {
        setTitle(Labels.get("frame_tree"));

        Diagram diagram = context.getDiagram();
        Tree tree = new Tree(
                new SerializerXML().serialize(diagram),
                new DefaultMutableTreeNode());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setView(tree);
        add(scrollPane);
        setLocationRelativeTo(null);
        setSize(new Dimension(800, 800));
        setVisible(true);
    }

}
