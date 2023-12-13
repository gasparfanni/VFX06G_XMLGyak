package hu.domparse.vfx06g;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
public class DOMWriteVFX06G {
	public static void main(String argv[]) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

		Document doc = dBuilder.newDocument();

		Element root = doc.createElementNS("VFX06G", "Szalonok_VFX06G");
		doc.appendChild(root);

		// karbantarto_ceg

		root.appendChild(createCeg(doc, "1", "Ceg 1", "06705684589", "Kiss István", "Budapest", "1024", "Kossuth utca", "10"));
        root.appendChild(createCeg(doc, "2", "Ceg 2", "06705684111", "Kovács Anna", "Budapest", "1132", "Rákospatak utca", "5"));
        root.appendChild(createCeg(doc, "3", "Ceg 3", "06705684233", "Szabó István", "Debrecen", "3521", "Sport utca",  "8"));

		Element element = (Element) doc.getElementsByTagName("karbantarto_ceg").item(0);
		Comment comment = doc.createComment("Karbantartó cégek");
		element.getParentNode().insertBefore(comment, element);

		// szalon

        root.appendChild(createSzalon(doc, "1", "1", "Nagy Éliás", "Szalon 1", "1024", "Budapest", "Kossuth utca 10", "06705214787", "nagyelias@gmail.com", "nagyelias32"));
        root.appendChild(createSzalon(doc, "2", "2", "Szalon 2", "Szegedi Dóra", "1132", "Budapest", "Rákospatak utca 5", "06701112547", "szegedidora@gmail.com", "dorasz02"));
        root.appendChild(createSzalon(doc, "3", "3", "Szalon 3", "Kovács Adrienn", "3521", "Debrecen", "Sport utca 8", "06705214733", "kovacsadrienn@gmail.com", "szepsegvarazsszalon"));

		element = (Element) doc.getElementsByTagName("szalon").item(0);
		comment = doc.createComment("Szalonok");
		element.getParentNode().insertBefore(comment, element);

		// kedvezmeny

		String[] szolg = {"Smink", "Manikűr"};
		root.appendChild(createKedvezmeny(doc, "1", "1", "2023.05.12", szolg));
		String[] szolg2 = {"Fodrászat"};
		root.appendChild(createKedvezmeny(doc, "2", "2", "2023.05.12", szolg2));
		String[] szolg3 = {"Arckezelés", "Manikűr"};
		root.appendChild(createKedvezmeny(doc, "3", "3", "2023.05.12", szolg3));

		element = (Element) doc.getElementsByTagName("kedvezmeny").item(0);
		comment = doc.createComment("Kedvezmények");
		element.getParentNode().insertBefore(comment, element);

		// szolgaltatas

		root.appendChild(createSzolgaltatas(doc, "1", "Smink", "15000", "60"));
		root.appendChild(createSzolgaltatas(doc, "2", "Fodrászat", "25000", "90"));
		root.appendChild(createSzolgaltatas(doc, "3", "Manikűr", "8000", "45"));

		element = (Element) doc.getElementsByTagName("szolgaltatas").item(0);
		comment = doc.createComment("Szolgáltatások");
		element.getParentNode().insertBefore(comment, element);

		// vendeg

		root.appendChild(createVendeg(doc, "1", "06301234567", "kovacsnora@gmail.com", "25000", "Kovács Nóra"));
		root.appendChild(createVendeg(doc, "2", "06705555555", "szabo.andras@gmail.com", "", "Szabó András"));
		root.appendChild(createVendeg(doc, "3", "06708887777", "molnar.rita@gmail.com", "12000", "Molnár Rita"));
		
		element = (Element) doc.getElementsByTagName("vendeg").item(0);
		comment = doc.createComment("Vendégek");
		element.getParentNode().insertBefore(comment, element);

		// kert_szolgaltatas

        root.appendChild(createKertSzolgaltatas(doc, "3", "1", "2", "2023-05-15"));
        root.appendChild(createKertSzolgaltatas(doc, "1", "3", "1", "2023-05-15"));
        root.appendChild(createKertSzolgaltatas(doc, "2", "2", "3", "2023-05-15"));

		element = (Element) doc.getElementsByTagName("kert_szolgaltatas").item(0);
		comment = doc.createComment("Kért szolgáltatás több-több kapcsolat");
		element.getParentNode().insertBefore(comment, element);

		// kerheto_szolgaltatas

		root.appendChild(createKerhetoSzolgaltatas(doc, "2", "1"));
		root.appendChild(createKerhetoSzolgaltatas(doc, "1", "3"));
		root.appendChild(createKerhetoSzolgaltatas(doc, "3", "2"));

		element = (Element) doc.getElementsByTagName("kerheto_szolgaltatas").item(0);
		comment = doc.createComment("Kérhető szolgáltatások több-több kapcsolat");
		element.getParentNode().insertBefore(comment, element);

		// Transform

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();

		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

		// File letrehozas

