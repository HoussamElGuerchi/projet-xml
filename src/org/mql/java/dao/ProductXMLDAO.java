package org.mql.java.dao;

import java.util.Arrays;
import java.util.Vector;

import org.mql.java.models.Product;
import org.mql.java.parser.XMLDataLoader;
import org.mql.java.parser.XMLNode;

public class ProductXMLDAO implements DAO {
	String source;
	XMLDataLoader<Product> productLoader;
	Vector<Product> products;
	XMLNode productsRoot;

	public ProductXMLDAO(String source) {
		this.source = source;
		productLoader = new XMLDataLoader<>();
		products = productLoader.load(source, Product.class);
		productsRoot = new XMLNode(source);
	}

	public Vector<Product> getAll() {
		XMLNode[] productsChilds = productsRoot.childrens();
		for (XMLNode product : productsChilds) {
		}
		
		return products;
	}

	public Product get(int id) {
		XMLNode[] productsChilds = productsRoot.childrens();
		

		return null;
	}

	public void add(Object t) {

	}

	public void delete(int id) {

	}

	public void update(int id, Object t) {

	}

}
