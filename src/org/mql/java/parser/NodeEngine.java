package org.mql.java.parser;

import java.lang.reflect.Field;

import org.mql.java.annotations.Attribute;
import org.mql.java.annotations.XMLObject;
import org.mql.java.annotations.XmlElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class NodeEngine {
	private Document document;
	
	public NodeEngine(Document document) {
		this.document = document;
	}
	
	public Element genereteXMLNode(Object obj) throws Exception {
		Class<?> cls = obj.getClass();
		if (!cls.isAnnotationPresent(XMLObject.class)) 
			throw new Exception("Can not generate xml element from class : "+ cls.getName());
		else {
			XMLObject classElement = cls.getAnnotation(XMLObject.class);
			Element parentNode = document.createElement(classElement.name());
			
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Attribute.class)) {
					Attribute attr = field.getAnnotation(Attribute.class);
					parentNode.setAttribute(attr.name(), field.get(obj).toString());
				}
				if (field.isAnnotationPresent(XmlElement.class)) {
					XmlElement fieldElement = field.getAnnotation(XmlElement.class);
					Element child = document.createElement(fieldElement.name());
					child.appendChild(document.createTextNode(field.get(obj).toString()));
					parentNode.appendChild(child);
				}
				field.setAccessible(false);
			}
//			System.out.println(parentNode.getAttributes().getNamedItem("id")+"---");

			return parentNode;
		}
	}
}
