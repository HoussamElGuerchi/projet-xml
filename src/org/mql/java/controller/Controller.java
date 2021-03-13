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

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String xmlSource = getClass().getResource("/products.xml").getPath();
		String xslSource = getClass().getResource("/products.xsl").getPath();
		
		PrintWriter writer = response.getWriter();
		String xmlOutput = processXSL(xmlSource, xslSource);
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
