package software.unf.dk.timetracker;

import android.util.Log;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Handles processing of Classification XML file(s)
 *
 * File syntax:
 * <classifications>
 *     <classification name="User facing name"/>
 * </classifications>
 */

class ClassificationIOHandler extends IOHandler {
    public ClassificationIOHandler(File file) {
        super(file);
    }
    /**
     * Parses the file the IOHandler handles and returns an ArrayList with all the actions inside it
     * @return An ArrayList of the Actions in the XML file
     */
    public ArrayList<Classification> parseClassifications() {
        ArrayList<Classification> classifications = new ArrayList<>();
        Log.e("Info", "File length is " + file.length());
        try {
            document = builder.parse(file);
            // get root XML element
            Element root = document.getDocumentElement();
            // Check proper root tag
            if ((!root.getTagName().equals("classifications")) || root.getNodeType() != Node.ELEMENT_NODE) {
                // TODO: actually handle syntax errors
                throw new IOException();
            }
            // Get action nodes
            NodeList classificationNodes = root.getElementsByTagName("classification");
            for (int i = 0; i < classificationNodes.getLength(); i++) {
                Node classificationNode = classificationNodes.item(i);
                // Check proper tag name
                if (classificationNode.getNodeType() != Node.ELEMENT_NODE || !(classificationNode.getNodeName().equals("classification") )) {
                    throw new SAXException();
                }
                // Cast to Element
                Element actionElement = (Element) classificationNode;
                // Get values
                String name = actionElement.getAttribute("name");
                Classification classification = new Classification(name);
                classifications.add(classification);
            }
        } catch (IOException e) {
            Log.e("Error", "File input/output error");
            e.printStackTrace();
        } catch (SAXException e) {
            Log.e("Error", "XML Parsing error");
        }
        return classifications;
    }

    /**
     * Saves the specified ArrayList of Actions in the IOHandler's file
     * @param classificationList ArrayList of Actions to be saved
     */
    public void writeClassifications(ArrayList<Classification> classificationList) {
        Document document = builder.newDocument();

        // Create root element
        Element root = document.createElement("classifications");
        document.appendChild(root);

        // Create Action elements
        for (int i = 0; i < classificationList.size(); i++) {
            Classification classification = classificationList.get(i);
            Element classificationElement = document.createElement("classification");

            // Set attributes
            Attr nameAttribute = document.createAttribute("name");
            nameAttribute.setValue(classification.getName());
            classificationElement.setAttributeNode(nameAttribute);

            // Add element to root
            root.appendChild(classificationElement);
        }

        // Write XML file
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (Exception e) {
            Log.e("Error", "Failed to write XML file");
            e.printStackTrace();
        }
    }
}
