/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Kei Morisue
 *
 */
public interface Parser {
    public void parse(NodeList list,
            DefaultMutableTreeNode parentNode);

    public void buildNodeChild(
            DefaultMutableTreeNode parentNode, Node child);

    public String getNodeName(Node child, NamedNodeMap attributes);
}
