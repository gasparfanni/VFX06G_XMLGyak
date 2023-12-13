package hu.domparse.vfx06g;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class DOMQueryVFX06G {
	public static void main(String[] args) {

		try {

			// DocumentBuilder
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse("XMLVFX06G.xml");

			document.getDocumentElement().normalize();

			// XPath 
			XPath xPath = XPathFactory.newInstance().newXPath();
						
			// a Szalonok_VFX06G root elem szolgáltatás gyerekelemei
			 String expression = "Szalonok_VFX06G / szolgaltatas";

			// szalonok, amik rendelkeznek attributummal
			// String expression = "//szalon[@*]";

			// szolgáltatások, amik 60 percesek
			// String expression = "//szolgaltatas[idotartam='60']";

			// szolgáltatások típusa, amik több, mint 12000Ft-ba kerülnek
			// String expression = "//szolgaltatas[ar>12000]/tipus";

			// 2-es azonositoju szalon 
			// String expression = "//szalon[@szalon_id='2']";

			// a harmadik szalon kivalasztasa
			// String expression = "Szalonok_VFX06G/szalon[3]";

			// vendégek telefonszámának és fizetett mennyiségének kiíratása
			// String expression = "//telefonszam | //fizetett";

			// vendég első két eleme
			// String expression = "//vendeg[position()<3]";
			
			// keszitunk egy listat, majd az xPath kifejezest le kell forditani es ki kell ertekelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("\nAktualis elem: " + node.getNodeName());

				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("szolgaltatas")) {

					Element elem = (Element) node;

                        String id = elem.getAttribute("szolgaltatas_id");
						String munk_id = elem.getAttribute("munka_alkalmazott");
        
                        Node node1 = elem.getElementsByTagName("tipus").item(0);
                        String tipus = node1.getTextContent();
        
                        Node node2 = elem.getElementsByTagName("ar").item(0);
                        String ar = node2.getTextContent();
        
                        Node node3 = elem.getElementsByTagName("idotartam").item(0);
                        String idotart = node3.getTextContent();
        
                        System.out.println("Szolgáltatás id-je: " + id);
						System.out.println("Alkalmazott id-je: " + munk_id);
                        System.out.println("Típus: " + tipus);
                        System.out.println("Ár: " + ar);
                        System.out.println("Időtartam: " + idotart);

				}
				
				// szolgáltatás típusa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("tipus")) {

					Element element = (Element) node;

					System.out.println("Szolgáltatás típusa: " + element.getTextContent());

				}

				// vendég telefonszáma
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("telefonszam")) {

					Element element = (Element) node;

					System.out.println("Telefonszám:  " + element.getTextContent());

				}

                // vendég fizetett összege
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("fizetett")) {

					Element element = (Element) node;

					System.out.println("Fizetett: " + element.getTextContent());

				}

				//szalon kiiratasa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("szalon")) {

					Element element = (Element) node;

					System.out.println("ID: " + element.getAttribute("szalon_id"));
					System.out.println("Karbantartó cég id: " + element.getAttribute("karbantartja"));

					System.out.println(
							"Főnök neve: " + element.getElementsByTagName("fonok").item(0).getTextContent());

					System.out.println(
							"Szalon neve: " + element.getElementsByTagName("neve").item(0).getTextContent());
         
                    if (nodeList.item(i).getChildNodes().getLength() > 4) {
						int db = 0;
						Node node3 = element.getElementsByTagName("cim").item(0);
						while (node3 != null) {
							node3 = element.getElementsByTagName("cim").item(db);
							if (node3 != null) {
								Node n = element.getElementsByTagName("iranyitoszam").item(db);
								String isz = n.getTextContent();
								System.out.println("Telephely iranyitoszama: " + isz);
								Node n2 = element.getElementsByTagName("varos").item(db);
								String v = n2.getTextContent();
								System.out.println("Telephely varosa: " + v);
                                Node n3 = element.getElementsByTagName("utca_hazszam").item(db);
								String u = n3.getTextContent();
								System.out.println("Telephely utca,hazszama: " + u);
							}
							db++;
						}

					}

					if (nodeList.item(i).getChildNodes().getLength() > 5) {
						int db = 0;
						Node node3 = element.getElementsByTagName("elerhetoseg").item(0);
						while (node3 != null) {
							node3 = element.getElementsByTagName("elerhetoseg").item(db);
							if (node3 != null) {
								Node n = element.getElementsByTagName("telefonszam").item(db);
								String tsz = n.getTextContent();
								System.out.println("Szalon telefonszama: " + tsz);
	
								Node n2 = element.getElementsByTagName("email").item(db);
								String email = n2.getTextContent();
								System.out.println("Szalon emailje: " + email);
	
								Node n3 = element.getElementsByTagName("facebook").item(db);
								String face = n3.getTextContent();
								System.out.println("Szalon facebook oldala: " + face);
							}
							db++;
						}
	
					}

				}

				//vendég kiiratasa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("vendeg")) {

					Element element = (Element) node;

					System.out.println("ID: " + element.getAttribute("vendeg_id"));

					System.out.println("Telefonszám: " + element.getElementsByTagName("telefonszam").item(0).getTextContent());

					System.out.println(
							"Email: " + element.getElementsByTagName("email").item(0).getTextContent());

                    System.out.println(
							"Fizetett: " + element.getElementsByTagName("fizetett").item(0).getTextContent());        
                    
					System.out.println(
							"Név: " + element.getElementsByTagName("nev").item(0).getTextContent());        
                    

				}

			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}

}
