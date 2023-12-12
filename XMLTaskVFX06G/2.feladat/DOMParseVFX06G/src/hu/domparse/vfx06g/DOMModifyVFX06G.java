package hu.domparse.vfx06g;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyVFX06G {

	public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {

		try {

		File inputFile = new File("XMLVFX06G2.xml");

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = documentBuilder.parse(inputFile);

		// szalon attribútumának módosítása
		Node csoport = doc.getElementsByTagName("szalon").item(0);

		NamedNodeMap attr = csoport.getAttributes();
		Node nodeAttr = attr.getNamedItem("szalon_id");
		nodeAttr.setTextContent("5");

		// szalonnév módosítása
		NodeList list = csoport.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;

				if ("neve".equals(eElement.getNodeName())) {
					if ("Szalon 1".equals(eElement.getTextContent())) {
						eElement.setTextContent("Szalon 5");
					}
				}
			}
		}

		// kerheto_szolgaltatas módosítás
		NodeList kszList = doc.getElementsByTagName("kerheto_szolgaltatas");

		for (int i = 0; i < kszList.getLength(); i++) {
			Node node = kszList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				
				Element eElement = (Element) node;
				String tagValue = eElement.getAttribute("szalon_id");

				if ("1".equals(tagValue)) {
					eElement.setAttribute("szalon_id", "5");
				}

			}
		}

		// Tartalom írása
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		DOMSource source = new DOMSource(doc);

		System.out.println("!Módosított fájl!");

		StreamResult consoleResult = new StreamResult(System.out);
		StreamResult file = new StreamResult(inputFile);

		transformer.transform(source, consoleResult);
		transformer.transform(source, file);

	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
