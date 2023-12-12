package hu.domparse.vfx06g;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
public class DOMReadVFX06G {
	public static void main(String argv[]) throws Exception {
	    
        File xmlFile = new File("XMLVFX06G.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuild = dbf.newDocumentBuilder();
        Document doc = dBuild.parse(xmlFile);

        doc.getDocumentElement().normalize();

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult console = new StreamResult(System.out);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(source, console);
        
}
}
