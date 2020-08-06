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
public class XmlParser implements Parser {
    @Override
    public void parse(NodeList list,
            DefaultMutableTreeNode parentNode) {
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            switch (child.getNodeType()) {
            case Node.ELEMENT_NODE:
                buildNodeChild(parentNode, child);
                break;
            default:
                break;
            }
        }
    }

    @Override
    public void buildNodeChild(
            DefaultMutableTreeNode parentNode, Node child) {
        NamedNodeMap attributes = child.getAttributes();
        NodeList childNodes = child.getChildNodes();
        if (attributes.getNamedItem("method") != null) {
            parse(childNodes, parentNode);
            return;
        }
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(
                getNodeName(child, attributes));
        parentNode.add(treeNode);
        parse(childNodes, treeNode);
        return;
    }

    @Override
    public String getNodeName(Node child, NamedNodeMap attributes) {
        String name = null;
        for (int i = 0; i < attributes.getLength(); ++i) {
            Node item = attributes.item(i);
            String nodeValue = item.getNodeValue();
            if (item.getNodeName().equals("class")) {
                continue;
            }
            if (name == null) {
                name = nodeValue;
            } else {
                name += nodeValue;
            }
        }
        if (name == null) {
            return child.getFirstChild().getNodeValue();
        }
        return name;
    }
}
