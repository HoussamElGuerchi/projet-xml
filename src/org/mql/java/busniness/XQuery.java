package org.mql.java.busniness;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;
import javax.xml.xquery.XQStaticContext;

import oracle.xml.xquery.OXQDataSource;
import oracle.xml.xquery.OXQEntity;
import oracle.xml.xquery.OXQEntityKind;
import oracle.xml.xquery.OXQEntityLocator;
import oracle.xml.xquery.OXQEntityResolver;
import oracle.xml.xquery.OXQEntityResolverRequestOptions;
import oracle.xml.xquery.OXQView;

public class XQuery {

	private static class MyEntityResolver extends OXQEntityResolver {
		@Override
		public OXQEntity resolveEntity(OXQEntityKind kind, OXQEntityLocator locator,
				OXQEntityResolverRequestOptions options) {
			if (kind == OXQEntityKind.DOCUMENT) {
				URI systemId = locator.getSystemIdAsURI();
				if ("file".equals(systemId.getScheme())) {
					File file = new File(systemId);
					FileInputStream input;
					try {
						input = new FileInputStream(file);
						OXQEntity result = new OXQEntity(input);
						result.enlistCloseable(input);
						return result;
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return null;
		}
	}

	public static String runQuery(String pathquery) throws XQException, IOException {
		XQConnection con = null;
		XQPreparedExpression expr = null;
		XQSequence result = null;
		try {
			OXQDataSource ds = new OXQDataSource();
			con = ds.getConnection();
			OXQView.getConnection(con).setEntityResolver(new MyEntityResolver());
			File fileQuery = new File(pathquery);
			XQStaticContext ctx = con.getStaticContext();
			ctx.setBaseURI(fileQuery.toURI().toString());
			FileInputStream in = new FileInputStream(fileQuery);
			expr = con.prepareExpression(in, ctx);
			result = expr.executeQuery();
			return result.getSequenceAsString(null);
		} finally {
			if (result != null)
				result.close();
			if (expr != null)
				expr.close();
			if (con != null)
				con.close();
		}
	}

}
