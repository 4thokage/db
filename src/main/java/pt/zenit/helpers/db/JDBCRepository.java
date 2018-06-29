package pt.zenit.helpers.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDBC implementation reponsable for handling oracle DB connections
 */
public class JDBCRepository implements Repository {

	private static final Logger logger = LoggerFactory.getLogger(JDBCRepository.class);

	// Connection
	private static Connection conn = null;

	private JDBCRepository() {
	}

	/**
	 * Trata da conexão à DB
	 * 
	 * @throws SQLException
	 *             se n der pa se ligar for some reason
	 * @throws ClassNotFoundException
	 *             se n encontrar o driver Oracle
	 */
	public static void connect(String jdbcDriver, String connString) throws SQLException, ClassNotFoundException {
		// Setting Oracle JDBC Driver
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			logger.error("I can´t seem to find your Oracle JDBC Driver... WHERE IS IT? o.o", e);
			throw e;
		}

		logger.info("Oracle JDBC Driver Registered!");

		try {
			conn = DriverManager.getConnection(connString);
		} catch (SQLException e) {
			logger.error("Connection Failed!", e);
			throw e;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public static void disconnect() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	public static Connection getConnection() {
		return conn;
	}

}
