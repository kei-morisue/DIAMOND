/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.tree;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Kei Morisue
 *
 */
public class Tree extends JTree {
    private DocumentBuilderFactory factory = DocumentBuilderFactory
            .newInstance();
    private Node document = null;

    public Tree(InputStream serialized, DefaultMutableTreeNode root) {
        super(root);
        buildDocument(serialized);
        parse(document.getFirstChild().getChildNodes(), root);
        for (int i = 0; i < getRowCount(); ++i) {
            expandRow(i);
        }
    }

    private void buildDocument(InputStream serialized) {
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(serialized);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void parse(NodeList list, DefaultMutableTreeNode parentNode) {
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

    private void buildNodeChild(
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

    private String getNodeName(Node child, NamedNodeMap attributes) {
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
