package org.mql.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

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

import org.mql.java.models.Product;

@WebServlet(urlPatterns =  "/controller", loadOnStartup = 1)
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String xmlOutput;

	public Controller() {
	}
	
	@Override
	public void init() throws ServletException {
		String xmlSource = getClass().getResource("/products.xml").getPath();
		String xslSource = getClass().getResource("/products.xsl").getPath();
		xmlOutput = processXSL(xmlSource, xslSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			Product prod = new Product(0, "test", 1.0F, "test", "test");
			request.setAttribute("id", prod.getId());
			request.setAttribute("label", prod.getLabel());
			request.setAttribute("price", prod.getPrice());
			request.setAttribute("brand", prod.getBrand());
			request.setAttribute("image", prod.getImage());

			request.getRequestDispatcher("modify.jsp").forward(request, response);
		}
		
		PrintWriter writer = response.getWriter();
		writer.write(xmlOutput);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("ajouter") != null) {
			 
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
			
			StringWriter writer =  new StringWriter();
			transformer.transform(document, new StreamResult(writer));
			output = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

}
