package org.mql.java.parser;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {
	private Node node;
	private Document doc;
	private String source;

	public XMLNode(String source) {
		this.source = source;
		init(source);
		node = doc.getDocumentElement();
	}

	private void init(String source) {
		doc = DOMHandler.parse(source);
	}

	public XMLNode(Node node) {
		this.node = node;

	}

	public void add(Object obj) {
		NodeEngine engine = new NodeEngine(doc);
		try {
			Element objXML = engine.genereteXMLNode(obj);
			System.out.println(objXML.getAttribute("id")+"------->");
			node.appendChild(objXML);
			System.out.println(source);
			DOMHandler.transformXML(doc, source);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public XMLNode[] childrens() {
		NodeList childNodes = node.getChildNodes();
		Vector<XMLNode> vect = new Vector<XMLNode>();
		for (int i = 0; i < childNodes.getLength(); i++) {
			XMLNode varXMLNode = new XMLNode(childNodes.item(i));
			if (varXMLNode.getType() == Node.ELEMENT_NODE) {
				vect.add(varXMLNode);
			}
		}
		XMLNode[] arrXML = new XMLNode[vect.size()];
		vect.toArray(arrXML);
		return arrXML;
	}

	public XMLNode getChildByTag(String tagNode) {
		XMLNode[] childrens = childrens();
		XMLNode varNode = null;
		for (int i = 0; i < childrens.length; i++) {
			varNode = childrens[i];
			if (varNode.txtValue().equals(tagNode))
				return varNode;
		}
		return varNode;
	}

	public String getAttribute(String name) {
		NamedNodeMap attributes = node.getAttributes();
		Node attr = attributes.getNamedItem(name);
		if (attr == null) {
			return null;
		}
		return attr.getNodeValue();
	}
	
	public String txtValue() {
		return node.getTextContent();
	}

	public short getType() {
		return node.getNodeType();
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Document getDoc() {
		return doc;
	}

	public String getSource() {
		return source;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public void delete(String reference) {

		NodeList elements = doc.getElementsByTagName("article");
		for (int i = 0; i < elements.getLength(); i++) {
			Element article = (Element) elements.item(i);
			if (article.getElementsByTagName("reference").item(0).getFirstChild().getNodeValue().equals(reference)) {
				article.getParentNode().removeChild(article);
			}
		}
		DOMHandler.transformXML(doc, source);

	}
	
	public String getNodeName() {
		return node.getNodeName();
	}
}
