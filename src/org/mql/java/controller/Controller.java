package org.mql.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.mql.java.dao.ProductXMLDAO;
import org.mql.java.models.Product;
import org.mql.java.parser.XMLDataLoader;
import org.mql.java.parser.XMLNode;

@WebServlet(urlPatterns =  "/controller", loadOnStartup = 1)
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String xmlOutput;
	private String xmlSource;
	private XMLNode productRoot;
	private ProductXMLDAO productXMLDAO;

	public Controller() {
	}

	@Override
	public void init() throws ServletException {
		xmlSource = getClass().getResource("/products.xml").getPath();
		String xslSource = getClass().getResource("/products.xsl").getPath();
		xmlOutput = processXSL(xmlSource, xslSource);
		productRoot = new XMLNode(xmlSource);
		productXMLDAO = new ProductXMLDAO(xmlSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int requestedProductId = Integer.parseInt(request.getParameter("id"));
			
			Product prod = new Product(0, "test", 1.0F, "test", "test");
			request.setAttribute("id", prod.getId());
			request.setAttribute("label", prod.getLabel());
			request.setAttribute("price", prod.getPrice());
			request.setAttribute("brand", prod.getBrand());
			request.setAttribute("image", prod.getImage());

			request.getRequestDispatcher("modify.jsp").forward(request, response);
		}
		
		XMLDataLoader<Product> productLoader = new XMLDataLoader<>();
		Vector<Product> products = productLoader.load(xmlSource, Product.class);
		
		PrintWriter writer = response.getWriter();
		writer.write(xmlOutput);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("create") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			String label = request.getParameter("label");
			float price = Float.parseFloat(request.getParameter("price"));
			String brand = request.getParameter("brand");
			String image = request.getParameter("image");
			Product proudct = new Product(id, label, price, brand, image);
			productRoot.add(proudct);
		}
		
		if (request.getParameter("update") != null) {
			
		}
		
		if (request.getParameter("delete") != null) {
			
		}
		

		doGet(request, response);

	}

	private String processXSL(String xmlDocument, String xslSource) {
		TransformerFactory factory = TransformerFactory.newDefaultInstance();
		StreamSource document = new StreamSource(new File(xmlDocument));
		Source xsl = new StreamSource(new File(xslSource));
		String output = "";

		try {
			Transformer transformer = factory.newTransformer(xsl);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			StringWriter writer = new StringWriter();
			transformer.transform(document, new StreamResult(writer));
			output = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

}
