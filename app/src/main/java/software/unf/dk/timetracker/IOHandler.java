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

public class IOHandler {

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
    }
}