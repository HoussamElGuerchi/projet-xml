package org.mql.java.parser;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {
	private Node node;
	private DOMHandler domHandler;

	public XMLNode(String source) {
		domHandler = new DOMHandler(source);
		Document document = domHandler.parse();
		node = document.getDocumentElement();
	}

	public XMLNode(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}

	public XMLNode[] getChildrens() {
		Vector<XMLNode> childsVect = new Vector<>();
		NodeList childsList = node.getChildNodes();

		for (int i = 0; i < childsList.getLength(); i++) {
			if (childsList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				childsVect.add(new XMLNode(childsList.item(i)));
			}
		}

		XMLNode[] childrens = new XMLNode[childsVect.size()];
		childsVect.toArray(childrens);
		return childrens;
	}

	public XMLNode getChild(String name) {
		XMLNode[] childrens = getChildrens();
		for (int i = 0; i < childrens.length; i++) {
			if (name.equals(childrens[i].getNode().getNodeName()))
				return childrens[i];
		}
		return null;
	}

	public String getAttribute(String attrName) {
		NamedNodeMap attributes = node.getAttributes();
		Node attr = attributes.getNamedItem(attrName);
		return attr.getNodeValue();
	}
	
	public String textValue() {
		return node.getTextContent();
	}

	public void addNode(Object obj) {
		NodeEngine engine = new NodeEngine(domHandler.getDocument());
		try {
			XMLNode newNode = engine.generateXMLNode(obj);
			node.appendChild(newNode.getNode());
			domHandler.transform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteNode(int index) {
		
	}

}
