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
        NodeList childNodes = document.getFirstChild().getChildNodes();
        new XmlParser().parse(childNodes, root);
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

}
