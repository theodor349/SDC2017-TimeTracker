package software.unf.dk.timetracker;

import android.util.Log;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Handles processing of Classification XML file(s)
 *
 * File syntax:
 * <classifications>
 *     <classification id="0" name="User facing name" visible="(true|false)"/>
 * </classifications>
 */

class ClassificationIOHandler extends IOHandler {
    private final String DOCUMENT_HEADER = "<?xml version=\"1.0\"?>\n<classifications>\n<classification name=\"Default\" id=\"0\" visible=\"true\"/>\n</classifications>";
    public ClassificationIOHandler(File file) {
        super(file);
        try {
            // Create file if it doesn't exist
            if (file.createNewFile() || file.length() == 0) {
                PrintWriter writer = new PrintWriter(file, "UTF-8");
                writer.println(DOCUMENT_HEADER);
                writer.close();
            }
            // Initialise parser objects
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            // Create test string
            ByteArrayInputStream input = new ByteArrayInputStream(DOCUMENT_HEADER.getBytes("UTF-8"));

            // Parse file
            document = builder.parse(file);
            // document = builder.parse(file);
        } catch (ParserConfigurationException e) {
            // TODO: Actually handle exceptions
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Error", "File input/output error");
            e.printStackTrace();
        } catch (SAXException e) {
            Log.e("Error", "XML parsing error");
            e.printStackTrace();
        }
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
                int id;
                try {
                    id = Integer.parseInt(actionElement.getAttribute("id"));
                } catch (NumberFormatException e) {
                    id = Classification.getUniqueId();
                }
                boolean visible;
                if (actionElement.getAttribute("visible").equals("true")) {
                    visible = true;
                } else {
                    visible = false;
                }

                classifications.add(new Classification(name, id, visible));
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