		DOMSource source = new DOMSource(doc);
		File myFile = new File("XMLVFX06G2.xml");

		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);

		transf.transform(source, console);
		transf.transform(source, file);

	}

	private static Node createCeg(Document doc, String ceg_id, String ceg, String telefonszam, String nev,
			String varos, String iranyitoszam, String utca, String hazszam) {

		Element kc = doc.createElement("karbantarto_ceg");

		kc.setAttribute("ceg_id", ceg_id);
		kc.appendChild(createElement(doc, "ceg", ceg));

		Element kt = doc.createElement("kapcsolattarto");
		kt.appendChild(createElement(doc, "telefonszam", telefonszam));
        kt.appendChild(createElement(doc, "nev", nev));

		kc.appendChild(kt);

		Element cim = doc.createElement("telephely");
		cim.appendChild(createElement(doc, "varos", varos));
		cim.appendChild(createElement(doc, "iranyitoszam", iranyitoszam));
		cim.appendChild(createElement(doc, "utca", utca));
		cim.appendChild(createElement(doc, "hazszam", hazszam));

        kc.appendChild(cim);
		
		return kc;

	}

	private static Node createSzalon(Document doc, String szalon_id, String karbantartja, String fonok,
			String neve, String isz, String varos, String utcahazszam, String telefonszam, String email, String facebook) {

		Element sz = doc.createElement("szalon");

		sz.setAttribute("szalon_id", szalon_id);
		sz.setAttribute("karbantartja", karbantartja);
		sz.appendChild(createElement(doc, "fonok", fonok));
		sz.appendChild(createElement(doc, "neve", neve));

		Element cim = doc.createElement("cim");
		cim.appendChild(createElement(doc, "iranyitoszam", isz));
        cim.appendChild(createElement(doc, "varos", varos));
		cim.appendChild(createElement(doc, "utca_hazszam", utcahazszam));

        sz.appendChild(cim);

		Element elerh = doc.createElement("elerhetoseg");
		elerh.appendChild(createElement(doc, "telefonszam", telefonszam));
        elerh.appendChild(createElement(doc, "email", email));
		elerh.appendChild(createElement(doc, "facebook", facebook));
		
		sz.appendChild(elerh);
		
		return sz;

	}

	private static Node createKedvezmeny(Document doc, String kedvezmeny_id, String vendeg_id, String elsoIdopont, 
	String[] szolgaltatas) {

		Element kedv = doc.createElement("kedvezmeny");

		kedv.setAttribute("kedvezmeny_id", kedvezmeny_id);
		kedv.setAttribute("vendeg_id", kedvezmeny_id);

		kedv.appendChild(createElement(doc, "elsoIdopont", elsoIdopont));

		Node[] node1 = appendArray(doc, "szolgaltatas", szolgaltatas);

		for (int i = 0; i < szolgaltatas.length; i++) {
			kedv.appendChild(node1[i]);
		}
		
		return kedv;

	}

	private static Node createSzolgaltatas(Document doc, String szolgaltatas_id, String tipus, 
        String ar, String idotartam) {

		Element csop = doc.createElement("szolgaltatas");

		csop.setAttribute("szolgaltatas_id", szolgaltatas_id);
        csop.appendChild(createElement(doc, "tipus", tipus));
		csop.appendChild(createElement(doc, "ar", ar));
		csop.appendChild(createElement(doc, "idotartam", idotartam));

		return csop;

	}

	private static Node createVendeg(Document doc, String vendeg_id, String telefonszam, String email, String fizetett,
			String nev) {

		Element v = doc.createElement("vendeg");

		v.setAttribute("vendeg_id", vendeg_id);
		v.appendChild(createElement(doc, "telefonszam", telefonszam));
		v.appendChild(createElement(doc, "email", email));

        if(!(fizetett.equals(""))){
            v.appendChild(createElement(doc, "fizetett", fizetett));
        }
		
		v.appendChild(createElement(doc, "nev", nev));

		return v;

	}

	private static Node createKertSzolgaltatas(Document doc, String ksz_id, String vevo_id, String szolgaltatas_id,
			String datum) {

		Element th = doc.createElement("kert_szolgaltatas");

		th.setAttribute("ksz_id", ksz_id);
		th.setAttribute("vevo_id", vevo_id);
        th.setAttribute("szolgaltatas_id", szolgaltatas_id);
		th.appendChild(createElement(doc, "datum", datum));

		return th;

	}

	private static Node createKerhetoSzolgaltatas(Document doc, String szalon_id, String szolgaltatas_id) {

		Element lz = doc.createElement("kerheto_szolgaltatas");

		lz.setAttribute("szalon_id", szalon_id);
		lz.setAttribute("szolgaltatas_id", szolgaltatas_id);

		return lz;

	}

	private static Node createElement(Document doc, String name, String value) {

		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));

		return node;

	}

	private static Node[] appendArray(Document doc, String name, String[] value) {

		Element nodes[] = new Element[value.length];

		for (int i = 0; i < value.length; i++) {

			nodes[i] = doc.createElement(name);
			nodes[i].appendChild(doc.createTextNode(value[i]));

		}

		return nodes;

	}

}
