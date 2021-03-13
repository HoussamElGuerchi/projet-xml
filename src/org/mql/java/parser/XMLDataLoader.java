package org.mql.java.parser;

import java.lang.reflect.Field;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mql.java.annotations.Attribute;
import org.mql.java.annotations.XMLObject;
import org.mql.java.annotations.XmlElement;
import org.w3c.dom.Document;

public class XMLDataLoader<T> {
	private Vector<T> elements;
	private XMLNode root;

	public XMLDataLoader() {
		elements = new Vector<>();
	}

	private T generateObjectFromElement(Class<?> cls, XMLNode element) {
		try {
			T obj = (T) cls.getConstructor().newInstance();
			Field[] fields = cls.getDeclaredFields();

			for (Field field : fields) {
				if (field.isAnnotationPresent(Attribute.class)) {
					field.setAccessible(true);
					
					setPropertyFromAttribute(field, obj, element);
					
					field.setAccessible(false);
				}
				if (field.isAnnotationPresent(XmlElement.class)) {
					field.setAccessible(true);
					
					setPropertyFromElement(field, obj, element);
					
					field.setAccessible(false);
				}
			}

			return obj;
		} catch (Exception e) {
			return null;
		}

	}

	private void setPropertyFromAttribute(Field field, T obj, XMLNode element) {
		Attribute attributeAnnot = field.getAnnotation(Attribute.class);
		String attributeName;
		if ("".equals(attributeAnnot.name())) {
			attributeName = field.getName();
		} else {
			attributeName = attributeAnnot.name();
		}
		setByFieldType(field, obj, element.getAttribute(attributeName));
	}

	private void setPropertyFromElement(Field field, T obj, XMLNode element) {
		XmlElement elementAnnot = field.getAnnotation(XmlElement.class);
		String childElementName;
		if ("".equals(elementAnnot.name())) {
			childElementName = field.getName();
		} else {
			childElementName = elementAnnot.name();
		}
		setByFieldType(field, obj, element.getChildByTag(childElementName).txtValue());
	}

	private void setByFieldType(Field field, Object obj, String value) {
		try {
			if (field.getType() == int.class) {
				field.setInt(obj, Integer.parseInt(value));
			}
			if (field.getType() == double.class) {
				field.setDouble(obj, Double.parseDouble(value));
			}
			if (field.getType() == String.class) {
				field.set(obj, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector<T> load(String path, Class<?> cls) {
		root = new XMLNode(path);
		XMLNode[] childrens = root.childrens();
		String modelElementName;

		if (cls.isAnnotationPresent(XMLObject.class)) {
			XMLObject xmlObject = cls.getDeclaredAnnotation(XMLObject.class);
			if ("".equals(xmlObject.name())) {
				modelElementName = cls.getSimpleName();
			} else {
				modelElementName = xmlObject.name();
			}

			for (XMLNode child : childrens) {
				if (modelElementName.equals(child.getNodeName())) {
					T model = (T) generateObjectFromElement(cls, child);
					System.out.println(model.toString());
					elements.add(model);
				}
			}
		}

		return elements;
	}
}
