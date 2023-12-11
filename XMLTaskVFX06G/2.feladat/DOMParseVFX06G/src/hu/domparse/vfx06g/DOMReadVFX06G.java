package hu.domparse.vfx06g;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class DOMReadVFX06G {
	public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {

		File xmlFile = new File("XML_VFX06G.xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

		Document doc = dBuilder.parse(xmlFile);

		doc.getDocumentElement().normalize();

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

		NodeList kcList = doc.getElementsByTagName("karbantarto_ceg");

		for (int i = 0; i < kcList.getLength(); i++) {

			Node nNode = kcList.item(i);
			System.out.println("\nCurrent element: " + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;
				String id = elem.getAttribute("ceg_id");

				Node node1 = elem.getElementsByTagName("ceg").item(0);
				String ceg = node1.getTextContent();

				System.out.println("A cég id-je: " + id);
				System.out.println("Cég: " + ceg);

				if (kcList.item(i).getChildNodes().getLength() > 2) {
					int db = 0;
					Node node2 = elem.getElementsByTagName("kapcsolattarto").item(0);
					while (node2 != null) {
						node2 = elem.getElementsByTagName("kapcsolattarto").item(db);
						if (node2 != null) {

							Node n = elem.getElementsByTagName("telefonszam").item(db);
							String t = n.getTextContent();
							System.out.println("Kapcsolattarto telefonszama: " + t);

							Node n2 = elem.getElementsByTagName("nev").item(db);
							String nev = n2.getTextContent();
							System.out.println("Kapcsolattarto neve: " + nev);

						}
						db++;
					}

				}

				if (kcList.item(i).getChildNodes().getLength() > 3) {
					int db = 0;
					Node node2 = elem.getElementsByTagName("telephely").item(0);
					while (node2 != null) {
						node2 = elem.getElementsByTagName("telephely").item(db);
						if (node2 != null) {

							Node n = elem.getElementsByTagName("varos").item(db);
							String v = n.getTextContent();
							System.out.println("Telephely városa: " + v);

							Node n2 = elem.getElementsByTagName("iranyitoszam").item(db);
							String isz = n2.getTextContent();
							System.out.println("Telephely irányítószáma: " + isz);

							Node n3 = elem.getElementsByTagName("utca").item(db);
							String u = n3.getTextContent();
							System.out.println("Telephely utcája: " + u);

							Node n4 = elem.getElementsByTagName("hazszam").item(db);
							String hsz = n4.getTextContent();
							System.out.println("Telephely házszáma: " + hsz);

						}
						db++;
					}

				}

			}
		}

		NodeList szList = doc.getElementsByTagName("szalon");

		for (int i = 0; i < szList.getLength(); i++) {

			Node fNode = szList.item(i);
			System.out.println("\nCurrent element: " + fNode.getNodeName());

			if (fNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) fNode;
				String id = elem.getAttribute("szalon_id");
				String karbantart = elem.getAttribute("karbantartja");

				Node node1 = elem.getElementsByTagName("fonok").item(0);
				String fonok = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("neve").item(0);
				String nev = node2.getTextContent();

				System.out.println("A szalon id-je: " + id);
				System.out.println("A szalon karbantartojanak id-je: " + karbantart);
				System.out.println("A fonok neve: " + fonok);
				System.out.println("A szalon neve: " + nev);

				if (szList.item(i).getChildNodes().getLength() > 4) {
					int db = 0;
					Node node3 = elem.getElementsByTagName("cim").item(0);
					while (node3 != null) {
						node3 = elem.getElementsByTagName("cim").item(db);
						if (node3 != null) {
							Node n = elem.getElementsByTagName("iranyitoszam").item(db);
							String isz = n.getTextContent();
							System.out.println("Telephely irányítószáma: " + isz);

							Node n2 = elem.getElementsByTagName("varos").item(db);
							String v = n2.getTextContent();
							System.out.println("Telephely városa: " + v);

							Node n3 = elem.getElementsByTagName("utca_hazszam").item(db);
							String u = n3.getTextContent();
							System.out.println("Telephely utcája és házszáma: " + u);
						}
						db++;
					}

				}

				if (szList.item(i).getChildNodes().getLength() > 5) {
					int db = 0;
					Node node3 = elem.getElementsByTagName("elerhetoseg").item(0);
					while (node3 != null) {
						node3 = elem.getElementsByTagName("elerhetoseg").item(db);
						if (node3 != null) {
							Node n = elem.getElementsByTagName("telefonszam").item(db);
							String tsz = n.getTextContent();
							System.out.println("Szalon telefonszama: " + tsz);

							Node n2 = elem.getElementsByTagName("email").item(db);
							String email = n2.getTextContent();
							System.out.println("Szalon emailje: " + email);

							Node n3 = elem.getElementsByTagName("facebook").item(db);
							String face = n3.getTextContent();
							System.out.println("Szalon facebook oldala: " + face);
						}
						db++;
					}

				}

				

			}
		}
		
		NodeList tList = doc.getElementsByTagName("kedvezmeny");

		for (int i = 0; i < tList.getLength(); i++) {

			Node tNode = tList.item(i);
			System.out.println("\nCurrent element: " + tNode.getNodeName());

			if (tNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) tNode;
				String aid = elem.getAttribute("kedvezmeny_id");


				Node node2 = elem.getElementsByTagName("elsoIdopont").item(0);
				String nev = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("szolgaltatasok").item(0);
				String fizetes = node3.getTextContent();

				System.out.println("A kedvezmeny kulcsa: " + aid);
				System.out.println("Az elsoIdopont: " + nev);
				System.out.println("Szolgaltatas: " + fizetes);

			}
		}
		
		NodeList csList = doc.getElementsByTagName("szolgaltatas");

		for (int i = 0; i < csList.getLength(); i++) {

			Node vNode = csList.item(i);
			System.out.println("\nCurrent element: " + vNode.getNodeName());

			if (vNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) vNode;
				String sz_id = elem.getAttribute("szolgaltatas_id");

				Node node1 = elem.getElementsByTagName("tipus").item(0);
				String tipus = node1.getTextContent();

				Node node2 = elem.getElementsByTagName("ar").item(0);
				String ar = node2.getTextContent();

				Node node3 = elem.getElementsByTagName("idotartam").item(0);
				String idotart = node3.getTextContent();
				
				System.out.println("A szolgaltatas azonosítója: " + sz_id);
				System.out.println("A szolgáltatás típusa: " + tipus);
				System.out.println("Ára: " + ar);
				System.out.println("Időtartama: " + idotart);
				
			}
		}
		
		NodeList vList = doc.getElementsByTagName("vendeg");

		for (int i = 0; i < vList.getLength(); i++) {

			Node gyNode = vList.item(i);
			System.out.println("\nCurrent element: " + gyNode.getNodeName());

			if (gyNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) gyNode;
				String id = elem.getAttribute("vendeg_id");
				String kid = elem.getAttribute("kedvezmeny_id");

				Node node1 = elem.getElementsByTagName("telefonszam").item(0);
				String tf = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("email").item(0);
				String email = node2.getTextContent();

				Node node3 = null;
				String fizetett = null;
				if(elem.getElementsByTagName("fizetett").item(0) != null){
					node3 = elem.getElementsByTagName("fizetett").item(0);
					fizetett = node3.getTextContent();
				}

				Node node4 = elem.getElementsByTagName("nev").item(0);
				String nev = node4.getTextContent();

				System.out.println("Vendég egyedi azonosítója: " + id);
				System.out.println("Szolgáltatás kedvezmény egyedi azonosítója: " + kid);
				System.out.println("Vendég telefonszáma: " + tf);
				System.out.println("Vendég emailje: " + email);
				if(node3 != null)
					System.out.println("Vendég által fizetett összeg: " + fizetett);
				System.out.println("A vendég neve: " + nev);


			}
		}
		
		NodeList thList = doc.getElementsByTagName("kert_szolgaltatas");

		for (int i = 0; i < thList.getLength(); i++) {

			Node lNode = thList.item(i);
			System.out.println("\nCurrent element: " + lNode.getNodeName());

			if (lNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) lNode;
				String ksz_id = elem.getAttribute("ksz_id");
				String vid = elem.getAttribute("vevo_id");
				String szid = elem.getAttribute("szolgaltatas_id");

				Node node1 = elem.getElementsByTagName("datum").item(0);
				String date = node1.getTextContent();

				System.out.println("A kért szolgáltatás kapcsolat egyedi azonosítója: " + ksz_id);
				System.out.println("A vevő azonosítója: " + vid);
				System.out.println("A szolgáltatás azonosítója: " + szid);
				System.out.println("A dátum: " + date);

			}
		}
		
		NodeList lzList = doc.getElementsByTagName("kerheto_szolgaltatas");

		for (int i = 0; i < lzList.getLength(); i++) {
			
			Node tnNode = lzList.item(i);
			System.out.println("\nCurrent element: " + tnNode.getNodeName());

			if (tnNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) tnNode;
				String szid = elem.getAttribute("szalon_id");
				String szolid = elem.getAttribute("szolgaltatas_id");

				System.out.println("A szalon azonosítója: " + szid);
				System.out.println("A szolgáltatás azonosítója: " + szolid);

			}
		}

	}
}
