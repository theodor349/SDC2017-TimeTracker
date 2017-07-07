package software.unf.dk.timetracker;

import android.util.Log;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Handles processing of XML Files.
 */

// TODO: compartmentalize into ActionIOHandler and CategoryIOHandler?

public class IOHandler {
    private final String DOCUMENT_HEADER = "<?xml version=\"1.0\"?>\n<actions>\n</actions>";

    protected File file;
    protected DocumentBuilderFactory factory;
    protected DocumentBuilder builder;
    protected Document document;

    /**
     * Creates a new IOHandler
     * @param file File the handler should write to
     */
    public IOHandler(File file) {
        this.file = file;
        try {
            // Create file if it doesn't exist
            if (file.createNewFile()) {
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
}