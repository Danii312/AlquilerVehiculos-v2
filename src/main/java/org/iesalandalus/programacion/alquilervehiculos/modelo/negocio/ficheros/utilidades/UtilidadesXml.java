package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class UtilidadesXml {

    private UtilidadesXml() {

    }

    public static Document xmlToDom(String rutaXml) {
        Document document = null;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(rutaXml);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return document;
    }

    public static boolean domToXml(Document dom, String rutaXml) {
        try {
            File file = new File(rutaXml);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 4);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            StreamResult result = new StreamResult(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            DOMSource source = new DOMSource(dom);
            transformer.transform(source, result);
            return true;
        } catch (TransformerException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static Document crearDomVacio(String raiz) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        Document document = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            document.appendChild(document.createElement(raiz));
            return document;
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
        return document;
    }

}
