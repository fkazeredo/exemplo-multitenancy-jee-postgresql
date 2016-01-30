package br.com.fk.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

public class MultiTenantProvider implements MultiTenantConnectionProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection conn;

	public MultiTenantProvider() {
		 Context initContext;
		    conn = null;
		    try {
		        initContext = new InitialContext();
		        DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/multitenancyDS");
		        conn = ds.getConnection();
		    } catch (NamingException | SQLException e) {
		        e.printStackTrace();
		    }
	}

	@SuppressWarnings("rawtypes")
	public boolean isUnwrappableAs(Class arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	// metodo que configura uma conexao default para o hibernate

	public Connection getAnyConnection() throws SQLException {

		return conn;
	}

	// metodo que configura conexao para o schema especifico
	public Connection getConnection(String tenant) throws SQLException {

		conn.createStatement().execute("SET search_path TO " + tenant);

		return conn;
	}

	public void releaseAnyConnection(Connection arg0) throws SQLException {

	}

	public void releaseConnection(String arg0, Connection arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

}