package org.mql.java.parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class DOMHandler {
	public static Document parse(String source) {

		Document doc = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(source);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	public static void transformXML(Document doc, String source) {

		TransformerFactory factory = TransformerFactory.newDefaultInstance();
		try {
			Transformer transform = factory.newTransformer();
			transform.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult result = new StreamResult(source);
			DOMSource domSource = new DOMSource(doc);
			transform.transform(domSource, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
